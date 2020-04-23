import java.util.HashMap;

/** Representation of the router's control plane */
public class ControlPlane {
    /** The router this control is on */
    Router router;
    /** The routing algorithm in use */
    RouterLogic algo;
    /** The cached interface state */
    HashMap<Interface,Boolean> ifacestate;
    /** The interfaces the control plane knows about... May not be used */
    HashMap<String,Interface> ifaces;

    /** Create a new instance */
    public ControlPlane(Router r, RouterLogic algo) {
        this.algo = algo;
        router = r;
        ifacestate = new HashMap<Interface,Boolean>();
        ifaces = new HashMap<String,Interface>();
    }
    
    /** Adds an interface to the control plane */
    public void addInterface(Interface iface) {
        ifacestate.put(iface, iface.alive());
        ifaces.put(iface.id, iface);
    }

    /** Sets an address and netmask for an interface */
    public void setInterfaceAddress(String id, int addr, int net, int mask) {
        // tell the routing algorithm about the change
        algo.ifaceAddrNetSet(id, addr, net, mask);
    }

    /** Receives a packet destined for this router */
    public void receive(Packet p) {
        if(p.ttl<1) {
            // TODO: send unreachable to sender
            router.log("TTL expired",p);
            return;
        }
        algo.receive(p);
    }

    /** respond to a simulation clock tick */
    public void tick() {
        // check the interfaces for a state change
        for(Interface i: ifacestate.keySet()) {
            if(ifacestate.get(i) != i.alive()) {
                ifacestate.put(i, i.alive());
                // let the algorithm know
                algo.ifaceStateChange(i.id, i.alive());
            }
        }
    }
}

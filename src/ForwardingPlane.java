import java.util.HashMap;

/** Representation of the router forwarding plane 
 *  <p>Currently lossless and allows infinitely many packets to be queued
 */
public class ForwardingPlane {
    /** Router supported by this Forwarding Plane */
    Router router;
    /** Interfaces traffic can move on */
    HashMap<String,Interface> ifaces;
    /** Queued packets */
    HashMap<String,ArrayDeque<Packet>> queues;
    /** Addresses the control plane cares about */
    HashSet<Integer> localAddresses;
    /** Forwarding on? */
    boolean running;

    /** Create a new instance 
     *@param r the router this forwarding plane supports
     */
    public ForwardingPlane(Router r) {
        router = r;
        ifaces = new HashMap<String,Interface>();
        queues = new HashMap<String,Interface>();
        localAddresses = new HashSet<Integer>();
    }
    
    /** Stop forwarding */
    public void stop() {
        running = false;
        queues = new HashMap<String,Interface>();
    }
    /** Start forwarding */
    public void start() {
        running = true;
    }

    /** Notify the forwarding plane of the existance of an interface
     *@param i the interface to add
     */
    public void addInterface(Interface i) {
        ifaces.put(i.id, i);
        queues.put(i.id, new ArrayDeque<Packet>());
    }

    /** Process a packet */
    public void process(Packet p, Interface i) {
        if(!running) { return; }
        p = p.clone();
        if(localAddresses.contains(p.dst) || p.ttl<1) {
            router.receive(p);
            return;
        }
        String iface = table.apply(p);
        if(iface != null) {
            queues.put(iface, p);
        } else {
            router.log("Unmatched destination",p);
        }
    }

    /** Respond to a simulator tick by attempting to send the next
     *  queued packet on each interface
     */
    public void tick() {
        for(Interface iface: ifaces.values()) {
            if(iface.ready() && queues.get(iface.id).size()>0) {
                iface.sendPacket(queues.get(iface.id).removeFirst());
            }
        }
    }
}

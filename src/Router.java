/** Represents a routing device */
public class Router {
    /** The control plane in this router */ 
    private ControlPlane ctl;
    /** The forwarding plane in this router */
    private ForwardingPlane fwd;
    /** The router ports */
    private Interface[] ports;
    /** An identifier to make logs easier to read */
    private String name;

    /** Creates a new router
     *@param ports the number of ports for this router
     *@param algo  the routing algorithm to run
     *@param n the name of this router
     */
    public Router(String n, int ports, RouterLogic algo) {
        name = n;
        RoutingTable rt = new RoutingTable(1024);
        ctl = new ControlPlane(this, algo);
        fwd = new ForwardingPlane(this, rt);
        this.ports = new Interface[ports];
        for(int i=0; i<ports; i++) {
            Interface iface = new Interface("iface"+i);
            fwd.addInterface(iface);
            ctl.addInterface(iface);
            this.ports[i] = iface;
        }
    }

    /** Plugs a wire into a port of the router
     *@param wire the wire to plug in
     */
    public void connect(Wire w, int portnum) {
        ports[portnum].connect(w);
    }

    /** Receives a packet to process */
    public void receive(Packet p) {
        ctl.receive(p);
    }

    /** Turns the router on */
    public void turnOn() {
        fwd.start();
    }
    /** Turns the router off */
    public void turnOff() {
        fwd.stop();
    }
    /** respond to a simulation tick */
    public void tick() {
        fwd.tick();
        ctl.tick();
    }

    /** Logging interface to get some information out */
    public void log(String msg, Packet p) {
    }
}

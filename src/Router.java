/** Represents a routing device */
public class Router {
    /** The router logic to use in this router */
    private RouterLogic algo;
    /** The control plane in this router */ 
    private ControlPlane ctl;
    /** The forwarding plane in this router */
    private ForwardingPlane fwd;
    /** The router ports */
    private Interface[] ports;

    /** Creates a new router
     *@param ports the number of ports for this router
     *@param algo  the routing algorithm to run
     */
    public Router(int ports, RouterLogic algo) {
        this.algo = algo;
        ctl = new ControlPlane(algo);
        fwd = new ForwardingPlane();
        ctl.setForwarding(fwd);
        fwd.setControl(ctl);
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
}

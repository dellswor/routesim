/** Representation of an interface on the router device */
public class Interface {
    /** The identifier of the interface */
    String id;
    /** The "wire" this interface is connected to */
    Wire wire;
    /** The forwarding plane that processes packets from this interface */
    ForwardingPlane forwarder;
    /** The control plane that controls this interface */
    ControlPlane control;

    /** Creates a new interface instance 
     *@param id the identifier for this interface on this device
     */
    public Interface(String id) {
        this.id = id;
    }

    /** Configures this interface to live on a specific router
     *@param ctl the control plane for this interface
     *@param fwd the forwarding plane for this interface
     */
    public void setPlanes(ControlPlane ctl, ForwardingPlane fwd) {
        this.forwarder = fwd;
        this.control   = ctl;
    }

    /** "Plugs" a wire into this interface
     *@param wire the wire to connect
     */
    public void setWire(Wire wire) {
        this.wire = wire;
        wire.addEndpoint(this);
    }

    /** Receives a packet from the wire
     *@param p the packet recieved
     */
    public void recievePacket(Packet p) {
        forwarder.process(p, this);
    }
    /** Sends a packet to the wire
     *@param p the packet to send
     */
    public void sendPacket(Packet p) {
        if(wire==null) {
            control.ifaceDown(this);
        }
        success = wire.process(p, this);
        if(!success) {
            control.ifaceDown(this);
        }
    }

    /** is the link ready to send? */
    public boolean ready() {
        return wire.ready(this);
    }
    /** is the link alive? */
    public boolean alive() {
        return wire.alive();
    }
}

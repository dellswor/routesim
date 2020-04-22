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
     *@param wire the wire this interface is connected to
     *@param forwarder the forwarding plane this interface is connected to
     *@param control the control plane this interface is connected to
     */
    public Interface(String id,ForwardingPlane forwarder, ControlPlane control) {
        this.id = id;
        this.forwarder = forwarder;
        this.control   = control;
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
}

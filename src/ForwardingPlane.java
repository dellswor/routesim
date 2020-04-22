import java.util.HashMap;

/** Represents the forwarding plane of the router (the part that actually
 *  directs packets received on one interface out another interface
 */
public class ForwardingPlane {
    /** The interfaces organized by identifier */
    private HashMap<String,Interface> interfaces;
    /** The control plane that is connected to this forwarding plane */
    private ControlPlane control;

    /** Creates a new ForwardingPlane instance
     *@param control the control plane connected to this forwarding plane
     *@param ports the number of interfaces
     */
    public ForwardingPlane(ControlPlane control, int ports) {
        this.control = control;
        interfaces = new HashMap<String,Interface>();
        for(int i=0; i<ports; i++) {
            String id="iface"+i;
            interfaces.put(id,new Interface(id,this,control));
        }
    }
}

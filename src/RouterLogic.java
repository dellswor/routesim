/** Interface for routing algorithm implementations */
public interface RouterLogic {
    /** Sets the routing table that this instance of routing logic manipulates
     *@param rt the routing table instance to manipulate
     */
    public void setRoutingTable(RoutingTable rt);

    /** Informs the RouterLogic instance about interface configuration
     *@param iface information about an interface the router logic manages
     */
    public void ifaceUpdate(IfaceUpdate iface);

    /** Delivers a packet destined to the routing logic
     *@param pkt the packet to process
     */
    public void receive(Packet pkt);
}

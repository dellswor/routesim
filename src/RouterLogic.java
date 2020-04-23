/** Interface for routing algorithm implementations */
public interface RouterLogic {
    /** Sets the router this logic is running on
     *  <p>Needed so that the algorithm can send packets...
     *@param r The router instance
     */
    public void setRouter(Router r);

    /** Sets the routing table that this instance of routing logic manipulates
     *@param rt the routing table instance to manipulate
     */
    public void setRoutingTable(RoutingTable rt);

    /** Informs the RouterLogic instance about interface state change
     *@param iface The interface
     *@param alive Is this interface alive
     */
    public void ifaceStateChange(String iface, boolean alive);

    /** Informs the RouterLogic instance about an interface's static route
     *@param iface The interface
     *@param addr  The interface's address
     *@param net   The statically configured network for this interface
     *@param mask  The bitmask for the network
     */
    public void ifaceAddrNetSet(String iface, int addr, int net, int mask);

    /** Delivers a packet destined to the routing logic
     *@param pkt the packet to process
     */
    public void receive(Packet pkt);
}

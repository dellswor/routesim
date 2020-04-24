import java.util.ArrayList;

/** Interface for routing algorithms */
public interface Algorithm {
    /** Sets the node this algorithm is running on 
     *@param n the node running the algorithm instance
     */
    public void setNode(Node n);

    /** Set neighbors 
     *@param nbrs a list of neigbors connected by a single link
     */
    public void setNeighbors(ArrayList<Node> nbrs);

    /** Processes a packet containing routing information 
     *@param pkt the packet with data for this router
     */
    public void routeData(RoutingPacket pkt);

    /** Determines the next hop for a packet being routed 
     *@param pkt the packet we want the next hop for
     *@return the next router on the path
     */
    public Node route(Packet pkt);

    /** Notifies the algorithm that a simulation step has started 
     *  <p>For a basic distance vector protocol you may not need this
     */
    public void tick();

    /** Dumps the routing information to the console
     *  <p>Output should be in three columns; the first column should give
     *  the destination node, the second column should be next hop, and the
     *  third should be the estimated cost (number of hops). If there is an
     *  entry for which there is no route, the cost should be reported as 
     *  null
     */
    public void dumpRoutingTable();
}

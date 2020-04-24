import java.util.ArrayList;
/** Represents a node on the network */
public class Node {
    /** Routing algorithm implementation */
    private Algorithm algo;

    /** Address/identifier of this node */
    private String address;

    /** List of the nodes this node is connected to */
    private ArrayList<Node> neighbors;

    /** Create a new node instance 
     *@param id the name of the node
     *@param algo the routing algorithm instance to use
     */
    public Node(String id, Algorithm algo) {
        address = id;
        this.algo = algo;
    }

    /** Set neighbors 
     *@param nbrs the list of neighbors to use
     */
    public void setNeighbors(ArrayList<Node> nbrs) {
        neighbors = nbrs;
        algo.setNeighbors(nbrs);
    }

    /** Receive a packet with routing updates
     *@param pkt a routing update to process
     */
    public void recv(RoutingPacket pkt) {
        algo.routeData(pkt);
    }
    /** Receive a packet with data
     *@param pkt a data packet to process
     */
    public void recv(DataPacket pkt) {
        Node nextHop = algo.route(pkt);
        pkt.expire();
        Network.send(this,nextHop,pkt);
    }

    /** Broadcast a packet to transmit
     *@param pkt the packet to broadcast to neighbors
     */
    public void broadcast(Packet pkt) {
        for(Node n: neighbors) {
            Network.send(this, n, pkt);
        }
    }

    /** Dumps the routing table at this node */
    public void dumpTable() {
        algo.dumpRoutingTable();
    }

    /** Produces a string representation of this node */
    public String toString() {
        return address;
    }
}

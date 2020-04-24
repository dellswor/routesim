/** Class to represent routed packets on the network */
public class RoutingPacket extends Packet {
    /** Generates a new packet instance
     *@param s the source node for the packet (should match the sending router)
     *@param d the destination node for the packet (should be null for broadcast
     *@param p the payload
     *@param t the initial time to live
     */
    public RoutingPacket(Node s, Node d, Object p, int t) {
        super(s,d,p,t);
    }

    /** Method for a packet to deliver itself over a link
     *@param src the sending node
     *@param dst the receiving node
     */
    public final void process(Node src, Node dst) {
        dst.recv(this);
    }
}

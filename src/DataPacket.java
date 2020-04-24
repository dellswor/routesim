/** Class to represent routed packets on the network */
public class DataPacket extends Packet {
    /** Generates a new packet instance
     *@param s the source node for the packet
     *@param d the destination node for the packet
     *@param p the payload
     *@param t the initial time to live
     */
    public DataPacket(Node s, Node d, Object p, int t) {
        super(s,d,p,t);
    }

    /** Method for a packet to deliver itself to a node
     *@param src the sending node
     *@param dst the receiving node
     */
    public void process(Node src, Node dst) {
        dst.recv(this);
    }
}

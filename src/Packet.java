/** Abstract class to represent packets on the network */
public abstract class Packet {
    private final Object payload;
    private final Node src;
    private final Node dst;
    private int ttl;

    /** Creates a new packet with initial content
     *@param s the sender
     *@param d the receiver
     *@param p the payload/data
     *@param t the number of hops before the packet dies
     */
    public Packet(Node s, Node d, Object p, int t) {
        src = s;
        dst = d;
        payload = p;
        ttl = t;
    }

    /** Make packet deliver itself 
     *@param src the sending node
     *@param dst the recieving node
     */
    public abstract void process(Node src, Node dst);

    /** Get the payload */
    public Object getPayload() {
        return payload;
    }

    /** Get the TTL */
    public int getTTL() {
        return ttl;
    }

    /** Get the source */
    public Node getSrc() {
        return src;
    }

    /** Get the destination */
    public Node getDst() {
        return dst;
    }

    /** Method to reduce the ttl */
    public void expire() {
        ttl--;
    }
}

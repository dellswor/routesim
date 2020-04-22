/** Class that represents a packet traversing the network */
public class Packet {
    /** The address of the sender in the packet */
    public final Address  src;
    /** The address of the destination in the packet */
    public final Address  dst;
    /** A "port" number used to determine the service at the dst */
    public final int      dport;
    /** A "port" number used to determine the service at the src */
    public final int      sport;
    /** The payload of the packet */
    private final byte[]  data;

    /** Creates a new instance
     *@param src source address
     *@param sport source port
     *@param dst destination address
     *@param dport destination port
     *@param data the packet payload
     */
    public Packet(Address src, int sport, Address dst, int dport, byte[] data) {
        this.src = src;
        this.sport = sport;
        this.dst = dst;
        this.dport = dport;
        this.data = Array.copyOf(data);
    }

    /** Extract the data from a packet
     *  <p>Declaring this method as final means that implementing classes
     *  cannot override the method...
     */
    public final byte[] getData() {
        return Array.copyOf(data, data.length);
    }
}

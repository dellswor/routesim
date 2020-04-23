/** Entry in the routing table */
public final class TableEntry {
    /** Address mask to use for matching destination */
    private final int mask;
    /** Network to use for matching destination */
    private final int net;
    /** id of interface to send the packet out */
    private final String iface;

    /** Make a new table entry instance 
     *@param mask the bitmask for matching addresses
     *@param net the network this rule matches
     *@param iface the interface to send the packet to
     */
    public TableEntry(int mask, int net, String iface) {
        this.mask = mask;
        this.net  = net;
        this.iface = iface;
    }

    /** The interface to use if this packet matches
     *@param p the packet to match
     *@return the interface id
     */
    public String match(Packet p) {
        int dst = p.dst;
        if( (dst & mask) ^ net == 0 ) {
            return iface;
        }
        return null;
    }

    public boolean equals(Object o) {
        try {
            TableEntry e = (TableEntry)o;
            if(e.mask==mask && e.net==net && e.iface.equals(iface) ) {
                return true;
            }
        } catch(ClassCastException e) {
            return false;
        }
    }
    public int hashCode() {
        return iface.hashCode() ^ mask ^ net;
    }
}


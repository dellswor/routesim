import java.util.ArrayList;

/** Routing table used to match packets with interfaces/destinations */
public class RoutingTable {
    /** The size of the routing table */
    private final int size;

    /** The sequence of table entries to use for forwarding */
    ArrayList<TableEntry> entries;

    /** Creates a new routing table instance
     *@param size the maximum number of records that can be held in the table
     */
    public RoutingTable(int size) {
        this.size = size;
        entries = new ArrayList<TableEntry>();
    }

    /** Get the current table entries
     *@return the current entries in order
     */
    public TableEntry[] dumpTable() {
        TableEntry[] ret = new TableEntry[size];
        int i = 0;
        for(TableEntry e: entries) {
            ret[i] = e;
            i++;
        }
        return ret;
    }

    /** Injects an entry into the table
     *  <p>Injecting at an index past the end adds the entry to the end
     *@param e the entry to add
     *@param idx the index in the table to place the entry at
     */
    public void inject(TableEntry e, int idx) {
        if(idx>entries.size()) {
            entries.add(e);
        } else {
            entries.add(idx, e);
        }
        if(entries.size()>size) {
            entries.remove(size);
        }
    }

    /** Removes an entry from the table
     *@param idx index to remove from
     */
    public void remove(int idx) {
        if(idx>=entries.size()) {
            return;
        }
        entries.remove(idx);
    }

    /** Maximum number of entries this routing table can hold
     *@return the maximum number of entries
     */
    public int size() {
        return size;
    }

    /** Applies a packet to the routing table entries
     *@param p packet to find an entry for
     *@return the interface to route the packet to
     */
    public String apply(Packet p) {
        for(TableEntry e: entries) {
            String iface = e.match(p);
            if(iface!=null) {
                return iface;
            }
        }
        return null;
    }
}

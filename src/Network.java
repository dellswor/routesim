import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayDeque;

/** Representation of the network (poorly implemented singleton pattern) */
public class Network {
    private static Network me=new Network();
    private HashSet<Cable> cables;
    private HashSet<Node> nodes;
    private HashMap<Node,ArrayList<Node>> nbrs;
    private ArrayDeque<Event> events;
    private long tick;
    private boolean prepared;

    /** Create a new empty network */
    public Network() {
        nbrs = new HashMap<Node,ArrayList<Node>>(); 
        nodes = new HashSet<Node>();
        cables = new HashSet<Cable>();
        events = new ArrayDeque<Event>();
    }

    /** Configures the nodes on the network for simulation */
    public static void prepareNetwork() {
        me.prepared = true;
        for(Node n: me.nodes) {
            n.setNeighbors(me.nbrs.get(n));
        }
    }
    /** Adds a node to the network
     *@param n the new node to add
     */
    public static void addNode(Node n) {
        if(me.prepared) {
            throw new RuntimeException("Can't add nodes after simulation start");
        }
        me.nbrs.put(n, new ArrayList<Node>());
        me.nodes.add(n);
    }
    /** Adds a cable between two nodes on the network
     *@param a one node
     *@param b other node
     */
    public static void addCable(Node a, Node b) {
        if(me.prepared) {
            throw new RuntimeException("Can't add nodes after simulation start");
        }
        if(!me.nodes.contains(a)) { addNode(a); }
        if(!me.nodes.contains(b)) { addNode(b); }
        me.nbrs.get(a).add(b);
        me.nbrs.get(b).add(a);
        me.cables.add(new Cable(a,b));
    }

    /** Reporting method to determine how many messages are queued for delivery
     *@return the number of messages waiting in the network
     */
    public static int messagesInNetwork() {
        return me.events.size();
    }

    /** Reporting method to show the routing tables of all the nodes */
    public static void dumpTables() {
        for(Node n: me.nodes) {
            System.out.println(n+" -----------");
            n.dumpTable();
        }
    }

    /** Register a packet to be sent over a link
     *@param src sending node
     *@param dst receiving node
     *@param pkt the packet to transmit
     */
    public static void send(Node src, Node dst, Packet pkt) {
        // check that the send is over a cable that exists
        if(!me.cables.contains(new Cable(src,dst))) {
            System.out.println("Message transmission attempted between "+src+" and "+dst+" when so such link exists!");
            return;
        }
        me.events.add(new Event(src, dst, pkt) );
    }

    /** Process a simulation step
     *@return the number of packets transmitted in the step
     */
    public static int tick() {
        for(Node n: me.nodes) {
            n.tick();
        }
        int evs = me.events.size();
        for(int i=0; i<evs; i++) {
            Event e = me.events.removeFirst();
            e.pkt.process(e.src, e.dst);
        }
        System.out.println("processed "+evs+" packets in tick "+me.tick);
        me.tick++;
        return evs;
    }

    /** Structure for queued messages */
    private static class Event {
        public Node src;
        public Node dst;
        public Packet pkt;

        public Event(Node s, Node d, Packet p) {
            src = s;
            dst = d;
            pkt = p;
        }
    }
}

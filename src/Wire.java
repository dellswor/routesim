/** Represents a point to point wire between nodes */
public class Wire {
    /** interfaces connected by this wire */
    private Interface[] ifaces;
    /** Next time a packet can be sent */
    private long[] sendOk;
    /** Packets in flight */
    private Packet[] inflight;
    /** transmission rate, bps */
    private int rate;
    /** propegation delay */
    private int delayms;
    /** Is the wire cut? */
    private boolean cut;
    

    /** Creates a new wire with a given rate and delay
     *@param rate bandwidth of the link in bps
     *@param delay propegation delay in ms
     */
    public Wire(int rate, int delay) {
        this.rate = rate;
        this.delayms = delay;
        cut = true;
        ifaces = Interface[2];
        sendOk = long[2];
        inflight = Packet[2];
    }

    /** Connects this cable to an interface
     *@param i the interface to plug into
     */
    public void addEndpoint(Interface i) {
        if(ifaces[0]==null) {
            ifaces[0] = i;
        } else if(ifaces[1]==null) {
            ifaces[1] = i;
            cut = false;
        }
    }

    /** Attempt to process a packet
     *@param i the interface sending
     *@param p the packet to process
     */
    public void process(Packet p, Interface i) {
        if(ifaces[0]==i) {
            if(sendOk[0]<Simulator.now()) {
                inflight[0] = p;
                sendOk[0] = Simulator.now() + delayms + p.size()/rate;
            }
        } else if(ifaces[1]==i) {
            if(sendOk[1]<Simulator.now()) {
                inflight[1] = p;
                sendOk[1] = Simulator.now() + delayms + p.size()/rate;
            }
        }
    }

    /** Respond to a simulator clock tick */
    public void tick() {
        if(cut) { return; }
        if(inflight[0] != null && sendOk<Simulator.now()) {
            ifaces[1].receive(inflight[0]);
            inflight[0] = null;
        }
        if(inflight[1] != null && sendOk<Simulator.now()) {
            ifaces[0].receive(inflight[1]);
            inflight[1] = null;
        }
    }

    /** Cuts the wire */
    public void cut() {
        cut = true;
        sendOk = long[2];
        inflight = Packet[2];
    }
    /** Mends the wire */
    public void mend() {
        cut = false;
    }
    /** Ready to send for an interface? */
    public boolean ready(Interface i) {
        if(ifaces[0]==i && sendOk[0]<Simulator.now()) {
            return true;
        } else if(ifaces[1]==i && sendOk[1]<Simulator.now()) {
            return true;
        }
        return false;
    }
    /** Is the link alive? */
    public boolean alive() {
        return !cut;
    }
}

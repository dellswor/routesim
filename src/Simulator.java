/** the global simulator state */
public class Simulator {
    /** bad form to use a static variable rather than a singleton... but this
     *  will involve less object passing to complete the same job */
    private static long now=0;

    /** The wires in the network */
    private ArrayList<Wire> wires;

    public Simulator() {
        wires = new ArrayList<Wire>();
    }

    public static long now() { return now; }
    private static advanceTime(long ms) {
        now += ms;
        for(Wire w: wires) {
            w.tick();
        }
        for(Router r: routers) {
            r.tick();
        }
    }
}

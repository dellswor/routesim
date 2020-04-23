import java.util.ArrayList;

/** the global simulator state */
public class Simulator {
    /** bad form to use a static variable rather than a singleton... but this
     *  will involve less object passing to complete the same job */
    private static long now=0;

    /** The wires in the network */
    private ArrayList<Wire> wires;

    /** The routers in the network */
    private ArrayList<Router> routers;

    public Simulator() {
        wires = new ArrayList<Wire>();
        routers = new ArrayList<Router>();
    }

    public static long now() { return now; }
    private void advanceTime(long ms) {
        now += ms;
        for(Wire w: wires) {
            w.tick();
        }
        for(Router r: routers) {
            r.tick();
        }
    }
}

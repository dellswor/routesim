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
    public void add(Router r) { routers.add(r); }
    public void add(Wire w) { wires.add(w); }

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

    public static void main(String[] args) {
        Simulator sim = new Simulator();
        // Make the nodes and add to the simulation
        Router h1 = new Router("h1",1,new EchoLogic());
        Router h2 = new Router("h2",1,new EchoLogic());
        Router r1 = new Router("r1",2,new SimpleLogic());
        sim.add(h1);
        sim.add(h2);
        sim.add(r1);

        // Make the links and add to the simulation
        Wire h1r1 = new Wire(1500, 0);
        Wire h2r1 = new Wire(1500, 0);
        sim.add(h1r1);
        sim.add(h2r1);
        
        // wire the network
        h1.connect(h1r1, 0);
        r1.connect(h1r1, 0);
        h2.connect(h2r1, 0);
        r1.connect(h2r1, 1);

        // Run the simulator for 100 ms
        for(int i=0; i<100; i++) {
            sime.advanceTime(1);
        }
}

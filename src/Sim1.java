/** Dan's smoketest simulation */
public class Sim1 {
    public static void main(String[] args) {
        // Make three nodes all running their own copies of distance vector
        Algorithm a = new DEAlgorithm();
        Node n1 = new Node("A",a);
        a.setNode(n1);
        a = new DEAlgorithm();
        Node n2 = new Node("B",a);
        a.setNode(n2);
        a = new DEAlgorithm();
        Node n3 = new Node("C",a);
        a.setNode(n3);
       
        // Cable the nodes together in a line
        Network.addCable(n1,n2);
        Network.addCable(n2,n3);

        // Prepare the network for simulation
        Network.prepareNetwork();
        // Run 10 time steps to see how the routes change
        for(int i=0; i<10; i++) {
            System.out.println(i+": "+Network.messagesInNetwork());
            Network.dumpTables();
            Network.tick();
        }
    }
}

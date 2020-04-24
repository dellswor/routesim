/** Represents a connection between nodes */
public class Cable {
    // cable end points
    private Node n1;
    private Node n2;
    
    /** Creates a cable between two nodes
     *@param a one of the nodes
     *@param b the other node
     */
    public Cable(Node a, Node b) {
        n1=a;
        n2=b;
    }

    /** Computes a hash code so that instances can be used with hashing 
     *  data structures
     *@return an integer representation of the cable
     */
    public int hashCode() {
        return n1.hashCode() ^ n2.hashCode();
    }
    /** Overloading hashCode requires overloading equals to get correct
     *  behavior.
     *@param o the object to compare with
     *@return true if they represent the same cable
     */
    public boolean equals(Object o) {
        try {
            Cable c = (Cable)o;
            return (c.n1==n1 && c.n2==n2) || (c.n1==n2 && c.n2==n1);
        } catch (ClassCastException e) { }
        return false;
    }
}

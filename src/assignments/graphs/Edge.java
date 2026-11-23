package assignments.graphs;

public class Edge {
    public final int src; // Where edge starts.
    public final int tgt; // Where ends.

    /**
     * Construct an immutable edge, indicating a source vertex and a target vertex.
     * @param src the source vertex label
     * @param tgt the target vertex label
     */
    public Edge(int src, int tgt) {
        this.src = src; // Start vertex and end vertex.
        this.tgt = tgt;
    }

    /**
     * Two edges are equal if their source and target match.
     * 
     * @param o - the object to compare to
     * @return true iff o is also an edge, with the same source and target
     */
    public boolean equals(Object o) {
        if (!(o instanceof Edge)) { // If o isnt an edge.
            return false;
        }
        Edge edge = (Edge) o; // Convert o to edge;
        if (src == edge.src && tgt == edge.tgt) { // Checking if both values are the same.
            return true;
        }
        return false;
    }

    /**
     * A hashing function that respects the three criteria from java documentation:
     * 1. Consistent within a single program execution.
     * 2. Two equal edges give the same hash code.
     * 3. Two unequal edges give different hash codes as often as possible.
     * 
     * @return a hash
     */
    public int hashCode() {
        return 31 * src + tgt; // Returns hashcode. 31 because its the most popular prime number in java to reduce collisions.
    }
}
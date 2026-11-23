package assignments.graphs;

import java.util.Set;

public class AdjacencyMatrix {
    private boolean[][] matrix; // 2D array, stores edges: T/F.

    /**
     * Construct an AdjacencyList representation of a graph with vertices V and edges E.
     * @param V a set of vertices, labeled with integers
     * @param E a set of edges
     * 
     * NOTE: In this assignment, the vertices will always be labeled from 0 up to n
     * 
     */
    public AdjacencyMatrix(Set<Integer> V, Set<Edge> E) {
        int n = V.size(); // Number of vertices.
        this.matrix = new boolean[n][n]; // Creates matrix with n rows and col.
        for (Edge e: E) { // Go through every edge in E.
            this.matrix[e.src][e.tgt] = true; // True where edge exists.
        }
    }

    /**
     * Report whether or not this graph has a given edge.
     * @param e the Edge
     */
    public boolean hasEdge(Edge e) {
        return matrix[e.src][e.tgt]; // True if edge exists.
    }

    /**
     * Construct an AdjacencyMatrix representation of the graph G.
     * @param G the graph, as an AdjacencyList
     */
    public AdjacencyMatrix(AdjacencyList G) {
        this(G.getVertices(), G.getEdges()); //Use verticies and edges from G to create matrix.
    }

    /**
     * Return a set of vertices in this graph.
     * 
     * Each vertex is represented by an integer.
     * 
     * @return a set of vertices
     */
    public Set<Integer> getVertices() {
        Set<Integer> vertices = new java.util.HashSet<>(); // Create empty set for verticies.
        for (int i = 0; i < matrix.length; i++) { // Go through all vertices from 0 to n-1.
            vertices.add(i); // Add vertex to set.
        }
        return vertices;
    }

    /**
     * Return a set of Edges in this graph.
     * @return set of edges
     */
    public Set<Edge> getEdges() {
        Set<Edge> edges = new java.util.HashSet<>(); // Create empty set for edges.
        for (int i = 0; i < matrix.length; i++) { // Go through rows.
            for (int j = 0; j < matrix.length; j++) { // Go through columns.
                if (matrix[i][j]) { // If there is an edge.
                    edges.add(new Edge(i, j)); // Add edge.
                }
            }
        }
        return edges;
    }
}

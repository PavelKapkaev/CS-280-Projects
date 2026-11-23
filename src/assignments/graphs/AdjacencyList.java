package assignments.graphs;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class AdjacencyList {
    private List<List<Integer>> edges;

    /**
     * Construct an AdjacencyList representation of a graph with vertices V and edges E.
     * @param V a set of vertices, labeled with integers
     * @param E a set of edges
     * 
     * NOTE: In this assignment, the vertices will always be labeled from 0 up to n
     * 
     */
    public AdjacencyList(Set<Integer> V, Set<Edge> E) {
        int n = V.size();
        this.edges = new ArrayList<List<Integer>>(n);
        
        for (int i = 0; i < n; i ++) {
            this.edges.add(null); // Create a position in the list.
            this.edges.set(i, new ArrayList<Integer>()); // Then replace null withempty list.
        }

        for (Edge e: E) {
            this.edges.get(e.src).add(e.tgt);
        }
    }

    /**
     * Report whether or not this graph has a given edge.
     * @param e the Edge
     */
    public boolean hasEdge(Edge e) {
        return this.edges.get(e.src).contains(e.tgt);
    }

    /**
     * Construct an AdjacencyList representation of the graph G.
     * @param G the graph, as an AdjacencyMatrix
     */
    public AdjacencyList(AdjacencyMatrix G) {
        this(G.getVertices(), G.getEdges()); // List with verticies and edges from G.
    }

    /**
     * Return a set of vertices in this graph.
     * 
     * Each vertex is represented by an integer.
     * 
     * @return Set<Integer>
     */
    public Set<Integer> getVertices() {
        Set<Integer> vertices = new java.util.HashSet<>(); // Empty set.
        for (int i = 0; i < edges.size(); i++) { // Go through all vertex.
            vertices.add(i); // Add vertex to the set.
        }
        return vertices;
    }

    /**
     * Return a set of Edges in this graph.
     * @return Set<Edge>
     */
    public Set<Edge> getEdges() {
        Set<Edge> E = new java.util.HashSet<>(); 
        for (int i = 0; i < edges.size(); i++) { // Go through every vertex.
            for (int j : edges.get(i)) { // Through connected vertices. 
                E.add(new Edge(i, j)); // Add edge.
            }
        }
        return E;
    }


}
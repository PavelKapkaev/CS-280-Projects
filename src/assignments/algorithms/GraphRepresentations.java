package assignments.algorithms;

import assignments.graphs.*;
import java.util.*;

public class GraphRepresentations {
  public static void main(String[] args) {
    Set<Integer> V = new HashSet<>(); // Create vertices.
    V.add(0);
    V.add(1);
    V.add(2);
    Set<Edge> E = new HashSet<>(); // Create edges.
    E.add(new Edge(0, 1));
    E.add(new Edge(1, 2));
    AdjacencyMatrix adjMatrix = new AdjacencyMatrix(V, E); // Create matrix.
    AdjacencyList adjList = new AdjacencyList(V, E); // Create list.
    assert adjMatrix.getVertices().equals(V); // Matrix vertices.
    assert adjMatrix.getEdges().equals(E); // Matrix edges.
    assert adjList.getVertices().equals(V); // List vertices.
    assert adjList.getEdges().equals(E); // List edges.
    AdjacencyMatrix convMatrix = new AdjacencyMatrix(adjList); // List to matrix.
    AdjacencyList convList = new AdjacencyList(adjMatrix); // Matrix to list.
    assert convMatrix.getVertices().equals(V); // Test conversion.
    assert convMatrix.getEdges().equals(E);
    assert convList.getVertices().equals(V);
    assert convList.getEdges().equals(E);
    System.out.println("Graph representation passed all tests.");
  }
}
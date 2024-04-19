import java.util.Scanner;

/**
 * This class is an implementation of Dijkstra's Algorithm to find the shortest
 * path in a weighted graph.
 */
public class DijkstraAlgorithm {

  public static void main(String[] args) {
    // adjacency matrix representing weighted graph
    int[][] graph = {
        { 0, 53, 10, 12, 0, 0, 0, 0, 0, 0 },
        { 53, 0, 33, 0, 2, 0, 101, 0, 0, 0 },
        { 10, 33, 0, 9, 30, 18, 0, 0, 0, 0 },
        { 12, 0, 9, 0, 0, 17, 0, 0, 6, 0 },
        { 0, 2, 30, 0, 0, 14, 123, 122, 0, 0 },
        { 0, 0, 18, 17, 14, 0, 0, 137, 7, 0 },
        { 0, 101, 0, 0, 123, 0, 0, 8, 0, 71 },
        { 0, 0, 0, 0, 122, 137, 8, 0, 145, 66 },
        { 0, 0, 0, 6, 0, 7, 0, 145, 0, 212 },
        { 0, 0, 0, 0, 0, 0, 71, 66, 212, 0 }
    };

    // read user input
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Enter source node: ");
      int source = scanner.nextInt();

      // check if source node is within valid range
      if (source < 0 || source >= graph.length) {
        System.out.println("Invalid source node.");
        return;
      }

      // execute Dijkstra's algorithm
      dijkstra(graph, source);
    } catch (Exception e) {
      // handle errors gracefully
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * This method calculates the shortest path from the indicated source node to
   * all other nodes using Dijkstra's algorithm
   * 
   * @param graph      Weighted graph as adjacency matrix
   * @param sourceNode The node from which to find the shortest path
   */
  public static void dijkstra(int[][] graph, int sourceNode) {
    int numVertices = graph.length;
    // store shortest distance from source node to vertex
    int[] distance = new int[numVertices];
    // track whether a vertex was visited or not
    boolean[] visited = new boolean[numVertices];
    // store parent nodes in the shortest path
    int[] parents = new int[numVertices];

    // initialize distances and array
    for (int i = 0; i < numVertices; i++) {
      distance[i] = Integer.MAX_VALUE;
      visited[i] = false;
    }

    distance[sourceNode] = 0;

    // run algorithm
    for (int i = 0; i < numVertices - 1; i++) {
      // find the vertex with the shortest distance from the source node among the
      // unvisited vertices
      int u = minDistance(distance, visited);
      visited[u] = true;

      // update distances to adjacent vertices if a shorter path is found
      for (int v = 0; v < numVertices; v++) {
        if (!visited[v] && graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE &&
            distance[u] + graph[u][v] < distance[v]) {
          distance[v] = distance[u] + graph[u][v];
          parents[v] = u;
        }
      }
    }

    // print shortest paths
    printShortestPaths(distance, parents, sourceNode);
  }

  /**
   * This method finds the vertex with the minimum distance from the source node
   * among the vertices not yet visited.
   * 
   * @param distance Array that contains the shortest distances to each vertex
   * @param visited  Array that indicates whether each vertex has been visited or
   *                 not
   * @return Index of the vertex with the shortest distance
   */
  public static int minDistance(int[] distance, boolean[] visited) {
    // initialize the min distance to the max possible value
    int min = Integer.MAX_VALUE;
    // initialize the index of the vertex with min distance
    int minIndex = -1;

    // iterate through all vertices
    for (int v = 0; v < distance.length; v++) {
      // check if the vertex has not been visited and its distance is <= current min
      if (!visited[v] && distance[v] <= min) {
        // update min distance
        min = distance[v];
        // update index of the vertex with the shortest distance
        minIndex = v;
      }
    }

    return minIndex;
  }

  /**
   * This method prints the shortest paths from the source node to all other
   * nodes.
   * 
   * @param distance   Array containing the shortest distances to each vertex
   * @param parents    Array containing the parent nodes in the shortest paths
   * @param sourceNode The node from which the shortest paths are calculated
   */
  public static void printShortestPaths(int[] distance, int[] parents, int sourceNode) {
    System.out.println("Shortest paths from node " + sourceNode + " to all other nodes:");

    // iterate through all the vertices and print its shortest path
    for (int i = 0; i < distance.length; i++) {
      // skip printing for the source node
      if (i != sourceNode) {
        System.out.print("Node " + i + ": ");
        // print shortest path from the source node to the current node
        printPath(i, parents, sourceNode);
        // print distance of the shortest path
        System.out.println(" (Distance: " + distance[i] + ")");
      }
    }
  }

  /**
   * This method prints the shortest path from the source node to the given
   * desination node.
   * 
   * @param destination Destination node to print the path to
   * @param parents     Array containing the parent nodes in the shortest path
   * @param sourceNode  Node from which the shortest paths are calculated
   */
  public static void printPath(int destination, int[] parents, int sourceNode) {
    // if the parent of the destination is the source
    if (parents[destination] == sourceNode) {
      System.out.print(sourceNode + " " + destination + " ");
      return;
    }

    // print shortest path from source to the parent of the current destination
    printPath(parents[destination], parents, sourceNode);

    // print current destination after printing its parent
    System.out.print(destination + " ");
  }
}

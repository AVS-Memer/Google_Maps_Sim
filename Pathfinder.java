import java.util.*; 
import java.lang.*; 
import java.io.*; 

public class Pathfinder { 
  public static WeightedGraph<String, Double> graph;
  public static HashMap<String,Location> locations;
  public static PriorityQueue<Location> unvisited;
  public Pathfinder(WeightedGraph<String,Double> graph, HashMap<String,Location> l) {
    this.graph = graph;
    locations = l;
    unvisited = new PriorityQueue<>();
  }
  public static void findShortestPath(Location start, Location end) {
    for (Location i : locations.values()) {
      i.setDistance(Double.MAX_VALUE);
      i.setPrevious(null);
      unvisited.add(i);
    }
    start.setDistance(0.0);
    updateNeighborDistances(start);
    Location a;
    while (unvisited.contains(end)) {
      a = unvisited.poll();
      updateNeighborDistances(a);
    }
    System.out.printf("\nTotal Distance: %.2f miles\n", getTotalDistance(end));
    printPath(end);
    System.out.println();
  }
  public static void updateNeighborDistances(Location a) {
    for (String i : graph.getEdges(a.getName()).keySet()) {
      if (graph.getEdges(a.getName()).get(i) > 0) {
        if (unvisited.contains(locations.get(i))) {
          if (a.getDistance()+graph.getEdges(a.getName()).get(i) < locations.get(i).getDistance()) {
            locations.get(i).setDistance(a.getDistance()+graph.getEdges(a.getName()).get(i));
            locations.get(i).setPrevious(a);
            unvisited.remove(locations.get(i));
            unvisited.add(locations.get(i));
          }
        }
      }
    }
  }
  public static double getTotalDistance(Location l) {
    Location prev = l.getPrevious();
    if (prev == null) return 0;
    else {
      double distance = graph.getEdges(prev.getName()).get(l.getName());
      return distance+getTotalDistance(prev);
    }
  }
  public static void printPath(Location l) {
    Location prev = l.getPrevious();
    if (prev != null) {
      printPath(prev);
      System.out.print(prev.getName() + " --> " + l.getName());
      System.out.printf("(%.2f miles)\n",graph.getEdges(prev.getName()).get(l.getName()));
    }
  }
}

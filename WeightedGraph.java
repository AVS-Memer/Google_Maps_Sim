import java.util.*;

public class WeightedGraph<T, W> {
  private HashMap<T, HashMap<T,W>> map = new HashMap<>();
  private int numEdges = 0;
  
  public int getVertexCount() {
    return map.size();
  }
  public int getEdgesCount() {
    return numEdges;
  }
  public HashMap<T,W> getEdges(T vertex) {
    if (map.containsKey(vertex)) return map.get(vertex);
    else return new HashMap<>();
  }
  public boolean hasVertex(T vertex) {
    return map.containsKey(vertex);
  }
  public boolean hasEdge(T source, T destination) {
    return map.containsKey(source) && map.get(source).containsKey(destination);
  }
  public void addVertex(T vertex) {
    if (!map.containsKey(vertex)) map.put(vertex,new HashMap<>());
  }
  public void addEdge(T source, T destination) {
    addEdge(source,destination,null);
  }
  public void addEdge(T source, T destination, W weight) {
    addVertex(source);
    addVertex(destination);
    if (!map.get(source).containsKey(destination)) {
      map.get(source).put(destination, weight);
      numEdges++;
    }
  }
  @Override
  public String toString() {
    String r = "Graph:";
    for (T i : map.keySet()) {
      r += "\n"+i+":\n";
      for (T j : map.get(i).keySet()) r += "  "+j+"("+map.get(i).get(j)+")\n";
    }
    return r;
  }
}

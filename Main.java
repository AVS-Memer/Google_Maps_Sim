import java.io.*;
import java.util.*;

class Main {
  public static WeightedGraph<String, Double> graph;
  public static HashMap<String, Location> locations;
  public static Scanner scanner;
  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    locations = new HashMap<>();
    graph = new WeightedGraph<>();
    loadData();
    System.out.println("\nNetwork Loadeed!\nLoaded "+graph.getEdgesCount()+" roads.\nLoaded "+graph.getVertexCount()+" locations.\n\nLOCATIONS:");
    for (String i : locations.keySet()) System.out.println(i);
    while (true) runProgram();
  }
  public static void runProgram() {
    try {
      System.out.println();
      System.out.println("Enter a starting location: ");
      String locStart = scanner.nextLine();
      System.out.println("Enter an ending location: ");
      String locEnd = scanner.nextLine();
      Pathfinder p = new Pathfinder(graph,locations);
      if (locations.get(locStart) == null || locations.get(locEnd) == null) {
        System.out.println("Invalid Start or End Location.");
        return;
      }
      p.findShortestPath(locations.get(locStart), locations.get(locEnd));
    } catch (IllegalArgumentException e) {
      System.out.println("That location does not exist! Try again!");
    }
  }
  public static void loadData() {
    try {
      System.out.println("Please enter a file name: ");
      File file = new File(scanner.nextLine());
      Scanner fileScanner = new Scanner(file);
      while (fileScanner.hasNext()) parseTokens(fileScanner.nextLine());
      // every line contains 4 tokens:
      // 0: fromLat,fromLong
      // 1: toLat,toLong
      // 2: distance
      // 3: fromName -- toName
      fileScanner.close();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }
  public static void parseTokens(String line) {
    String[] t = line.split(" ");
    String[] name = t[3].split("--");
    String[] coords1 = t[0].split(",");
    String[] coords2 = t[1].split(",");
    locations.put(name[0],new Location(name[0],Double.parseDouble(coords1[0]),Double.parseDouble(coords1[1])));
    locations.put(name[1],new Location(name[1],Double.parseDouble(coords2[0]),Double.parseDouble(coords2[1])));
    graph.addEdge(name[0],name[1],Double.parseDouble(t[2]));
  }
  
}

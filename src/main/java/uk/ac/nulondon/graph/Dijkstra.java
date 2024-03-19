package uk.ac.nulondon.graph;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dijkstra extends ShortestPathFinder{

    public void calculate(String source, List<Vertex> graph) {
        distanceMap.clear();
        shortestPathTree.clear();
        Map<String, Vertex> vertexMap = graph.stream().collect(Collectors.toMap(Vertex::getName, Function.identity()));
        Set<String> q = new HashSet<>();

        //Initialization
        /*
        for each vertex v in Graph.Vertices:
           dist[v] ← INFINITY
           prev[v] ← UNDEFINED
           add v to Q
       dist[source] ← 0
         */

        for (Vertex vertex : graph) {
            distanceMap.put(vertex.getName(), Integer.MAX_VALUE);
            q.add(vertex.getName());
        }
        distanceMap.put(source, 0);

        //Main step
        /*
        while Q is not empty:
          u ← vertex in Q with min dist[u]
          remove u from Q

          for each neighbor v of u still in Q:
              alt ← dist[u] + Graph.Edges(u, v)
              if alt < dist[v]:
                  dist[v] ← alt
                  prev[v] ← u
         */

        while (!q.isEmpty()) {
            //Finding vertex in q with minimum distance
            int minDist = Integer.MAX_VALUE;
            String u = null;
            for (String s : q) {
                if (distanceMap.get(s) <= minDist) {
                    u = s;
                    minDist = distanceMap.get(s);
                }
            }
            q.remove(u);
            for (String v : vertexMap.get(u).getNeighbours().keySet()) {
                if (q.contains(v)) {
                    int alt = distanceMap.get(u) + vertexMap.get(u).getNeighbours().get(v);
                    if (alt < distanceMap.get(v)) {
                        distanceMap.put(v, alt);
                        shortestPathTree.put(v, u);
                    }
                }
            }
        }
    }


}

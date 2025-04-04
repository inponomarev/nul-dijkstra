package uk.ac.nulondon.graph;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dijkstra extends ShortestPathFinder {

    public boolean calculate(String source, List<Vertex> graph) {
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
        for (Vertex v : graph) {
            distanceMap.put(v.getName(), Integer.MAX_VALUE);
            q.add(v.getName());
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
            //find a vertex with the minimum distance
            int min = Integer.MAX_VALUE;
            String u = null;
            for (String vertName : q) {
                if (distanceMap.get(vertName) < min) {
                    min = distanceMap.get(vertName);
                    u = vertName;
                }
            }
            q.remove(u);

            Map<String, Integer> neighbours = vertexMap.get(u).getNeighbours();
            for (String neighbour : neighbours.keySet()) {
                if (q.contains(neighbour)) {
                    int alt = min + neighbours.get(neighbour);
                    if (alt < distanceMap.get(neighbour)) {
                        distanceMap.put(neighbour, alt);
                        shortestPathTree.put(neighbour, u);
                    }
                }
            }
        }

        return true;
    }


}

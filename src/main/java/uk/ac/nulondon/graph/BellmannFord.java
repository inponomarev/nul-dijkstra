package uk.ac.nulondon.graph;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BellmannFord extends ShortestPathFinder {

    @Override
    public void calculate(String source, List<Vertex> graph) {
        distanceMap.clear();
        shortestPathTree.clear();
        Map<String, Vertex> vertexMap = graph.stream().collect(Collectors.toMap(Vertex::getName, Function.identity()));

        for (Vertex vertex : graph) {
            distanceMap.put(vertex.getName(), Integer.MAX_VALUE);
        }
        distanceMap.put(source, 0);

        for (int i = 0; i < vertexMap.size() - 1; i++) {
            for (String u : vertexMap.keySet()) {
                for (String v : vertexMap.get(u).getNeighbours().keySet()) {
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

package uk.ac.nulondon.graph;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BellmannFord extends ShortestPathFinder {

    @Override
    public boolean calculate(String source, List<Vertex> graph) {
        distanceMap.clear();
        shortestPathTree.clear();
        Map<String, Vertex> vertexMap = graph.stream().collect(Collectors.toMap(Vertex::getName, Function.identity()));

        //Setup initial state
        for (Vertex vertex : graph) {
            distanceMap.put(vertex.getName(), Integer.MAX_VALUE);
        }
        distanceMap.put(source, 0);

        //Do |V|-1 relaxations
        for (int i = 0; i < graph.size(); i++) {
            for (Vertex vertex : graph) {
                int dist = distanceMap.get(vertex.getName());
                if (dist == Integer.MAX_VALUE) {
                    continue;
                }
                for (String n : vertex.getNeighbours().keySet()) {
                    int alt = dist + vertex.getNeighbours().get(n);
                    if (alt < distanceMap.get(n)) {
                        distanceMap.put(n, alt);
                        shortestPathTree.put(n, vertex.getName());
                    }
                }
            }
        }

        //Final check for negative loops
        for (Vertex vertex : graph) {
            for (String n : vertex.getNeighbours().keySet()) {
                if (distanceMap.get(vertex.getName()) + vertex.getNeighbours().get(n) <
                        distanceMap.get(n)) {
                    return false;
                }
            }
        }

        return true;

    }


}

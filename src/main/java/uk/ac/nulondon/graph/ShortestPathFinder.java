package uk.ac.nulondon.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ShortestPathFinder {
    final Map<String, Integer> distanceMap = new HashMap<>();
    final Map<String, String> shortestPathTree = new HashMap<>();
    abstract public boolean calculate(String source, List<Vertex> graph);

    public Map<String, Integer> getDistanceMap() {
        return distanceMap;
    }

    public Map<String, String> getShortestPathTree() {
        return shortestPathTree;
    }
}

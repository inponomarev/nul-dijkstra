package uk.ac.nulondon.graph;

import java.util.Map;

public class Vertex {
    private final String name;
    private final Map<String, Integer> neighbours;

    public Vertex(String name, Map<String, Integer> outgoing) {
        this.name = name;
        this.neighbours = outgoing;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getNeighbours() {
        return neighbours;
    }
}

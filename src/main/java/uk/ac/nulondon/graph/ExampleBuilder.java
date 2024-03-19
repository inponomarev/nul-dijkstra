package uk.ac.nulondon.graph;

import java.util.List;
import java.util.Map;

public class ExampleBuilder {
    public static List<Vertex> graph (){
        return List.of(
                new Vertex("A",
                        Map.of("B", 10, "C", 5)),
                new Vertex("B",
                        Map.of("C", 2, "D", 1)),
                new Vertex("C",
                        Map.of("B", 3, "D", 9, "E", 2)),
                new Vertex("D",
                        Map.of("E", 4)),
                new Vertex("E",
                        Map.of("A", 7, "D", 6))

        );
    }

    public static List<Vertex> graphWithNegativeEdges (){
        return List.of(
                new Vertex("A",
                        Map.of("B", 6, "C", 7)),
                new Vertex("B",
                        Map.of("C", 8, "D", 5, "E", -4)),
                new Vertex("C",
                        Map.of("D", -3, "E", 9)),
                new Vertex("D",
                        Map.of("B", -2)),
                new Vertex("E",
                        Map.of("A", 2, "D", 7))

        );
    }
}

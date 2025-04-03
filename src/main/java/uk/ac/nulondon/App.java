package uk.ac.nulondon;

import uk.ac.nulondon.graph.*;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Vertex> graph = ExampleBuilder.graphWithNegativeEdges();

        visualize(graph);

        ShortestPathFinder pathFinder = new BellmannFord();

        System.out.println(pathFinder.calculate("A", graph));

        visualize(graph, pathFinder.getDistanceMap(), pathFinder.getShortestPathTree());
    }

    public static void visualize(List<Vertex> graph) throws IOException, URISyntaxException {
        visualize(graph, Map.of(), Map.of());
    }

    public static void visualize(List<Vertex> graph,
                                 Map<String, Integer> distanceMap,
                                 Map<String, String> shortestPathTree) throws IOException, URISyntaxException {
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw)) {
            pw.println("digraph G {");
            pw.println("  rankdir=\"LR\";");
            for (Vertex vertex : graph) {
                if (distanceMap.containsKey(vertex.getName())) {
                    pw.printf("  %s[label=\"%s\\n%s\"];%n", vertex.getName(),
                            vertex.getName(), distanceMap.get(vertex.getName()));
                } else {
                    pw.printf("  %s;%n", vertex.getName());
                }
                for (Map.Entry<String, Integer> e : vertex.getNeighbours().entrySet()) {
                    if (vertex.getName().equals(shortestPathTree.get(e.getKey()))) {
                        pw.printf("   %s->%s[label=%s,color=red];%n",
                                vertex.getName(), e.getKey(), e.getValue());
                    } else {
                        pw.printf("   %s->%s[label=%s];%n",
                                vertex.getName(), e.getKey(), e.getValue());
                    }
                }
            }
            pw.println("}");
            pw.flush();
            show(sw.toString());
        }
    }

    private static void show(String dot) throws IOException, URISyntaxException {
        String encoded = URLEncoder.encode(dot, "UTF8")
                .replaceAll("\\+", "%20");
        Desktop.getDesktop().browse(
                new URI("https://dreampuf.github.io/GraphvizOnline/#"
                        + encoded));
    }
}

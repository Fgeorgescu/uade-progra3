package TPO.utils;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class GraphFactory {

    /**
     * <a href="{@docRoot}/assets/graphs/Graph1.png"> Image </a>
     * @return Graph1
     */
    public static Graph<String, DefaultWeightedEdge> generateGraph1() {
        Graph<String, DefaultWeightedEdge> g = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        // Define vertex as constants to avoid typos
        final String v1 = "v1";
        final String v2 = "v2";
        final String v3 = "v3";
        final String v4 = "v4";

        // Add the vertices to our graph
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);


        // Add edges and weights. Default weight is 1.0
        g.addEdge(v1,v2);
        g.setEdgeWeight(v1, v2, 10.0);

        g.addEdge(v2, v1);
        g.setEdgeWeight(v2, v1, 10.0);

        g.addEdge(v2, v3);
        g.setEdgeWeight(v2, v3, 10.0);

        g.addEdge(v3, v4);
        g.setEdgeWeight(v3, v4, 10.0);

        g.addEdge(v4, v1);
        g.setEdgeWeight(v4, v1, 10.0);


        return g;
    }
}

package TPO.utils;

import TPO.graphImpl.GrafosTDA;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.Random;

public class GraphFactory {

    private static final int MAX_DIM = 10;
    private static final int MAX_WEIGTH = 100; // Binario, o se conecta o no
    private static final int RANDOMIZER_ITERATIONS = 50;

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


    /**
     * Se numeran los vertices de 1 a MAX_DIM
     * @param g
     */
    public static void randomizeGraph(GrafosTDA g) {
        Random r = new Random();

        g.inicializarGrafo(MAX_DIM);
        for (int i = 0 ; i < MAX_DIM; i++) {
            g.agregarVertice(i);
        }

        for ( int i = 0; i < RANDOMIZER_ITERATIONS ; i++) {
            g.agregarArista(r.nextInt(MAX_DIM), r.nextInt(MAX_DIM),r.nextInt(MAX_WEIGTH));
        }
    }
}

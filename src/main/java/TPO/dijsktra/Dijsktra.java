package TPO.dijsktra;

import TPO.utils.GraphFactory;
import TPO.utils.LoggerFactory;
import TPO.visualization.JGraphXAdapterDemo;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;
import java.lang.management.PlatformLoggingMXBean;
import java.util.*;
import java.util.logging.Logger;

public class Dijsktra extends JApplet{

    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);


    private static final Logger logger = LoggerFactory.getLogger(Dijsktra.class.getSimpleName());

    public void execute(String source) {
        logger.info("Iniciamos la búsqueda del camino mínimo con ");
        Graph<String, DefaultWeightedEdge> stringGraph = GraphFactory.generateGraph1();

        logger.info("Tenemos el grafo: " + stringGraph.toString());

        Queue<String> q = new ArrayDeque<>();

        logger.finer("Inicializamos las estructuras auxiliares, distancia y previo");
        Map<String, Double> dist = new HashMap<>(); //Hashmap does not allow duplicated keys,
        Map<String, String> prev = new HashMap<>();

        logger.finer("Buscamos todos los vértices");
        Set<String> verticesSet = stringGraph.vertexSet();
        logger.info("Los vertices son: " + verticesSet.toString());

        verticesSet.forEach((vertice -> {
            logger.finer("Agregamos " + vertice + " a la cola, y ponemos los valores -1 y \"\" en distancia y previo");
            q.add(vertice);
            dist.put(vertice, -1.0);
            prev.put(vertice, "");
        }));

        logger.info("Definimos la distancia a nuestro origen " + source + " como 0.0");
        dist.replace(source, 0.0);


        while (!q.isEmpty()) {
            String verticeActual = q.poll(); // gets the element and removes from queue

            logger.fine("Como tenemos el vertice " + verticeActual + " en la cola, analizamos las aristas salientes");
            Set<DefaultWeightedEdge> aristasSalientes = stringGraph.outgoingEdgesOf(verticeActual);
            logger.fine("Las aristas salientes son: " + aristasSalientes.toString());

            aristasSalientes.forEach(saliente -> {
                logger.fine("Analizamos la arista " + saliente.toString());
                String destino = stringGraph.getEdgeTarget(saliente);
                double pesoArista = stringGraph.getEdgeWeight(saliente);
                logger.fine(String.format("La distancia de %s a %s es de %f", verticeActual, destino, pesoArista));

                double pesoCamino = dist.get(verticeActual) + pesoArista;

                if (pesoCamino < dist.get(verticeActual)) {
                    dist.put(verticeActual, pesoArista);
                    prev.put(stringGraph.getEdgeTarget(saliente), verticeActual);
                }
            }); // https://stackoverflow.com/questions/30876939/getting-all-edges-going-out-from-a-node-in-jgrapht
        }

        logger.info(dist.toString());
        logger.info(prev.toString());
    }
}

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

    private static final Double INFINITO = -1.0;

    private static final Logger logger = LoggerFactory.getLogger(Dijsktra.class.getSimpleName());

    public void execute(String source) {
        logger.info("Iniciamos la búsqueda del camino mínimo con Dijkstra");
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
            dist.put(vertice, INFINITO);
            prev.put(vertice, "");
        }));

        logger.info("Definimos la distancia a nuestro origen " + source + " como 0.0");
        dist.replace(source, 0.0);


        while (!q.isEmpty()) {
            String verticeActual = q.poll(); // gets the element and removes from queue

            logger.fine("Como tenemos el vertice " + verticeActual + " en la cola, analizamos las aristas salientes");
            Set<DefaultWeightedEdge> aristasSalientes = stringGraph.outgoingEdgesOf(verticeActual);
            logger.fine("Las aristas salientes son: " + aristasSalientes.toString());

            // Analizando todas las aristas salientes, determino los pesos para llegar a los destinos de las mismas
            aristasSalientes.forEach(aristaSaliente -> {
                logger.fine("Analizamos la arista " + aristaSaliente.toString());

                //Calculo el destino y peso de la arista
                String destino = stringGraph.getEdgeTarget(aristaSaliente);
                double pesoArista = stringGraph.getEdgeWeight(aristaSaliente);
                logger.fine(String.format("El peso de la arista (%s:%s) es de %f, y el peso hasta %s es %f",
                        verticeActual,
                        destino,
                        pesoArista,
                        verticeActual,
                        dist.get(verticeActual))
                );

                double pesoCaminoHastaDestino = dist.get(verticeActual) + pesoArista;
                 logger.fine(String.format("El peso acumulado desde el origen pasando por (%s:%s) sería %f",
                         verticeActual,
                         destino,
                         pesoCaminoHastaDestino)
                 );

                // Si el peso hasta el destino, reemplazo el peso y el antecesor por los valores correspondientes
                // Peso == -1
                 if (dist.get(destino).equals(INFINITO) || pesoCaminoHastaDestino < dist.get(destino)) {
                    logger.fine(String.format("El nuevo peso para %s es mejor que el existente (%f < %s)",
                            destino,
                            pesoCaminoHastaDestino,
                            dist.get(destino).equals(INFINITO) ? "Infinito" : dist.get(destino))
                    );

                    logger.fine(String.format("Actualizamos el peso hasta el vertice %s por %f",
                            destino,
                            pesoCaminoHastaDestino)
                    );

                    dist.put(destino, pesoCaminoHastaDestino);
                    logger.fine(String.format("Actualizamos el previo del destino %s por el vertice actual %s",
                            destino,
                            verticeActual)
                    );

                    prev.put(stringGraph.getEdgeTarget(aristaSaliente), verticeActual);
                } else {
                     logger.fine(String.format("El nuevo peso para %s es peor que el existente (%f > %s)",
                             destino,
                             pesoCaminoHastaDestino,
                             dist.get(destino).equals(INFINITO) ? "Infinito" : dist.get(destino))
                     );

                 }
            }); // https://stackoverflow.com/questions/30876939/getting-all-edges-going-out-from-a-node-in-jgrapht
        }

        logger.info(dist.toString());
        logger.info(prev.toString());
    }
}

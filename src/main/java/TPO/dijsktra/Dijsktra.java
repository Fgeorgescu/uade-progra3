package TPO.dijsktra;

import TPO.utils.GraphFactory;
import TPO.utils.LoggerFactory;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.util.*;
import java.util.logging.Logger;

public class Dijsktra {

    private static final Double INFINITO = -1.0;
    private static final String DESCONOCIDO = "unknown";

    private static final Logger logger = LoggerFactory.getLogger(Dijsktra.class.getSimpleName());

    public void execute() {

        logger.info("Iniciamos la búsqueda del camino mínimo con Dijkstra");
        logger.info("Recuerde que puede cambiar el origen o en grafo tocando el código (Menú NTH)");

        String source = "v1"; // Origen del algoritmo, debe existir en el grafo
        // Grafo a usar, pueden ver assets/graphs/Dijkstra para buscar las imágenes
        Graph<String, DefaultWeightedEdge> stringGraph = GraphFactory.dijkstraGraph2();

        logger.info("Tenemos el grafo: " + stringGraph.toString());

        // Utilizamos el iterador de la librería JGraph para recorrer el grafo por un BFS
        BreadthFirstIterator<String, DefaultWeightedEdge> iterator = new BreadthFirstIterator<>(stringGraph, source);

        Queue<String> q = new ArrayDeque<>();

        logger.finer("Inicializamos las estructuras auxiliares, distancia y previo");
        Map<String, Double> distanciaAcumulada = new HashMap<>(); //Hashmap does not allow duplicated keys,
        Map<String, String> prev = new HashMap<>();

        logger.finer("Buscamos todos los vértices");
        Set<String> verticesSet = stringGraph.vertexSet();
        logger.info("Los vertices son: " + verticesSet.toString());

        while (iterator.hasNext()) {
            String vertice = iterator.next();
            q.add(vertice);
            distanciaAcumulada.put(vertice, INFINITO);
            prev.put(vertice, DESCONOCIDO);
        }

        logger.info("Definimos la distancia a nuestro origen " + source + " como 0.0");
        distanciaAcumulada.replace(source, 0.0);
        prev.replace(source, "origen (" + source + ")");

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
                        distanciaAcumulada.get(verticeActual))
                );

                double pesoCaminoHastaDestino = distanciaAcumulada.get(verticeActual) + pesoArista;
                 logger.fine(String.format("El peso acumulado desde el origen pasando por (%s:%s) sería %f",
                         verticeActual,
                         destino,
                         pesoCaminoHastaDestino)
                 );

                // Si el peso hasta el destino, reemplazo el peso y el antecesor por los valores correspondientes
                // Peso == -1
                 if (distanciaAcumulada.get(destino).equals(INFINITO) || pesoCaminoHastaDestino < distanciaAcumulada.get(destino)) {
                    logger.fine(String.format("El nuevo peso para %s es mejor que el existente (%f < %s)",
                            destino,
                            pesoCaminoHastaDestino,
                            distanciaAcumulada.get(destino).equals(INFINITO) ? "Infinito" : distanciaAcumulada.get(destino))
                    );

                    logger.fine(String.format("Actualizamos el peso hasta el vertice %s por %f",
                            destino,
                            pesoCaminoHastaDestino)
                    );

                     distanciaAcumulada.put(destino, pesoCaminoHastaDestino);
                    logger.fine(String.format("Actualizamos el previo del destino %s por el vertice actual %s",
                            destino,
                            verticeActual)
                    );

                    prev.put(stringGraph.getEdgeTarget(aristaSaliente), verticeActual);
                } else {
                     logger.fine(String.format("El nuevo peso para %s es peor que el existente (%f > %s)",
                             destino,
                             pesoCaminoHastaDestino,
                             distanciaAcumulada.get(destino).equals(INFINITO) ? "Infinito" : distanciaAcumulada.get(destino))
                     );

                 }
            }); // https://stackoverflow.com/questions/30876939/getting-all-edges-going-out-from-a-node-in-jgrapht
        }

        logger.info("Distancias acumuladas partiendo de " +source+ ": " + distanciaAcumulada.toString());
        prev.forEach((k,v) -> {
            if (v.contains("origen")) logger.info("Origen: " + k);
            else logger.info(v + " -> " + k);
        });

    }
}

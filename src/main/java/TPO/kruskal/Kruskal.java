package TPO.kruskal;

import TPO.graphImpl.Arista;
import TPO.graphImpl.GrafoEstatico;
import TPO.utils.GraphFactory;
import TPO.utils.LoggerFactory;

import java.util.*;
import java.util.logging.Logger;

public class Kruskal {

    private static final Logger logger = LoggerFactory.getLogger("Kruskal");

    public void execute() {
        logger.info("Iniciamos la ejecución del algoritmo de Kruskal.");

        List<String> aristasSolucion = new ArrayList<>();

        GrafoEstatico g = GraphFactory.kruskalGraph1();
        logger.finer("Grafo generado");

        logger.finer("Inicializamos el set de soluciones");
        Conjuntos<Integer> conjuntos = new Conjuntos<>();

        logger.finer("Obtenemos los vértices del grafo para generar nuestro bosque");
        int[] bosque = g.vertices();

        logger.fine("Nuestro bosque es " + Arrays.toString(bosque));

        logger.finer("Obtenemos las aristas del grafo para generar nuestro array, las cuales ordenamos por peso (de menor a mayor)");
        List<Arista> aristas = g.aristas(); // O(n^2)
        logger.fine("Nuestras aristas sin ordenar son: " + aristas.toString());

        aristas.sort(Comparator.naturalOrder()); // n log(n)
        logger.fine("Nuestras aristas luego de ordenarlas son: " + aristas.toString());

        for(int vertice : bosque ){
            conjuntos.add(vertice); // agrego los elementos al conjunto.
        }

        logger.fine("Nuestro conjunto solución antes de procesar queda: " + conjuntos.toString());

        int i = 0; //Contador de las aristas

        while (!conjuntos.conjuntoUnico()) {
            logger.fine(String.format("Quedan %d conjuntos en la solución: %s.",
                    conjuntos.getConjuntos().size(),
                    conjuntos.toString())
            );

            if(!conjuntos.inSameGroup(aristas.get(i).origen, aristas.get(i).destino)) {// si mi arista no conecta nodos
                logger.fine(String.format("Los vértices %d y %d están en grupos distintos, los unimos.",
                        aristas.get(i).origen,
                        aristas.get(i).destino)
                );

                aristasSolucion.add(String.format("(%d:%d) - %d",
                        aristas.get(i).origen,
                        (aristas.get(i).destino),
                        aristas.get(i).peso)
                );

                conjuntos.joinGroups(aristas.get(i).origen, aristas.get(i).destino);
            } else {
                logger.fine(String.format("Los vértices %d y %d ya están unidos, avanzamos.",
                        aristas.get(i).origen,
                        aristas.get(i).destino)
                );
            }

            logger.fine(String.format("Luego de analizar, los conjuntos quedan: %s.",
                    conjuntos.toString())
            );

            i++;
        }

        logger.info("Las aristas que conforman la solución son: " + aristasSolucion);
    }
}
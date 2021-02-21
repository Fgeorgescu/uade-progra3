package TPO.kruskal;

import TPO.graphImpl.Arista;
import TPO.graphImpl.GrafoEstatico;
import TPO.graphImpl.GrafosTDA;
import TPO.utils.GraphFactory;
import TPO.utils.LoggerFactory;

import java.util.*;
import java.util.logging.Logger;

public class Kruskal {

    private static final Logger logger = LoggerFactory.getLogger("Kruskal");



    public void execute() {
        logger.info("Iniciamos la ejecución del algoritmo de Kruskal.");
        GrafoEstatico g = new GrafoEstatico();

        GraphFactory.randomizeGraph(g);
        logger.finer("Grafo generado");

        logger.finer("Inicializamos la lista de solución");
        List<Arista> solución = new LinkedList<>();

        logger.finer("Obtenemos los vértices del grafo para generar nuestro bosque");
        int[] bosque = g.vertices();

        logger.fine("Nuestro bosque es " + Arrays.toString(bosque));

        logger.finer("Obtenemos las aristas del grafo para generar nuestro array, las cuales ordenamos por peso (de menor a mayor)");
        List<Arista> aristas = g.aristas();
        logger.fine("Nuestras aristas sin ordenar son: " + aristas.toString());
        aristas.sort(Comparator.naturalOrder()); // n log(n)
        logger.fine("Nuestras aristas luego de ordenarlas son: " + aristas.toString());

        Set<Integer> conjuntos = new HashSet<>();

        for(int vertice : bosque ){
            conjuntos.add(vertice); // agrego los elementos al conjunto.
        }

        int i = 0;
        while (conjuntos.size() > 1) {
            if(conjuntos.contains(aristas.get(i).origen) && conjuntos.contains(aristas.get(i).destino)) {// si mi arista no conecta nodos
                   solución.add(aristas.get(i));
                   conjuntos.remove(aristas.get(i).origen);
                   conjuntos.remove(aristas.get(i).destino);
            }
        }
    }
}
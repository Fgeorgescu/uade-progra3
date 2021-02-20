package TPO.kruskal;

import TPO.graphImpl.Arista;
import TPO.graphImpl.GrafoEstatico;
import TPO.graphImpl.GrafosTDA;
import TPO.utils.LoggerFactory;

import java.util.*;
import java.util.logging.Logger;

public class Kruskal {

    private static final Logger logger = LoggerFactory.getLogger("Kruskal");

    private static final int MAX_DIM = 10;
    private static final int MAX_WEIGTH = 100; // Binario, o se conecta o no
    private static final int RANDOMIZER_ITERATIONS = 50;

    public void execute() {

        // TODO validar conexo
        GrafoEstatico g = new GrafoEstatico();

        randomizeGraph(g);

        List<Arista> solución = new LinkedList<>();


        int[] bosque = g.vertices();
        List<Arista> aristas = g.aristas();
        aristas.sort(Comparator.naturalOrder()); // n log(n)

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


    /**
     * Se numeran los vertices de 1 a MAX_DIM
     * @param g
     */
    private static void randomizeGraph(GrafosTDA g) {
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

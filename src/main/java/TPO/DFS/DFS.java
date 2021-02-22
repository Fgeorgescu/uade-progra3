package TPO.DFS;

import TPO.graphImpl.GrafoEstatico; //Nos la dio el profe
import TPO.graphImpl.GrafosTDA; //Nos la dio el profe
import TPO.utils.LoggerFactory; //La encontro Fran, arma el LOG

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class DFS {

    private static final Logger logger = LoggerFactory.getLogger("DFS");

    private int MAX_DIM = 10;
    private int MAX_WEIGTH = 100; // Binario, o se conecta o no
    private int RANDOMIZER_ITERATIONS = 50;

    // No modificar debajo de este comentario
    private final int BLANCO = 0;
    private final int GRIS = 1;
    private final int NEGRO = 2;

    private int[] marcas = new int[MAX_DIM];

  Map<Integer, Integer> p = new HashMap<>();
    private int[] d = new int [MAX_DIM];
    private int[] f = new int [MAX_DIM];
    private int t = 0;

    public DFS() {
    }

    public DFS(int MAX_DIM, int MAX_WEIGTH, int RANDOMIZER_ITERATIONS) {
        this.MAX_DIM = MAX_DIM;
        this.MAX_WEIGTH = MAX_WEIGTH;
        this.RANDOMIZER_ITERATIONS = RANDOMIZER_ITERATIONS; // Este metodo en valores al azar mete pesos al azar
    }

    public void execute() {
        logger.finest("Iniciamos la ejecución del algorítmo DFS");

        marcas = new int[MAX_DIM];
        d = new int [MAX_DIM];
        f = new int [MAX_DIM];
        t = 0;

        GrafosTDA g = new GrafoEstatico();

        randomizeGraph(g);

        logger.info(String.format("Inicia el algoritmo con %d nodos", g.vertices().length));


        logger.info("Mostramos la matriz: ");
        g.mostrarMatriz();
        try { // Sleep para que se imprima la matriz en consola
            sleep(100); //congela ejecución del codigo 100 milisegundos
        } catch (InterruptedException e) {
            e.printStackTrace(); //No debería romper
        }

        // Marcamos todos los nodos como BLANCO, y dejamos como -1 todos los predecesores
        for( int i = 0; i < g.vertices().length; i++) { //g.vertices te devuelve los vertices con length por tamaño
            logger.finest(String.format("Setteando como blanco y con predecesor -1 el nodo %d", i));
            marcas[i] = BLANCO;
            p.put(i, -1);
        }

        logger.fine("Iniciamos la iteración sobre el dfs_forest");
        for( int i = 0; i < g.vertices().length; i++) {
            logger.finest(String.format("Analizando el nodo %d en el loop de dfs forest", i));
            if (marcas[i] == BLANCO) {
                logger.fine(String.format("El nodo %d es BLANCO, vamos a profundizar el mismo. Llamando al método DFS", i));
                dfs(g, i); //Recursivo
            }
        }

        logger.info("Se analizaron todos los nodos, acá están los resultados:");
        logger.info("Para mostrar el recorrido, mostramos los origenes y las aristas: ");
        p.forEach((k,v) -> {
            if (v == -1) logger.info("Origen: " + k);
            else logger.info(v + " -> " + k);
        });
        logger.info("Tiempos iniciales de procesamiento de un nodo = " + Arrays.toString(d));
        logger.info("Tiempos de final de procesamiento de un nodo = " + Arrays.toString(f));

    }

    //
    private void dfs(GrafosTDA g, int origen) {
        logger.fine(String.format("Se inicia el método dfs recursivo para el nodo %d", origen));

        t++; //sumo el tiempo arranca en cero
        d[origen] = t;
        marcas[origen] = GRIS;

        logger.fine(String.format("Tiempo de inicio para el nodo %d: %d", origen, t));

        int[] ady = g.adyacentes(origen);

        logger.finest(String.format("Adyacentes para %d: %s. %s", origen, Arrays.toString(ady), "Estas son las etiquetas de los nodos adyacentes (excluidos los -1)."));

        for (int i = 0 ; i < ady.length && ady[i] != -1 ; i++) { // Con el 1er -1, sale ya que no hay más adyacentes
            int candidate = ady[i];

            logger.finer(String.format("Analizando el nodo %d, adyacente al nodo origen %d", candidate, origen));

            if(marcas[candidate] == BLANCO) {
                logger.finer(String.format("El candidato %d está marcado como blanco, vamos a profundizar", candidate));
                p.put(candidate, origen);
                dfs(g, candidate);
            }
        }

        marcas[origen] = NEGRO;
        t++;
        f[origen] = t;

        logger.fine(String.format("Tiempo de fin para el nodo %d: %d", origen, t));
    }

    /**
     * Se numeran los vertices de 1 a MAX_DIM
     * @param g
     */
    private void randomizeGraph(GrafosTDA g) {
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

package graphs.algorithm.search;

import graphs.GrafoEstatico;
import graphs.GrafosTDA;
import graphs.algorithm.Algorithms;

import java.util.Arrays;
import java.util.Random;

public class DFS implements Algorithms {
    private static final int MAX_DIM = 100;
    private static final int MAX_WEIGTH = 100; // Binario, o se conecta o no
    private static final int RANDOMIZER_ITERATIONS = 10;
    private static final int BLANCO = 0;
    private static final int GRIS = 1;
    private static final int NEGRO = 2;

    static int[] marcas = new int[MAX_DIM];
    static int[] p = new int [MAX_DIM];
    static int[] d = new int [MAX_DIM];
    static int[] f = new int [MAX_DIM];
    static int t = 0;


    @Override
    public boolean contains(GrafosTDA root, int target) {
        return false;
    }

    public static int[] test() { //DFS_FOREST

        GrafosTDA g = new GrafoEstatico();

        randomizeGraph(g);

        System.out.println("INICIO " + Arrays.toString(marcas));
        for( int i = 0; i < g.vertices().length; i++) {
            marcas[i] = BLANCO;
            p[i] = -1;
        }

        for( int i = 0; i < g.vertices().length; i++) {
            if (marcas[i] == BLANCO) {
                dfs(g, i);
            }
        }
        System.out.println("FIN " + Arrays.toString(marcas));
        System.out.println("Predecesores? = " + Arrays.toString(p));
        System.out.println("d? = " + Arrays.toString(d));
        System.out.println("t? = " + Arrays.toString(f));

        return p;
        //System.out.println(Arrays.toString(g.vertices()));
    }

    //
    private static void dfs(GrafosTDA g, int origen) {
        System.out.println("DFS + " + origen);
        t++;
        d[origen] = t;
        marcas[origen] = GRIS;
        int[] ady = g.adyacentes(origen);
        System.out.printf("Adyacentes para %d: %s\n", origen, Arrays.toString(ady));
        for (int i = 0 ; i < ady.length && ady[i] != -1 ; i++) { //Con el 1er -1, sale ya que no hay más adyacentes
            int candidate = ady[i];
            if(marcas[candidate] == BLANCO) {
                p[candidate] = origen;
                dfs(g, candidate);
            }
        }
        marcas[origen] = NEGRO;
        t++;
        f[origen] = t;

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

import graphs.GrafoEstatico;
import graphs.GrafosTDA;
import graphs.algorithm.search.BFS;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger("Main");

    public static void main(String[] args) {
        logger.info("Hello world");

        GrafosTDA grafo = new GrafoEstatico();
        BFS.search(grafo);
    }
}

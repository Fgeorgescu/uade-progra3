package TPO;

import TPO.graphs.algorithm.search.DFS;
import TPO.utils.LoggerFactory;
import TPO.visualization.JGraphXAdapterDemo;

import javax.swing.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class.getSimpleName());



    public static void main(String[] args) throws IOException {
        //showMenu();
        logger.info("in MyClass");
        logger.warning("a test warning");
        logger.fine("test");

        // DFS.test();
        // testVisualization();
    }

    private static void testVisualization() {
        JGraphXAdapterDemo applet = new JGraphXAdapterDemo();
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("JGraphT Adapter to JGraphX Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void showMenu() {
        String menu = """
                Ingrese el nro de la opción deseada:
                \tAlgoritmos disponibles:
                \t\t1) Algoritmo Breadth-First Search (BFS)
                \t\t2) Algoritmo Depth-First Search (DFS)
                \t\tnotImplemented - Algoritmo de Prim
                \t\tnotImplemented - Algoritmo de Kruskal
                \t\tnotImplemented - Algoritmo de Dijsktra
                \t\tnotImpelemnted - Algoritmo de Floyd
                
                \t\t0) Salir
                """;


        Scanner scan = new Scanner(System.in);
        int opt = -1;
        //Menu loop, se ejecuta hasta que ingresen 0
        do {
            System.out.println(menu);;

            try {
                opt = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error leyendo el input, vaciamos el buffer");
                scan.nextLine(); //vaciamos el buffer si hubo un error
            }

            switch (opt) {
                case 1 -> DFS.test();
                case 2 -> System.out.println("Implementación de BFS");
                case 0 -> {}
                case -1 -> System.out.println("Usá el nro de la opción como input, debe ser un int");
                default -> System.out.println("Input inválido. Por favor usar el número de la opción");
            }
        } while (opt != 0);
    }
}
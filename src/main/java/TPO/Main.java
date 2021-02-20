package TPO;

import TPO.DFS.DFS;
import TPO.dijsktra.Dijsktra;
import TPO.kruskal.Kruskal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        showMenu();
    }

    /**
     * Muestra el menú y corre el algoritmo seleccionado
     */
    private static void showMenu() {
        DFS dfs = new DFS();
        Kruskal kruskal = new Kruskal();
        Dijsktra dijkstra = new Dijsktra();



        String menu = """
                Ingrese el nro de la opción deseada:
                \tAlgoritmos disponibles:
                \t\t1) Algoritmo Depth-First Search (DFS)
                \t\t2) Algoritmo de Kruskal
                \t\t3) Algoritmo de Dijsktra
                
                \t\t0) Salir
                """;


        Scanner scan = new Scanner(System.in);
        int opt = -1;

        // Menu loop, se ejecuta hasta que ingresen 0
        do {
            System.out.println(menu);

            try {
                opt = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error leyendo el input, vaciamos el buffer");
                scan.nextLine(); //vaciamos el buffer si hubo un error
            }

            switch (opt) {
                case 1 -> dfs.execute();
                case 2 -> kruskal.execute();
                case 3 -> dijkstra.execute("v1");
                case 0 -> {}
                case -1 -> System.out.println("Usá el nro de la opción como input, debe ser un int");
                default -> System.out.println("Input inválido. Por favor usar el número de la opción");
            }
        } while (opt != 0);
    }
}
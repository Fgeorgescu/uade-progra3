package TPO.graphs.algorithm;

import TPO.graphs.GrafosTDA;

public interface Algorithms {
    /**
     * Interfaz que engloba el comportamiendo de un algoritmo de búsqueda
     * @param root
     * @param value
     * @return
     */
    boolean contains(GrafosTDA root, int value);
}

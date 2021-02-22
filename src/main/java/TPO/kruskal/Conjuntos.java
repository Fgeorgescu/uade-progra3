package TPO.kruskal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Conjuntos<T> {

    private List<Set<T>> conjuntos = new ArrayList<>(); // Usamos una List<Set<>> en lugar de un Set<Set<>> Pues el set no hace correctamente el remove en algunos casos, sospecho que por el equals

    public List<Set<T>> getConjuntos() {
        return conjuntos;
    }

    public List<Set<T>> add(T elemento) {
        // Si el elemento está en un set de solución, no lo agrego
        for(Set<T> set : conjuntos) {
            if (set.contains(elemento)) {
                return conjuntos;
            }
        }
        // Si no se encuentra en ningun set, lo agrego como un elemento único del set
        Set<T> nuevo = new HashSet<>();
        nuevo.add(elemento);
        conjuntos.add(nuevo);
        return conjuntos;
    }

    public boolean inSameGroup(T e1, T e2) {
        for(Set<T> set : conjuntos) {
            if (set.contains(e1)) { // Si está el primer elemento
                return set.contains(e2); // Valido que esté el segundo
            }
        }

        // Si el primer no se encuentra en ningun set, devuelvo falso
        return false;
    }

    public boolean joinGroups(T e1, T e2) {
        Set<T> set1 = null;
        Set<T> set2 = null;
        for(Set<T> set : conjuntos) {
            if (set.contains(e1)) { // Si está el primer elemento
                set1 = set; // lo guardo en aux. Esto es el puntero al set.
            }
            if (set.contains(e2)) {
                set2 = set;
            }
        }

        // Valido haber encontrado ambos sets para unir
        if (set1 == null || set2 == null) {
            return false;
        }

        // Uno y borro el set extra
        conjuntos.remove(set2);
        set1.addAll(set2);
        return true;
    }

    /**
     * Devuelve si tenemos un único conjunto en nuestro set de conjuntos
     * @return
     */
    public boolean conjuntoUnico() {
        return conjuntos.size() == 1;
    }

    @Override
    public String toString() {
        return conjuntos.toString();
    }
}
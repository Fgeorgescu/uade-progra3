package TPO.visualization;

import TPO.utils.LoggerFactory;

import java.util.*;
import java.util.logging.Logger;

public class Printer {

    private static Logger logger = LoggerFactory.getLogger("Printer");

    public static void printPreviosArray(List<Integer> array) {
        List<String> elementos = new LinkedList<>();

        for (int i = 0; i < array.size(); i++) {
            elementos.add(array.get(i), String.valueOf(i));
        }

        logger.info("Listado de secuencias: " + String.join(" -> ", elementos));
    }

    private static <K, V> Set<K> getOrigin(Map<K, V> map, V value) {
        Set<K> keys = new HashSet<>();

        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                keys.add(entry.getKey());
            }
        }

        return keys;
    }

}

package TPO.visualization;

import TPO.utils.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Printer {

    private static Logger logger = LoggerFactory.getLogger("Printer");

    public static void printPreviosArray(List<Integer> array) {
        LinkedList<Integer> elementos = new LinkedList<>();

        for (int i = 0; i < array.size(); i++) {
            elementos.add(array.get(i),i);
        }

        logger.info(elementos.toString());
    }
}

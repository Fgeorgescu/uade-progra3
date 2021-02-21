package TPO.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Example: https://www.logicbig.com/tutorials/core-java-tutorial/logging/levels.html
 *
 * Guardar logs en un archivo: https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
 */
public class LoggerFactory {

    public static Logger getLogger(String className) {
        InputStream stream = LoggerFactory.class.getClassLoader().
                getResourceAsStream("loggin.properties"); //Configurar logger

        try {
            LogManager.getLogManager().readConfiguration(stream);
            return Logger.getLogger(className);

        } catch (IOException e) {
            e.printStackTrace();
            return Logger.getLogger(className);
        }
    }
}

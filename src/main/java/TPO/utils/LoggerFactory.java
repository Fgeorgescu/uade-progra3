package TPO.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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

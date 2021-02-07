import graphs.algorithm.search.DFS;
import visualization.JGraphXAdapterDemo;

import javax.swing.*;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger("Main");

    public static void main(String[] args) {
        DFS.test();
        //testVisualization();
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
}

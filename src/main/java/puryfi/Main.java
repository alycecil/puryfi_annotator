package puryfi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());
    public static final String DESIRED_LOOK_AND_FEEL = "Nimbus";

    public static void main(String[] args) {
        setupLookandFeel();

        startApp();
    }

    private static void startApp() {
        EventQueue.invokeLater(() -> {
            APICaller apiCaller = new APICaller();
            ImageLoader imageLoader = new ImageLoader(apiCaller);
            NSWFAPI nswfapi = new NSWFAPI(imageLoader);
            nswfapi.setVisible(true);
        });
    }

    private static void setupLookandFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException var7) {
            System.out.println("Could not load system LookAndFeel, loading Java L&F \"Metal\"");

            try {
                UIManager.LookAndFeelInfo[] var2 = UIManager.getInstalledLookAndFeels();

                for (UIManager.LookAndFeelInfo info : var2) {
                    if (DESIRED_LOOK_AND_FEEL.equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException var6) {
                LOGGER.error("Error Occured", var7);
            }
        }
    }
}

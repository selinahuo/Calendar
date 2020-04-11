package views;

import java.awt.Desktop;
import java.net.URI;

/**
 * Class responsible for interacting with the system to open the default web browser
 */
public class BrowserIntegrations {
    /**
     * Open a URL in the system's default browser
     *
     * @param url url to open
     * @return whether url was opened successfully
     */
    public static boolean openWithBrowser(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}

package views;

import controller.Controller;
import views.general.AuthMenu;

/**
 * Class that manages views and supports navigation between biews
 */
public class ViewManager {
    private Controller controller;

    /**
     * Create a ViewManager
     *
     * @param controller injected controller dependency
     */
    public ViewManager(Controller controller) {
        this.controller = controller;
    }

    /**
     * Display views starting from the first view and injecting the controller
     */
    public void run() {
        View curr = new AuthMenu(new LocalStorage(),null, controller);
        while (curr != null) {
            curr = curr.run();
        }
    }
}

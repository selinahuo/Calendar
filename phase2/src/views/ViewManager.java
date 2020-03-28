package views;

import controller.Controller;
import views.auth.AuthMenu;

public class ViewManager {
    private Controller controller;

    public ViewManager(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        View curr = new AuthMenu(new LocalStorage(),null, this.controller);
        while (curr != null) {
            curr = curr.run();
        }
    }
}

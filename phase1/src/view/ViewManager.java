package view;

import controller.CommandLineController;
import view.views.SignInView;
import view.views.View;

public class ViewManager {
    private CommandLineController controller;

    public ViewManager(CommandLineController controller) {
        this.controller = controller;
    }

    public void run() {
        View curr = new SignInView(new LocalStorage(),null, this.controller);
        while (curr != null) {
            curr = curr.run();
        }
    }
}

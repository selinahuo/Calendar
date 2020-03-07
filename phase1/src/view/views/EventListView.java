package view.views;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import view.LocalStorage;

public class EventListView extends ListView {
    public EventListView(LocalStorage localStorage, ListModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        for (String event: super.getModel().getList()) {
            System.out.println(event);
        }
        // get new inputs
        // blah/ blah/ blah
        return null;
    }
}

package view.views;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import view.LocalStorage;

abstract public class ListView extends View {
    private ListModel model;

    public ListView(LocalStorage localStorage, ListModel model, CommandLineController controller) {
        super(localStorage, model, controller);
        this.model = model;
    }

    @Override
    public ListModel getModel() {
        return model;
    }
}

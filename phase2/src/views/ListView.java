package views;

import controller.Controller;
import controller.viewmodels.ListModel;

abstract public class ListView extends View {
    private ListModel model;

    public ListView(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
        this.model = model;
    }

    @Override
    public ListModel getModel() {
        return model;
    }

    public void printList() {
        for (String item: getModel().getList()) {
            System.out.println(item);
        }
        System.out.println("");
    }
}

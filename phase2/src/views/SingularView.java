package views;

import controller.Controller;
import controller.viewmodels.SingularModel;

abstract public class SingularView extends View {
    private SingularModel model;

    public SingularView(LocalStorage localStorage, SingularModel model, Controller controller) {
        super(localStorage, model, controller);
        this.model = model;
    }

    @Override
    public SingularModel getModel() { return model; }

    public void printSingular() {
        System.out.println(getModel().getEntityString());
        System.out.println("");
    }
}

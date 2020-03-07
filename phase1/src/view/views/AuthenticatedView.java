package view.views;

import controller.CommandLineController;
import controller.viewmodels.AuthenticatedModel;
import view.LocalStorage;

public abstract class AuthenticatedView extends View {
    private AuthenticatedModel model;

    public AuthenticatedView(LocalStorage localStorage, AuthenticatedModel model, CommandLineController controller) {
        super(localStorage, model, controller);
        this.model = model;
    }

    @Override
    public AuthenticatedModel getModel() {
        return this.model;
    }
}

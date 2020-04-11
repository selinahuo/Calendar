package views;

import controller.Controller;
import controller.viewmodels.SingularModel;

/**
 * Abstract view responsible for user output and input on single entity views
 */
abstract public class SingularView extends View {
    private SingularModel model;

    /**
     * Construct a SingularView
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model singular view model used by this view
     * @param controller controller the view interacts with
     */
    public SingularView(LocalStorage localStorage, SingularModel model, Controller controller) {
        super(localStorage, model, controller);
        this.model = model;
    }

    /**
     * Get this view's singular view model
     *
     * @return view's singular view model
     */
    @Override
    public SingularModel getModel() { return model; }

    /**
     * Print this view's singular entity's string representation from the view model
     */
    public void printSingular() {
        System.out.println(getModel().getEntityString());
        System.out.println("");
    }
}

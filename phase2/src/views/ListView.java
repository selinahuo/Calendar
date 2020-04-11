package views;

import controller.Controller;
import controller.viewmodels.ListModel;

/**
 * Abstract view responsible for user output and input on list of entity views
 */
abstract public class ListView extends View {
    private ListModel model;

    /**
     * Construct a ListView
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model list view model used by this view
     * @param controller controller the view interacts with
     */
    public ListView(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
        this.model = model;
    }

    /**
     * Get this view's list view model
     *
     * @return view's list view model
     */
    @Override
    public ListModel getModel() {
        return model;
    }

    /**
     * Print this view's list of entities as a string representation from the view model
     */
    public void printList() {
        for (String item: getModel().getList()) {
            System.out.println(item);
        }
        System.out.println("");
    }
}

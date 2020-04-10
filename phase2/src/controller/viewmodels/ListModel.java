package controller.viewmodels;

import java.util.ArrayList;

/**
 * A view model for representing a list of entities
 */
public class ListModel extends ViewModel {
    private ArrayList<String> list;

    /**
     * Construct an empty list model
     */
    public ListModel() {
        this.list = new ArrayList<>();
    }

    /**
     * Construct a list model with a list of entity strings
     *
     * @param list list of entity strings representing the list of entities
     */
    public ListModel(ArrayList<String> list) {
        this.list = list;
    }

    /**
     * Get the list of entity strings
     *
     * @return list of entity strings
     */
    public ArrayList<String> getList() {
        return list;
    }
}

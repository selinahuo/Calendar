package controller.viewmodels;

import java.util.ArrayList;

public class ListModel extends ViewModel {
    private ArrayList<String> list;

    public ListModel() {
        this.list = new ArrayList<>();
    }

    public ListModel(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}

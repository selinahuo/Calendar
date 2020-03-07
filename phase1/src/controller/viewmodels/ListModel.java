package controller.viewmodels;

public class ListModel extends ViewModel {
    private String[] list;

    public ListModel(String[] list) {
        this.list = list;
    }

    public String[] getList() {
        return list;
    }
}

package views.series;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;

public class GetSeriesView extends View {

    public GetSeriesView(LocalStorage localStorage, ViewModel model, Controller controller){
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        return null;
    }
}

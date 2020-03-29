package views.series;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.users.UserMenu;

import java.util.Scanner;

public class SeriesList extends ListView {
    public SeriesList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPromt(){
        System.out.println("Please select one of the following choices by entering a number:");
//        System.out.println("[1] View individual event");
        System.out.println("[~] Back to event menu");
    }


    @Override
    public View run() {
        return null;
    }
}

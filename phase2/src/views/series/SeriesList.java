package views.series;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.calendars.CalendarMenu;
import views.users.UserMenu;

import java.util.Scanner;

public class SeriesList extends ListView {
    public SeriesList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt(){
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[~] Back to series menu");
    }


    @Override
    public View run() {
        printTitle("Series List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            if ("~".equals(selection)) {
                return new SeriesMenu(getLocalStorage(), null, getController());
            } else {
                super.printInputError();
            }
        }
    }
}

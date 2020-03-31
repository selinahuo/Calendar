package views.calendars;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.events.EventMenu;

import java.util.Scanner;

public class CalendarList extends ListView {
    public CalendarList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[~] Back to Calendar menu");
    }

    @Override
    public View run() {
        printTitle("Calendar List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    return null;
                case "~":
                    return new CalendarMenu(getLocalStorage(), null, getController());
                default:
                    super.printInputError();
            }
        }
    }
}

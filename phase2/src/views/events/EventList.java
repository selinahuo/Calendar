package views.events;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.users.UserMenu;

import java.util.Scanner;

public class EventList extends ListView {
    public EventList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
//        System.out.println("[1] View individual event");
        System.out.println("[~] Back to event menu");
    }

    @Override
    public View run() {
        printTitle("Event List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    return null;
                case "~":
                    return new EventMenu(getLocalStorage(), null, getController());
                default:
                    super.printInputError();
            }
        }
    }
}

package views.events;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.general.MainMenu;
import views.users.UserList;

import java.util.Scanner;

public class EventMenu extends View {
    public EventMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create event");
        System.out.println("[2] Create event using first weekday of month");
        System.out.println("[3] List events by name");
        System.out.println("[4] List past/current/future events");
        System.out.println("[5] List events using time frame (hour, day, week, month)");
        System.out.println("[~] Return to main menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("User Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    return null;
                case "2":
                    return null;
                case "3":
                    System.out.println("Enter event name:");
                    String eventName = input.nextLine();
                    ListModel nameModel = getController().getEventsByName(eventName, getLocalStorage().getUserID());
                    return new EventList(getLocalStorage(), nameModel, getController());
                case "4":
                    // TODO Past, current, future events
                    return null;
                case "5":
                    // TODO using time frame
                    return null;
                case "~":
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }
}


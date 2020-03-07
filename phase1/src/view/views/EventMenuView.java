package view.views;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;

import java.util.Scanner;

public class EventMenuView extends View {
    public EventMenuView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following menus by typing the number:");
        System.out.println("[1] Get event by Name");
        System.out.println("[2] Get events (past/ongoing/future)");
        System.out.println("[3] Get event by date");
        System.out.println("[4] Create a new event");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Event Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Get event by name");
                    break;
                case "2":
                    return new EventGetEventView(super.getLocalStorage(), super.getModel(), super.getController());
                case "3":
                    System.out.println("get event by date");
                    break;
                case "4":
                    return new CreateEventView(super.getLocalStorage(), super.getModel(), super.getController());
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}

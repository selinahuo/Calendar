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
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get events by Name");
        System.out.println("[2] Get events by relative date (past/current/future)");
        System.out.println("[3] Create a new event");
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
                    return new GetEventByNameView(getLocalStorage(), getModel(), getController());
                case "2":
                    return new GetEventByRelativeDateView(getLocalStorage(), getModel(), getController());
                case "3":
                    return new CreateEventView(super.getLocalStorage(), super.getModel(), super.getController());
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}

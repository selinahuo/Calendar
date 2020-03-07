package view.views;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;

import java.util.Scanner;

public class CreateEventView extends View {
    public CreateEventView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Create Event");
        System.out.println("New event name:");
        String eventName = input.nextLine();
        System.out.println("Event start time (YYYY-MM-DD-HH-MM):");
        String start = input.nextLine();
        System.out.println("Event end time (YYYY-MM-DD-HH-MM):");
        String end = input.nextLine();
        System.out.println("Event location:");
        String location = input.nextLine();
        boolean success = super.getController().createEvents(eventName, start, end, location, getLocalStorage().getUserID());
        if (success) {
            System.out.println("Event was created successfully.");
        } else {
            super.printError("Something went wrong with event creation");
        }
        return new EventMenuView(super.getLocalStorage(), super.getModel(), super.getController());
    }
}

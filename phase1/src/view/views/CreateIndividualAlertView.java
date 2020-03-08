package view.views;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class CreateIndividualAlertView extends View {
    public CreateIndividualAlertView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Create Individual Alert");
        System.out.println("Event ID:");
        String eventID = input.nextLine();
        System.out.println("New alert Name:");
        String alertName = input.nextLine();
        System.out.println("Alert start time (YYYY-MM-DD-HH-MM):");
        String startTime = input.nextLine();
        System.out.println("User ID:");
        String userID = input.nextLine();
        boolean success = super.getController().CreateIndividualAlert(eventID, alertName, startTime,userID);
        if (success) {
            System.out.println("Event was created successfully.");
        } else {
            super.printError("Something went wrong with individual alert creation");
        }
        return new CreateAlertView(super.getLocalStorage(), super.getModel(), super.getController());
    }

}

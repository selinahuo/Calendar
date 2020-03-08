package view.views.alert;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.View;

import java.util.Scanner;

public class CreateFrequencyAlertView extends View {
    public CreateFrequencyAlertView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Create Frequency Alert");
        System.out.println("Event ID:");
        String eventID = input.nextLine();
        System.out.println("New alert Name:");
        String alertName = input.nextLine();
        System.out.println("Alert start time (YYYY-MM-DD-HH-MM):");
        String startTime = input.nextLine();
        System.out.println("Alert frequency (d - daily, w - weekly):");
        String frequency = input.nextLine();
        boolean success = super.getController().CreateFrequencyAlert(eventID, alertName,startTime,frequency, getLocalStorage().getUserID());
        if (success) {
            System.out.println("Event was created successfully.");
        } else {
            super.printError("Something went wrong with frequency alert creation");
        }
        return new AlertMenuView(super.getLocalStorage(), super.getModel(), super.getController());
    }
}

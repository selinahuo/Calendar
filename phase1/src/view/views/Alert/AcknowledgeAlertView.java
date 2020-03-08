package view.views.Alert;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.View;
import view.views.alert.AlertMenuView;
import view.views.event.EventMenuView;

import java.util.Scanner;

public class AcknowledgeAlertView extends View {


    public AcknowledgeAlertView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Acknowledge Alert");
        System.out.println("Alert ID");
        String alerID = input.nextLine();
        System.out.println("User ID");
        String userID = input.nextLine();

        boolean success = super.getController().acknowledgeAlert(alerID,userID);
        if (success) {
            System.out.println("Alert was acknowledged successfully.");
        } else {
            super.printError("Something went wrong ");
        }
        return new AlertMenuView(super.getLocalStorage(), super.getModel(), super.getController());
    }
}

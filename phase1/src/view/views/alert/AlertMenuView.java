package view.views.alert;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.View;

import java.util.Scanner;

public class AlertMenuView extends View {
    public AlertMenuView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get overdue alerts");
//        System.out.println("[2] Get Individual Alert");
        System.out.println("[3] Create a new alert");
        System.out.println("[4] Acknowledge an alert");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        super.printTitle("Alert Menu");
        Scanner input = new Scanner(System.in);
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    ListModel overdueModel = super.getController().getOverdueAlerts(getLocalStorage().getUserID());
                    if (overdueModel != null) {
                        return new AlertListView(getLocalStorage(), overdueModel, getController());
                    }
                    else {
                        super.printError("Something went wrong.");
                    }
                    break;
                case "2":
                    System.out.println("Under construction");
                    break;
                case "3":
                    return new CreateAlertView(getLocalStorage(), getModel(), getController());
                case "~":
                    return null;
                default:
                    super.printInputError();
            }
        }
    }

    // getOverdueAlerts
    //getIndividualAlert
    //createIndividualAlertOnEvent
    //createFrequencyAlertOnEvent
    //acknowledgeAlert




}

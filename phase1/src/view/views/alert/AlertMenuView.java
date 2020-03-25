package view.views.alert;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.HomeView;
import view.views.View;

import java.util.Scanner;

public class AlertMenuView extends View {
    public AlertMenuView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get overdue alerts");
        System.out.println("[2] Get Alert");
        System.out.println("[3] Create a new alert");
        System.out.println("[4] Acknowledge an alert");
        System.out.println("[5] Edit an alert");
        System.out.println("[6] Delete an alert");
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
                case "4":
                    return new AcknowledgeAlertView(getLocalStorage(), getModel(), getController());

                case "5":
                    System.out.println("click for edit alert ");
                    break;
                case "6":
                    System.out.println("click for alert deletion");
                    break;
                case "~":
                    return new HomeView(getLocalStorage(), null, getController());
                default:
                    super.printInputError();
            }
        }
    }
}

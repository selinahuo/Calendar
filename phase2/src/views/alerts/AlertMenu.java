package views.alerts;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.events.EventList;
import views.general.MainMenu;

import java.util.Scanner;

public class AlertMenu extends View{
    public AlertMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt(){
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get Overdue Alerts");
        System.out.println("[2] List Alerts");
        System.out.println("[3] Create a new Alert");
        System.out.println("[4] Acknowledge Alert");
        System.out.println("[5] Edit an Alert");
        System.out.println("[6] Delete an Alert");
        System.out.println("[~] Return to main menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Alert Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    ListModel overdueModel = getController().getOverdueAlerts(getLocalStorage().getUserID());
                    if (overdueModel != null) {
                        return new AlertList(getLocalStorage(), overdueModel, getController());
                    }
                    else {
                        super.printError("Oops, Something went wrong.");
                    }
                case "2":
                    ListModel alertModel = getController().getAlertsByUserID(getLocalStorage().getUserID());
                    return new AlertList(getLocalStorage(), alertModel, getController());
                case "3":
                    return new CreateAlertView(getLocalStorage(), getModel(), getController());
                case "4":
                    System.out.println("Enter alert ID:");
                    String alertID = input.nextLine();
                    boolean success = getController().acknowledgeAlert(alertID, getLocalStorage().getUserID());
                    if (success) {
                        System.out.println("Alert was acknowledged successfully.");
                    } else {
                        printError("Something went wrong, did NOT acknowledge the given alert.");
                    }
                    return new MainMenu(getLocalStorage(), null, getController());
                case "5":
                    return new EditAlertView(getLocalStorage(), getModel(),getController());
                case "6":
                    return null;
                case "~":
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }
}
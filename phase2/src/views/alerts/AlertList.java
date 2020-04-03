package views.alerts;
import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.SingularModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.alerts.AlertMenu;
import views.events.EventList;
import views.events.EventMenu;

import java.util.Scanner;

public class AlertList extends ListView {
    public AlertList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Acknowledge an Alert");
        System.out.println("[2] Edit Alert");
        System.out.println("[3] Delete Alert");
        System.out.println("[4] View event associated with alert");
        System.out.println("[~] Back to Alert menu");
    }

    @Override
    public View run() {
        printTitle("Alert List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch (selection) {
                case "1":
                    System.out.println("Enter alert ID:");
                    String alertID = input.nextLine();
                    boolean success = getController().acknowledgeAlert(alertID, getLocalStorage().getUserID());
                    if (success) {
                        System.out.println("Alert was acknowledged successfully");
                    } else {
                        printError("Something went wrong, did NOT acknowledge the given alert");
                    }
                    System.out.println("");
                    return new AlertMenu(getLocalStorage(),getModel(),getController());
                case "2":
                    System.out.println("");
                    return new EditAlertView(getLocalStorage(), getModel(),getController());
                case "3":
                    System.out.println("Please enter the ID of the alert you would like to delete: ");
                    String deleteAlertID = input.nextLine();
                    boolean deleted = getController().deleteAlertByID(deleteAlertID, getLocalStorage().getUserID());
                    if (deleted){
                        System.out.println("The alert was deleted");
                    } else {
                        System.out.println("The alert deletion was not complete");
                    }
                    System.out.println("");
                    return new AlertMenu(getLocalStorage(),getModel(),getController());
                case "4":
                    System.out.println("Please enter the alert ID to view the associated event:");
                    String eventAlertID = input.nextLine();
                    ListModel alertEvents = getController().getEventsFromAlert(eventAlertID, getLocalStorage().getUserID());
                    System.out.println("");
                    return new EventList(getLocalStorage(), alertEvents, getController());
                case "~":
                    System.out.println("");
                    return new AlertMenu(getLocalStorage(), getModel(), getController());
                default:
                    super.printInputError();
            }
        }
    }
}
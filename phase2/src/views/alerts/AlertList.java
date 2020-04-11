package views.alerts;
import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.events.EventList;

import java.util.Scanner;

/**
 * A view for display and handle the following options for showing list of alerts.
 * Functions including:
 * Showing this user's list of alerts, edit alert options, alert deletion, and acknowledge alert.
 */
public class AlertList extends ListView {
    public AlertList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Acknowledge an Alert");
        System.out.println("[2] Edit Alert Name");
        System.out.println("[3] Edit Individual Alert Time");
        System.out.println("[4] Delete Alert");
        System.out.println("[5] View event associated with alert");
        System.out.println("[~] Back to Alert menu");
    }

    /**
     * Displays a representation of a list of alerts.
     * Allows for following options to interact with entities:
     * edit alert options, alert deletion, and view alerts  or navigate back to the alert menu.
     *
     * @return Alert menu view.
     */
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
                    // Edit Alert Name
                    System.out.println("Enter Alert ID:");
                    String editAlertID = input.nextLine();
                    System.out.println("Enter the new name for this alert:");
                    String newName = input.nextLine();
                    boolean modified = getController().editAlertName(editAlertID, newName, getLocalStorage().getUserID());
                    if (modified){
                        System.out.println("The Alert is being modified:");
                        System.out.println(getController().getAlertByIDAndUserID(editAlertID,getLocalStorage().getUserID()));
                    } else {
                        printError("The Alert name change was not complete");
                    }
                    System.out.println("");
                    return new AlertMenu(getLocalStorage(),getModel(),getController());
                case "3":
                    // Edit Individual Alert Time
                    System.out.println("Enter Alert ID:");
                    String EditTimeAlertID = input.nextLine();
                    // The new alert time
                    System.out.println("Enter new alert start time (yyyy-mm-dd hh:mm), default - set to current time:");
                    String editStart = input.nextLine();
                    boolean changed = getController().editAlertTimeAsIndividual(EditTimeAlertID, editStart, getLocalStorage().getUserID());
                    if (changed) {
                        System.out.println("the new alert time is modified: ");
                        System.out.println(getController().getAlertByIDAndUserID(EditTimeAlertID,getLocalStorage().getUserID()));
                    } else {
                        printError("Something went wrong.");
                    }
                    System.out.println("");
                    return new AlertMenu(getLocalStorage(),getModel(),getController());
                case "4":
                    System.out.println("Please enter the ID of the alert you would like to delete: ");
                    String deleteAlertID = input.nextLine();
                    boolean deleted = getController().deleteAlertByID(deleteAlertID, getLocalStorage().getUserID());
                    if (deleted){
                        System.out.println("The alert was deleted");
                    } else {
                        printError("The alert deletion was not complete");
                    }
                    System.out.println("");
                    return new AlertMenu(getLocalStorage(),getModel(),getController());
                case "5":
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
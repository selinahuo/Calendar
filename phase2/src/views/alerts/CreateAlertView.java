package views.alerts;

import views.View;
import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.events.EventList;
import views.events.EventMenu;
import views.general.MainMenu;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * A view for display and handle the following options for create alert menu.
 * Functions including:
 * Create individual alert, and create frequency alerts.
 */
public class CreateAlertView extends View {
    /**
     * Constructor for a view that allows user to choose to create an alert instance.
     *
     * @param localStorage the local storage for current user
     * @param model view model
     * @param controller general controller that this view calls
     */
    public CreateAlertView(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create Individual Alert");
        System.out.println("[2] Create Frequency Alert");
        System.out.println("[~] Return to Alert Menu");
    }

    /**
     * Displays a representation of a alert creation menu.
     * Allows for following options to interact with entities: clip an calendar to clipboard,
     * create individual alert and frequency alert, or navigate back to the event menu.
     *
     * @return Alert menu view
     */
    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Create Alert View");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    printClipBoard();
                    // EventID
                    System.out.println("Enter Event ID, or enter [E] to go to Event Menu :");
                    String choice = input.nextLine();
                    if (choice.equals("E")) {
                        System.out.println("");
                        return new EventMenu(getLocalStorage(), null, getController());
                    } else {
                        // Alert Name
                        System.out.println("Enter new Alert name:");
                        String alertName = input.nextLine();
                        // Alert Time
                        System.out.println("Enter alert start time (yyyy-mm-dd hh:mm):");
                        String alertTime = input.nextLine();
                        // Creating the alert
                        System.out.println(getController().createIndividualAlert(choice, alertName, alertTime, getLocalStorage().getUserID()));
                    }
                    // return to this user's list of alerts
                    System.out.println("");
                    ListModel alertModel = getController().getAlertsByUserID(getLocalStorage().getUserID());
                    return new AlertList(getLocalStorage(), alertModel, getController());
                case "2":
                    printClipBoard();
                    // EventID
                    System.out.println("Enter Event ID, or enter [E] to go to Event Menu :");
                    String frequencyEventID = input.nextLine();
                    if (frequencyEventID.equals("E")) {
                        System.out.println("");
                        return new EventMenu(getLocalStorage(), null, getController());
                    } else {
                        // Alert Name
                        System.out.println("Enter new Alert name:");
                        String frequencyAlertName = input.nextLine();
                        // Frequency
                        System.out.println("Enter frequency of the alert, [d]ay, [w]eek, [h]our: ");
                        String frequency = input.nextLine();
                        // Alert Time
                        System.out.println("Enter alert start time of this frequency alert (yyyy-mm-dd hh:mm):");
                        String startTime = input.nextLine();
                        // Creating the alert
                        System.out.println(getController().createFrequencyAlert(frequencyEventID, frequencyAlertName,
                                getLocalStorage().getUserID(), startTime, frequency));
                    }
                    System.out.println("");
                    // return to this user's list of alerts
                    ListModel alertListModel = getController().getAlertsByUserID(getLocalStorage().getUserID());
                    return new AlertList(getLocalStorage(), alertListModel, getController());
                case "~":
                    System.out.println("");
                    return new AlertMenu(getLocalStorage(),getModel(),getController());

                default:
                    printInputError();
            }
        }
    }
}

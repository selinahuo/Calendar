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

public class CreateAlertView extends View {
    public CreateAlertView(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create Individual Alert");
        System.out.println("[2] Create Frequency Alert");
        System.out.println("[~] Return to Alert Menu");
    }

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
                    ListModel alertModel = getController().getAlertsByUserID(getLocalStorage().getUserID());
                    return new AlertList(getLocalStorage(), alertModel, getController());
                case "2":
                    printClipBoard();
                    // EventID
                    System.out.println("Enter Event ID, or enter [E] to go to Event Menu :");
                    String frequencyEventID = input.nextLine();
                    if (frequencyEventID.equals("E")) {
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
                    // return to this user's list of alerts
                    ListModel alertListModel = getController().getAlertsByUserID(getLocalStorage().getUserID());
                    return new AlertList(getLocalStorage(), alertListModel, getController());
                case "~":
                    return new AlertMenu(getLocalStorage(),getModel(),getController());

                default:
                    printInputError();
            }
        }
    }
}

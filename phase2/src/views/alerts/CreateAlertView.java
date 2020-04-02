package views.alerts;

import views.View;
import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.events.EventList;
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
                    // EventID
                    System.out.println("Enter Event ID:");
                    String eventID = input.nextLine();
                    // Alert Name
                    System.out.println("Enter new Alert name:");
                    String alertName = input.nextLine();
                    // Alert Time
                    System.out.println("Enter new time year:");
                    int year = input.nextInt();
                    System.out.println("Enter new time month:");
                    int month = input.nextInt();
                    System.out.println("Enter new time day:");
                    int day = input.nextInt();
                    System.out.println("Enter new time hour:");
                    int hour = input.nextInt();
                    System.out.println("Enter new time minute:");
                    int minute = input.nextInt();
                    input.nextLine();
                    System.out.println("");
                    LocalDateTime alertTime = getController().alertTimeFromInteger(year,month,day,hour,minute);
                    // Creating the alert
                    System.out.println(getController().createIndividualAlert(eventID, alertName, alertTime, getLocalStorage().getUserID()));
                    // return to this user's list of alerts
                    ListModel alertModel = getController().getAlertsByUserID(getLocalStorage().getUserID());
                    return new AlertList(getLocalStorage(), alertModel, getController());
                case "2":
                    // EventID
                    System.out.println("Enter Event ID:");
                    String frequencyEventID = input.nextLine();
                    // Alert Name
                    System.out.println("Enter new Alert name:");
                    String frequencyAlertName = input.nextLine();
                    // Frequency
                    System.out.println("Enter frequency of the alert");
                    String frequency = input.nextLine();
                    // Alert Time
                    System.out.println("Enter the start time of this frequency alert");
                    System.out.println("Enter new time year:");
                    int frequencyYear = input.nextInt();
                    System.out.println("Enter new time month:");
                    int frequencyMonth = input.nextInt();
                    System.out.println("Enter new time day:");
                    int frequencyDay = input.nextInt();
                    System.out.println("Enter new time hour:");
                    int frequencyHour = input.nextInt();
                    System.out.println("Enter new time minute:");
                    int frequencyMinute = input.nextInt();
                    input.nextLine();
                    System.out.println("");
                    LocalDateTime startTime = getController().alertTimeFromInteger(frequencyYear,frequencyMonth,
                            frequencyDay,frequencyHour,frequencyMinute);
                    // Creating the alert
                    System.out.println(getController().createFrequencyAlert(frequencyEventID, frequencyAlertName,
                            getLocalStorage().getUserID(), startTime, frequency));
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

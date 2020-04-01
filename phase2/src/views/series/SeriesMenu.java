package views.series;

//import com.sun.tools.corba.se.idl.StringGen;
import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.alerts.EditAlertView;
import views.general.MainMenu;

import java.time.LocalDateTime;
import java.util.Scanner;

public class SeriesMenu extends View {
    public SeriesMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create series");
        System.out.println("[2] Get series");
        System.out.println("[3] Edit series");
        System.out.println("[~] Return to main menu");
    }

    private void inputPromptForCreateSeriesByEvents() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create series by combining events");
        System.out.println("[2] Create series from event formula");
        System.out.println("[~] Return to series menu");
    }


    private void createEventList(String [] events, Integer indexCount, String eventID){
        events[indexCount] = eventID;
        indexCount ++;
    }

    private void inputEvent(String answer, String [] events, Integer indexCount) {
        switch (answer) {
            case "y":
                Scanner input = new Scanner(System.in);
                System.out.println("Please enter the eventIDs:");
                String eventID = input.nextLine();
                createEventList(events, indexCount, eventID);
                inputEvent(answer, events, indexCount);
            case "n":
                break;
            default:
                printInputError();
                inputEvent(answer, events, indexCount);
        }

    }

    private LocalDateTime getTime() {
        Scanner input = new Scanner(System.in);
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
        LocalDateTime time = getController().alertTimeFromInteger(year, month, day, hour, minute);
        return time;
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Series Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch (selection) {
                case "1":
                    inputPromptForCreateSeriesByEvents();
                    String selection2 = input.nextLine();
                    switch (selection2) {
                        case "1":
                            System.out.println("Please enter the name of the series:");
                            String name = input.nextLine();
                            System.out.println("Do you want to add events? (y/n)");
                            String answer = input.nextLine();

                            String[] events = new String[0];
                            Integer indexCount = 0;
                            inputEvent(answer, events, indexCount);

                            boolean create = getController().createSeriesByCombiningEvents(name, events, getLocalStorage().getUserID());
                            if (create){
                                System.out.println("new series has been created");
                            } else {
                                System.out.println("An error occurred, the series was not created.");
                            }
                            return new SeriesMenu(getLocalStorage(), null, getController());

                        case "2":
                            System.out.println("Please enter the name of the series:");
                            String seriesName = input.nextLine();
                            System.out.println("Please enter the start time:");
                            LocalDateTime startTime = getTime();
                            System.out.println("Please enter the end time:");
                            LocalDateTime endTime = getTime();
                            System.out.println("Please enter the frequency:");
                            String frequency = input.nextLine();
                            System.out.println("Please enter number of the events:");
                            String numEvents = input.nextLine();
                            int num = Integer.parseInt(numEvents);

                            boolean createFromEventFormula = getController().createSeriesFromEventFormula(seriesName,
                                    startTime, endTime, frequency, num,getLocalStorage().getUserID());
                            if (createFromEventFormula){
                                System.out.println("new series has been created from the event formula");
                            } else {
                                System.out.println("An error occurred, the series was not created.");
                            }
                            return new SeriesMenu(getLocalStorage(), null, getController());
                    }
                case "2":
                    return new GetSeriesView(getLocalStorage(), null, getController());
                case "3":
                    System.out.println("Please enter the ID of the series you would like to modify");
                    String seriesID = input.nextLine();
                    return new EditSeriesView(getLocalStorage(), getModel(), getController(), seriesID);
                case "~":
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }

}

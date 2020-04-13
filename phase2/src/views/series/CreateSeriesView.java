package views.series;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A view for display and handle the following options for create series menu.
 */
public class CreateSeriesView extends View{

    /**
     * A constructor for create series view
     * @param localStorage the local storage of the current view
     * @param model the model of the current view
     * @param controller the controller of the current view
     */
    public CreateSeriesView(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPromptForCreateSeriesByEvents() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create series by combining events");
        System.out.println("[2] Create series from event formula");
        System.out.println("[~] Return to series menu");
    }

    private void createEventList(ArrayList<String> events, String eventID){
        events.add(eventID);
    }

    private void inputEvent(ArrayList<String> events) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to add events? (y/n)");
        String answer = input.nextLine();

        switch (answer) {
            case "y":
                System.out.println("Please enter the event ID:");
                String eventID = input.nextLine();
                createEventList(events, eventID);
                inputEvent(events);
            case "n":
                System.out.println("All events has been entered");
                break;
            default:
                System.out.println("Please enter y/n again");
                inputEvent(events);
        }

    }

    /**
     *  Functions including:
     *  1. Create series by combining events - enters all events that want to be in one series then the series will be created
     *  2. Create series from event formula - follow the event formula to create a series
     * @return back to the series menu
     */
    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Create Series Menu");
        while (true) {
            inputPromptForCreateSeriesByEvents();
            String selection = input.nextLine();
            switch (selection) {
                case "1":
                    System.out.println("");
                    System.out.println("Please enter the name of the series:");
                    String name = input.nextLine();

                    ArrayList<String> events = new ArrayList<>();
                    inputEvent(events);

                    boolean create = getController().createSeriesByCombiningEvents(name, events, getLocalStorage().getUserID());
                    if (create) {
                        System.out.println("new series has been created");
                    } else {
                        System.out.println("An error occurred, the series was not created.");
                    }
                    System.out.println("");
                    return new SeriesMenu(getLocalStorage(), null, getController());

                case "2":
                    System.out.println("");
                    System.out.println("Please enter the name of the series:");
                    String seriesName = input.nextLine();
                    System.out.println("Please enter the start time (yyyy-MM-dd HH:mm) of the first event:");
                    String startTime = input.nextLine();
                    System.out.println("Please enter the end time (yyyy-MM-dd HH:mm) of the first event:");
                    String endTime = input.nextLine();
                    System.out.println("Please enter the frequency ([d]ay/[w]eek):");
                    String frequency = input.nextLine();
                    System.out.println("Please enter number of the events:");
                    int numEvents = input.nextInt();
                    input.nextLine();

                    boolean createFromEventFormula = getController().createSeriesFromEventFormula(seriesName,
                            startTime, endTime, frequency, numEvents, getLocalStorage().getUserID());
                    if (createFromEventFormula) {
                        System.out.println("New series has been created from the event formula");
                    } else {
                        System.out.println("An error occurred, the series was not created.");
                    }
                    System.out.println("");
                    return new SeriesMenu(getLocalStorage(), null, getController());
            }
        }
    }

}

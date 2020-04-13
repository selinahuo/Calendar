package views.series;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.events.EventList;
import views.general.MainMenu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A view for display and handle the following options for Series Menu.
 * Functions including:
 * 1. Create series - goes to CreateSeriesView
 * 2. Get all series - goes to SeriesList where list of series will be presented
 * 3. Get events by name - goes to GetSeriesView
 * 4. Get events by series - goes to EventList where list of events will be presented
 * 5. Edit series - goes to EditSeries
 * ~. Return to main menu - back to the home menu
 */
public class SeriesMenu extends View {
    public SeriesMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create series");
        System.out.println("[2] Get all series");
        System.out.println("[3] Get series by name");
        System.out.println("[4] Get events by series");
        System.out.println("[5] Edit series");
        System.out.println("[~] Return to main menu");
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
                    System.out.println("");
                    return new CreateSeriesView(getLocalStorage(), null, getController());
                case "2":
                    ListModel seriesByUserID = getController().getSeriesByUserID(getLocalStorage().getUserID());
                    System.out.println("");
                    return new SeriesList(getLocalStorage(), seriesByUserID, getController());
                case "3":
                    return new GetSeriesView(getLocalStorage(), null, getController());
                case "4":
                    System.out.println("Enter SeriesID:");
                    String seriesID = input.nextLine();
                    ListModel eventsBySeriesID = getController().getEventsBySeriesIDAndOwnerID(seriesID, getLocalStorage().getUserID());
                    System.out.println("");
                    return new EventList(getLocalStorage(), eventsBySeriesID, getController());
                case "5":
                    System.out.println("Enter SeriesID:");;
                    String newSeriesID = input.nextLine();
                    return new EditSeriesView(getLocalStorage(), null, getController(), newSeriesID);
                case "~":
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
                    System.out.println("");
            }
        }
    }

}

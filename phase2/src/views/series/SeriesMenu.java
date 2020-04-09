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
                case "~":
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
                    System.out.println("");
            }
        }
    }

}

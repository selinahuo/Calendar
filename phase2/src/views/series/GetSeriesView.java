package views.series;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.general.MainMenu;

import java.util.Scanner;

/**
 * A view for display and handle the following options for get series menu.
 */
public class GetSeriesView extends View {

    /**
     * A constructor for get series view
     * @param localStorage the local storage of the current view
     * @param model the model of the current view
     * @param controller the controller of the current view
     */
    public GetSeriesView(LocalStorage localStorage, ViewModel model, Controller controller){
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get Series by UserID");
        System.out.println("[2] Get Series (plural) by Series Name");
        System.out.println("[~] Return to Main menu");
    }

    /**
     *  Functions including:
     *  1. Get series by userID - goes to SeriesList View
     *  2. Get multiple series by seriesName - goes to SeriesList View
     *  ~. Return back to series menu
     * @return as described above
     */
    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Get Series Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch (selection){
                case "1":
                    ListModel seriesByUserID = getController().getSeriesByUserID(getLocalStorage().getUserID());
                    System.out.println("");
                    return new SeriesList(getLocalStorage(), seriesByUserID, getController());
                case "2":
                    System.out.println("Enter Series Name:");
                    String seriesName = input.nextLine();
                    ListModel seriesBySeriesName = getController().getSeriesBySeriesName(seriesName, getLocalStorage().getUserID());
                    System.out.println("");
                    return new SeriesList(getLocalStorage(), seriesBySeriesName, getController());
                case "~":
                    return new SeriesMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
                    System.out.println("");
            }
        }
    }
}

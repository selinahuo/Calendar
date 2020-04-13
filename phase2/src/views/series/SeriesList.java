package views.series;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.calendars.CalendarMenu;
import views.users.UserMenu;

import java.util.Scanner;

/**
 * A view for display and handle the following options for series list menu.
 */

public class SeriesList extends ListView {
    public SeriesList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt(){
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Edit series's name");
        System.out.println("[~] Back to series menu");
    }

    /**
     *  Functions including:
     *  1. Edit series's name
     *  ~. Return to series menu
     * @return back to series menu
     */
    @Override
    public View run() {
        printTitle("Series List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch (selection) {
                case "1":
                    System.out.println("Enter series ID:");
                    String seriesID = input.nextLine();
                    System.out.println("Enter a new series name:");
                    String newName = input.nextLine();
                    boolean nameModified = getController().editSeriesName(seriesID, newName, getLocalStorage().getUserID());
                    if (nameModified) {
                        System.out.println("The series was modified");
                    } else {
                        System.out.println("The series name change was not complete");
                    }
                    System.out.println("");
                    return new SeriesMenu(getLocalStorage(), getModel(), getController());
                case "~":
                    System.out.println("");
                    return new SeriesMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
                    System.out.println("");
            }
        }
    }
}

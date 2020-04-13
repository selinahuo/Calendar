package views.series;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;

import java.util.Scanner;

/**
 * A view for display and handle the following options for edit series menu.
 */

public class EditSeriesView extends View {

    String seriesID;

    /**
     * A constructor for edit series view
     * @param localStorage the local storage of the current view
     * @param model the model of the current view
     * @param controller the controller of the current view
     */
    public EditSeriesView(LocalStorage localStorage, ViewModel model, Controller controller, String seriesID) {
        super(localStorage, model, controller);
        this.seriesID = seriesID;
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Edit the Name of an series");
        System.out.println("[~] Return to series menu");
    }

    /**
     *  Functions including:
     *  1. Edit series name - edit the name of the series - back to the series menu after modification
     *  2. Return to main menu - back to the series menu
     *  ~. back to the series menu
     * @return as described above, if any problem occurs, back to edit series menu
     */
    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Edit Series Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch (selection) {
                case "1":
                    System.out.println("Enter the new name for this series:");
                    String newName = input.nextLine();
                    boolean nameModified = getController().editSeriesName(seriesID, newName, getLocalStorage().getUserID());
                    if (nameModified) {
                        System.out.println("The series is being modified");
                    } else {
                        System.out.println("The series name change was not complete");
                    }
                    System.out.println("");
                    return new SeriesMenu(getLocalStorage(), getModel(), getController());
                case "~":
                    System.out.println("");
                    return new SeriesMenu(getLocalStorage(), getModel(), getController());
                default:
                    printInputError();
                    System.out.println("");
                    return new EditSeriesView(getLocalStorage(), getModel(), getController(), seriesID);
            }
        }
    }
}

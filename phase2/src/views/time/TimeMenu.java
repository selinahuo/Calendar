package views.time;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.events.EventList;
import views.general.MainMenu;

import java.util.Scanner;

/**
 * View for displaying and manipulating application time
 */
public class TimeMenu extends View {
    /**
     * Create a TimeMenu
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model view model used by this view, no model necessary for menu
     * @param controller controller the view interacts with
     */
    public TimeMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get current time");
        System.out.println("[2] Set time");
        System.out.println("[3] Reset Time");
        System.out.println("[~] Return to main menu");
    }

    private void printApplicationTime() {
        System.out.println(getController().getTime());
        System.out.println("");
    }

    /**
     * Displays and handles application time interaction options. Following options: getting time, and setting and
     * resetting time.
     * Allows user to navigate back to the main menu.
     *
     * @return next view; main menu
     */
    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Time Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            System.out.println("");
            switch(selection) {
                case "1":
                    printTitle("Application Time:");
                    printApplicationTime();
                    break;
                case "2":
                    System.out.println("Time will be paused until you reset the time");
                    System.out.println("Please enter the new time (yyyy-mm-dd hh:mm):");
                    String dateTime = input.nextLine();
                    System.out.println("");
                    getController().setTime(dateTime);
                    printTitle("Time set to:");
                    printApplicationTime();
                    break;
                case "3":
                    getController().resetTime();
                    printTitle("Time reset to:");
                    printApplicationTime();
                    break;
                case "~":
                    System.out.println("");
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
                    System.out.println("");
            }
        }
    }
}

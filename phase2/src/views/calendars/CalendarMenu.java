package views.calendars;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.general.MainMenu;

import java.util.Scanner;

/**
 * View dedicated to displaying and handling calendar entity functionality
 */
public class CalendarMenu extends View {

    /**
     * Create an CalendarMenu view
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model view model used by this view, no model necessary for menu
     * @param controller controller the view interacts with
     */
    public CalendarMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create calendar");
        System.out.println("[2] List your calendars");
        System.out.println("[~] Return to main menu");
    }

    /**
     * Displays and handles event entity interaction options. Options include, listing calendars, creating calendars
     * And allows user to navigate back to the main menu.
     *
     * @return next view; main menu, or an calendar list
     */
    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Calendar Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Please name your calendar:");
                    String calendarName = input.nextLine();
                    String userID = getLocalStorage().getUserID();
                    String calendarID = getController().createCalendar(userID, calendarName);
                    System.out.println(calendarID);
                    if (calendarID == null) {
                        printError("Something went wrong creating the calendar.\n");
                        break;
                    }
                    System.out.println("");

                case "2":
                    ListModel calendarModel = getController().getCalendarsByUserID(getLocalStorage().getUserID());
                    System.out.println("");
                    return new CalendarList(getLocalStorage(), calendarModel, getController());

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

package views.calendars;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.events.EventList;
import java.util.Scanner;

/**
 * A view for display and handle the following options for showing list of calendars.
 * Functions including:
 * Showing this user's list of calendars, edit calendar options, calendar deletion, and view events of the calendar
 */
public class CalendarList extends ListView {

    /**
     * Construct an CalendarList view
     *
     * @param localStorage session storage for calendar, similar to browser local storage
     * @param model list view model used by this view
     * @param controller controller the view interacts with
     */
    public CalendarList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Clip calendar");
        System.out.println("[2] Edit the calendar name");
        System.out.println("[3] Add Events to your Calendar");
        System.out.println("[4] View events of your Calendar");
        System.out.println("[5] Delete calendar");
        System.out.println("[~] Back to Calendar menu");
    }

    /**
     * Displays a representation of a list of calendars.
     * Allows for following options to interact with entities: clip an calendar to clipboard,
     * edit calendar options, calendar deletion, and view events of the calendar or navigate back to the event menu.
     *
     * @return the next view; calendar menu or an individual selected calendar view
     */
    @Override
    public View run() {
        printTitle("Calendar List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.print("Current ");
                    printClipBoard();
                    System.out.println("Enter calendar ID to clip:");
                    String clipCalendar = input.nextLine();
                    getLocalStorage().setClipCalendar(clipCalendar);
                    System.out.print("New ");
                    printClipBoard();
                    break;
                case "2":
                    System.out.println("Please enter the ID of the calendar you would like to modify");
                    String name_calendarID = input.nextLine();
                    System.out.println("Enter the new name for this calendar:");
                    String newName = input.nextLine();
                    boolean nameModified = getController().editCalendarName(name_calendarID, newName,
                            getLocalStorage().getUserID());
                    if (nameModified){
                        System.out.println("The Calendar is being modified:");
                    } else {
                        System.out.println("The Calendar name change was not complete");
                    }
                    ListModel nameCalendarModel = getController().getCalendarsByUserID(getLocalStorage().getUserID());
                    return new CalendarList(getLocalStorage(), nameCalendarModel, getController());
                case "3":
                    System.out.println("Please enter the ID of the calendar you would like to modify");
                    String add_calendarID = input.nextLine();
                    printClipBoard();
                    System.out.println("Enter the ID of the event your would like to add to the calendar");
                    String addEventID = input.nextLine();
                    boolean addedEvent = getController().addEventToCalendar(addEventID, add_calendarID,
                            getLocalStorage().getUserID());
                    if (addedEvent){
                        System.out.println("The event has been added\n");
                    } else {
                        System.out.println("The event could not be added\n");
                    }
                    ListModel addCalendarModel = getController().getCalendarsByUserID(getLocalStorage().getUserID());
                    return new CalendarList(getLocalStorage(), addCalendarModel, getController());
                case "4":
                    System.out.println("Please enter the ID of the calendar");
                    String eve_calendarID = input.nextLine();
                    ListModel eventModel = getController().getEventsByCalendarIDAndOwnerID(eve_calendarID,
                            getLocalStorage().getUserID());
                    System.out.println("");
                    return new EventList(getLocalStorage(), eventModel, getController());
                case "5":
                    System.out.println("Please enter the ID of the calendar you would like to delete");
                    String deleteCalendarID = input.nextLine();
                    boolean deleted = getController().deleteCalendar(deleteCalendarID, getLocalStorage().getUserID());
                    if (deleted){
                        System.out.println("Calendar has been deleted\n");
                    } else {
                        System.out.println("Calendar deletion was not completed. Make sure there are no events " +
                                "in the calendar\n");
                    }
                    return new CalendarMenu(getLocalStorage(), getModel(), getController());
                case "~":
                    System.out.println("");
                    return new CalendarMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
                    System.out.println("");
            }
        }
    }
}

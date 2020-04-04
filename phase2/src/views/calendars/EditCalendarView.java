package views.calendars;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.general.MainMenu;
import java.util.Scanner;

public class EditCalendarView extends View {

    String calendarID;

    public EditCalendarView(LocalStorage localStorage, ViewModel model, Controller controller, String calendarID) {
        super(localStorage, model, controller);
        this.calendarID = calendarID;
    }

    private void inputPrompt(){
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Edit the Name of an Calendar");
        System.out.println("[2] Add Events to your Calendar");
        System.out.println("[3] Remove Events from your Calendar");
        System.out.println("[~] Return to main menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Edit Calendar Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Enter the new name for this calendar:");
                    String newName = input.nextLine();
                    boolean nameModified = getController().editCalendarName(calendarID, newName, getLocalStorage().getUserID());
                    if (nameModified){
                        System.out.println("The Calendar is being modified:");
                    } else {
                        System.out.println("The Calendar name change was not complete");
                    }
                    return new CalendarMenu(getLocalStorage(),getModel(),getController());

                case "2":
                    System.out.println("Enter the ID of the event your would like to add to the calendar");
                    String addEventID = input.nextLine();
                    boolean addedEvent = getController().addEventToCalendar(addEventID, calendarID, getLocalStorage().getUserID());
                    if (addedEvent){
                        System.out.println("The Event has been added");
                    } else {
                        System.out.println("The Calendar change was not complete");
                    }
                    return new CalendarMenu(getLocalStorage(),getModel(),getController());

                case "3":
                    System.out.println("Enter the ID of the event your would like to remove from the calendar");
                    String removeEventID = input.nextLine();
                    boolean removed_event = getController().removeEventFromCalendar(removeEventID, getLocalStorage().getUserID());
                    if (removed_event){
                        System.out.println("The Event has been removed");
                    } else {
                        System.out.println("The Calendar change was not complete");
                    }
                    return new CalendarMenu(getLocalStorage(),getModel(),getController());

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

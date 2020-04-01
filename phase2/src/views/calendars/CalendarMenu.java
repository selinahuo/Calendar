package views.calendars;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.alerts.CreateAlertView;
import views.general.MainMenu;

import java.util.Scanner;

public class CalendarMenu extends View {
    public CalendarMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create calendar");
        System.out.println("[2] List your calendars");
        System.out.println("[3] Edit your calendar");
        System.out.println("[4] Delete an calendar");
        System.out.println("[~] Return to main menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Calendar Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    return new CreateCalendarView(getLocalStorage(), getModel(), getController());

                case "2":
                    ListModel calendarModel = getController().getCalendarsByUserID(getLocalStorage().getUserID());
                    return new CalendarList(getLocalStorage(), calendarModel, getController());

                case "3":
                    System.out.println("Please enter the ID of the calendar you would like to modify");
                    String calendarID = input.nextLine();
                    return new EditCalendarView(getLocalStorage(), getModel(), getController(), calendarID);

                case "4":
                    System.out.println("Please enter the ID of the calendar you would like to delete");
                    String deleteCalendarID = input.nextLine();
                    boolean deleted = getController().deleteCalendar(deleteCalendarID, getLocalStorage().getUserID());
                    if (deleted){
                        System.out.println("The Calendar is being deleted:");
                    } else {
                        System.out.println("The Calendar deletion was not complete");
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

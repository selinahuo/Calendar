package views.calendars;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.general.MainMenu;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CreateCalendarView extends View {
    public CreateCalendarView(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create Calendar");
        System.out.println("[~] Return to Main Menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Create Calendar View");
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
                        printError("Something went wrong creating the calendar.");
                        break;
                    }
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

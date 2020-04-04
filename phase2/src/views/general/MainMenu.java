package views.general;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.alerts.AlertMenu;
import views.calendars.CalendarMenu;
import views.events.EventMenu;
import views.invitations.InvitationMenu;
import views.notes.NoteMenu;
import views.series.SeriesMenu;
import views.time.TimeMenu;
import views.users.UserMenu;

import java.util.Scanner;

public class MainMenu extends View {
    public MainMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Alerts");
        System.out.println("[2] Calendars");
        System.out.println("[3] Events");
        System.out.println("[4] Invitations");
        System.out.println("[5] Notes");
        System.out.println("[6] Series");
        System.out.println("[7] Time");
        System.out.println("[8] Users");
        System.out.println("[q] Log out");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Main Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("");
                    return new AlertMenu(getLocalStorage(),null, getController());
                case "2":
                    System.out.println("");
                    return new CalendarMenu(getLocalStorage(), null, getController());
                case "3":
                    System.out.println("");
                    return new EventMenu(getLocalStorage(), null, getController());
                case "4":
                    System.out.println("");
                    return new InvitationMenu(getLocalStorage(), null, getController());
                case "5":
                    System.out.println("");
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "6":
                    System.out.println("");
                    return new SeriesMenu(getLocalStorage(), null, getController());
                case "7":
                    System.out.println("");
                    return new TimeMenu(getLocalStorage(), null, getController());
                case "8":
                    System.out.println("");
                    return new UserMenu(getLocalStorage(), null, getController());
                case "q":
                    System.out.println("");
                    getController().resetTime();
                    return new AuthMenu(new LocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }

}



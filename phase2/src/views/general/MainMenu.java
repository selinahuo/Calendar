package views.general;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.alerts.AlertMenu;
import views.events.EventMenu;
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
                    return new AlertMenu(getLocalStorage(),null, getController());
                case "2":
                    // TODO Calendars Menu
                    return null;
                case "3":
                    return new EventMenu(getLocalStorage(), null, getController());
                case "4":
                    return null;
                case "5":
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "6":
                    return new SeriesMenu(getLocalStorage(), null, getController());
                case "7":
                    return new TimeMenu(getLocalStorage(), null, getController());
                case "8":
                    return new UserMenu(getLocalStorage(), null, getController());
                case "q":
                    return new AuthMenu(new LocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }

}



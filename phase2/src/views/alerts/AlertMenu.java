package views.alerts;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.events.EventList;
import views.general.MainMenu;

import java.util.Scanner;

public class AlertMenu extends View{
    public AlertMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt(){
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get Overdue Alerts");
        System.out.println("[2] Get My Alerts");
        System.out.println("[3] Create a new Alert");
        System.out.println("[4] Acknowledge Alert");
        System.out.println("[5] Edit an Alert");
        System.out.println("[~] Return to main menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Alert Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":

                case "2":

                case "3":
                    return null;
                case "4":

                case "5":
                    return null;
                case "~":
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }
}

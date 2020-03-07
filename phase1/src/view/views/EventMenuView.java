package view.views;

import controller.CommandLineController;
import controller.viewmodels.AuthenticatedModel;
import view.LocalStorage;

import java.util.Scanner;

public class EventMenuView extends AuthenticatedView {
    public EventMenuView(LocalStorage localStorage, AuthenticatedModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following menus by typing the number:");
        System.out.println("[1] Get Event by Name");
        System.out.println("[2] Get events (past/ongoing/future)");
        System.out.println("[3] Get event by date");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Event Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Events");
                    break;
                case "2":
                    System.out.println("Alerts");
                    break;
                case "3":
                    System.out.println("Series");
                    break;
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}

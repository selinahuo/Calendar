package views.general;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;

import java.util.Scanner;

public class AuthMenu extends View {
    public AuthMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Log in");
        System.out.println("[2] Create user");
        System.out.println("[q] Quit");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Authentication Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    return new Login(getLocalStorage(), getModel(), getController());
                case "2":
                    return new CreateUser(getLocalStorage(), getModel(), getController());
                case "q":
                    return null;
                default:
                    printInputError();
            }
        }
    }
}

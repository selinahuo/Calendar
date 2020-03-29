package views.general;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;

import java.util.Scanner;

public class CreateUser extends View {
    public CreateUser(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Create a New User");

        System.out.println("Choose a username:");
        String username = input.nextLine();
        System.out.println("Choose a password:");
        String password = input.nextLine();
        boolean success = getController().createUser(username, password);

        if (success) {
            System.out.println("User created successfully.");
        } else {
            printError("That username is already in use, please choose another username.");
        }

        return new AuthMenu(getLocalStorage(), null, getController());
    }
}

package views.general;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;

import java.util.Scanner;

public class Login extends View {
    public Login(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Log In");

        System.out.println("Enter username:");
        String username = input.nextLine();
        System.out.println("Enter password:");
        String password = input.nextLine();
        String userID = getController().loginUser(username, password);

        if (userID == null) {
            printError("Login failed.\n");
            return new AuthMenu(getLocalStorage(), null, getController());
        }

        getLocalStorage().setUserID(userID);
        System.out.println("Login successful.\n");
        return new MainMenu(getLocalStorage(), null, getController());
    }
}

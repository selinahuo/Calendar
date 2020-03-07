package view.views;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;

import java.util.Scanner;

public class SignInView extends View{
    public SignInView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter username (Michael):");
            String username = input.nextLine();
            System.out.println("Enter password (123):");
            String password = input.nextLine();
            String userID = super.getController().authenticateUser(username, password);
            if (userID != null) {
                System.out.println("User login successful.");
                super.getLocalStorage().setUserID(userID);
                return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
            } else {
                System.out.println("Username or password are incorrect.");
            }
        }
    }
}

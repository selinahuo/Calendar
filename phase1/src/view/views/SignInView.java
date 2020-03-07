package view.views;

import controller.CommandLineController;
import controller.viewmodels.AuthenticatedModel;
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
            System.out.println("Enter username:");
            String username = input.nextLine();
            System.out.println("Enter password:");
            String password = input.nextLine();
            AuthenticatedModel newModel = super.getController().authenticateUser(username, password);
            if (newModel != null) {
                System.out.println("User login successful.");
                super.getLocalStorage().setUserID(newModel.getUserID());
                return new HomeView(super.getLocalStorage(), newModel, super.getController());
            } else {
                System.out.println("Username or password are incorrect.");
            }
        }
    }
}

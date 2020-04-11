package views.general;

import controller.Controller;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;

import java.util.Scanner;

/**
 * View for displaying and handling login and user creation. First application view.
 */
public class AuthMenu extends View {

    /**
     * Create an AuthMenu view
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model view model used by this view, no model necessary for menu
     * @param controller controller the view interacts with
     */
    public AuthMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Log in");
        System.out.println("[2] Create user");
        System.out.println("[q] Quit");
    }

    /**
     * Displays and handles application user options. Options include: logging in and creating a new user.
     * Allows user to quit the application and return a null next view
     *
     * @return next view; main menu or null
     */
    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Authentication Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Enter username:");
                    String username = input.nextLine();
                    System.out.println("Enter password:");
                    String password = input.nextLine();
                    String userID = getController().loginUser(username, password);

                    if (userID == null) {
                        printError("Login failed.\n");
                        break;
                    }

                    getLocalStorage().setUserID(userID);
                    System.out.println("Login successful.\n");
                    return new MainMenu(getLocalStorage(), null, getController());
                case "2":
                    System.out.println("Choose a username:");
                    String newUsername = input.nextLine();
                    System.out.println("Choose a password:");
                    String newPassword = input.nextLine();
                    boolean success = getController().createUser(newUsername, newPassword);

                    if (success) {
                        System.out.println("User created successfully.\n");
                    } else {
                        printError("That username is already in use, please choose another username.\n");
                    }
                    break;
                case "q":
                    return null;
                default:
                    printInputError();
            }
        }
    }
}

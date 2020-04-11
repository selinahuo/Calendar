package views.users;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.general.MainMenu;

import java.util.Scanner;

/**
 * View dedicated to displaying and handling user entity functionality
 */
public class UserMenu extends View {

    /**
     * Create an UserMenu
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model view model used by this view, no model necessary for menu
     * @param controller controller the view interacts with
     */
    public UserMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] List all users");
        System.out.println("[~] Return to main menu");
    }

    /**
     * Displays and handles user entity interaction options to get a list of all application users.
     * Allows user to navigate back to the main menu.
     *
     * @return navigates to the main menu or a UserList of all application users
     */
    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("User Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    ListModel model = getController().getUsers();
                    System.out.println("");
                    return new UserList(getLocalStorage(), model, getController());
                case "~":
                    System.out.println("");
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
                    System.out.println("");
            }
        }
    }
}

package views.users;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;

import java.util.Scanner;

/**
 * View for displaying and handling options related to a list of users
 */
public class UserList extends ListView {

    /**
     * Construct a UserList
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model list view model used by this view
     * @param controller controller the view interacts with
     */
    public UserList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Clip user ID");
        System.out.println("[~] Back to user menu");
    }

    /**
     * Displays a representation of a list of users.
     * Allows for following options to interact with entities: copy a user to clipboard, or navigate back to the user
     * menu.
     *
     * @return the next view; the user menu
     */
    @Override
    public View run() {
        printTitle("User List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.print("Current ");
                    printClipBoard();
                    System.out.println("Enter user ID to clip");
                    String userID = input.nextLine();
                    getLocalStorage().setClipUser(userID);
                    System.out.print("New ");
                    printClipBoard();
                    break;
                case "~":
                    System.out.println("");
                    return new UserMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
                    System.out.println("");
            }
        }
    }
}

package views.invitations;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.SingularInvitationModel;
import views.ListView;
import views.LocalStorage;
import views.View;

import java.util.Scanner;

/**
 * View for displaying and handling options related to a list of invitations
 */
public class InvitationList extends ListView {

    /**
     * Construct an InvitationList
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model list view model used by this view
     * @param controller controller the view interacts with
     */
    public InvitationList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] View individual invitation");
        System.out.println("[~] Back to invitation menu");
    }

    /**
     * Displays a representation of a list of invitations.
     * Allows for following options to interact with entities: view an individual invitation, or navigate back to the
     * invitation menu.
     *
     * @return the next view; invitation menu or an individual selected invitation view
     */
    @Override
    public View run() {
        printTitle("Invitation List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Please enter an invitation ID:");
                    String invitationID = input.nextLine();
                    SingularInvitationModel invModel = getController().getSingularInvitation(invitationID, getLocalStorage().getUserID());
                    if (invModel == null) {
                        printError("Invitation could not be found.");
                        break;
                    }
                    System.out.println("");
                    return new SingularInvitation(getLocalStorage(), invModel, getController());
                case "~":
                    System.out.println("");
                    return new InvitationMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
                    System.out.println("");
            }
        }
    }
}

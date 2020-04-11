package views.invitations;

import controller.Controller;
import controller.viewmodels.SingularInvitationModel;
import controller.viewmodels.SingularModel;
import views.LocalStorage;
import views.SingularView;
import views.View;

import java.util.Scanner;

/**
 * View for displaying and handling options related to a single invitation
 */
public class SingularInvitation extends SingularView {
    private SingularInvitationModel model;

    /**
     * Construct a SingleInvitation view
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model single invitation view model for this instance
     * @param controller controller the view interacts with
     */
    public SingularInvitation(LocalStorage localStorage, SingularInvitationModel model, Controller controller) {
        super(localStorage, model, controller);
        this.model = model;
    }

    /**
     * Get the SingularInvitationModel associated with this view
     *
     * @return view's SingularInvitationModel
     */
    @Override
    public SingularInvitationModel getModel() {
        return model;
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        if (getLocalStorage().getUserID().equals(getModel().getEntityOwner())) {
            System.out.println("[1] Delete invitation");
        } else if (getModel().getAccept() == null) {
            System.out.println("[a] Accept invitation");
            System.out.println("[d] Decline invitation");
        }
        System.out.println("[~] Return to invitation menu");
    }

    /**
     * Displays a string representation of a invitation.
     * Allows for following options to interact with invitation:
     * Accept or decline the invitation if current session user is the invitee.
     * Delete the invitation if current session user is the inviter
     *
     * @return the next view; invitation menu
     */
    @Override
    public View run() {
        printTitle("Invitation Single");
        Scanner input = new Scanner(System.in);

        printSingular();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    if (getLocalStorage().getUserID().equals(getModel().getEntityOwner())) {
                        getController().deleteInvitation(getModel().getEntityID(), getLocalStorage().getUserID());
                        System.out.println("Invitation deleted\n");
                        return new InvitationMenu(getLocalStorage(), null, getController());
                    }
                    printInputError();
                    break;
                case "a":
                    if (getModel().getAccept() == null) {
                        System.out.println("Enter your response:");
                        String acceptingResponse = input.nextLine();
                        getController().acceptInvitation(getModel().getEntityID(), acceptingResponse, true, getLocalStorage().getUserID());

                        System.out.println("");
                        SingularInvitationModel acceptModel = getController().getSingularInvitation(getModel().getEntityID(), getLocalStorage().getUserID());
                        return new SingularInvitation(getLocalStorage(), acceptModel, getController());
                    }
                    printInputError();
                    break;
                case "d":
                    if (getModel().getAccept() == null) {
                        System.out.println("Enter your response:");
                        String declineResponse = input.nextLine();
                        getController().acceptInvitation(getModel().getEntityID(), declineResponse, false, getLocalStorage().getUserID());

                        System.out.println("");
                        SingularInvitationModel acceptModel = getController().getSingularInvitation(getModel().getEntityID(), getLocalStorage().getUserID());
                        return new SingularInvitation(getLocalStorage(), acceptModel, getController());
                    }
                    printInputError();
                    break;
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

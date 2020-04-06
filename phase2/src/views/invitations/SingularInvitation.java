package views.invitations;

import controller.Controller;
import controller.viewmodels.SingularInvitationModel;
import controller.viewmodels.SingularModel;
import views.LocalStorage;
import views.SingularView;
import views.View;

import java.util.Scanner;

public class SingularInvitation extends SingularView {
    private SingularInvitationModel model;

    public SingularInvitation(LocalStorage localStorage, SingularInvitationModel model, Controller controller) {
        super(localStorage, model, controller);
        this.model = model;
    }

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

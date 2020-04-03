package views.invitations;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.SingularInvitationModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.events.EventList;
import views.general.MainMenu;

import java.util.Scanner;

public class InvitationMenu extends View {
    public InvitationMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] List incoming invitations");
        System.out.println("[2] List outgoing invitations");
        System.out.println("[3] Invite User to Event (have an user and event clipped)");
        System.out.println("[~] Return to main menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Invitation Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    ListModel incomingModel = getController().getIncomingInvitations(getLocalStorage().getUserID());
                    System.out.println("");
                    return new InvitationList(getLocalStorage(), incomingModel, getController());
                case "2":
                    ListModel outgoingModel = getController().getOutgoingInvitations(getLocalStorage().getUserID());
                    System.out.println("");
                    return new InvitationList(getLocalStorage(), outgoingModel, getController());
                case "3":
                    printClipBoard();
                    System.out.println("Please enter event ID of the invitation:");
                    String eventID = input.nextLine();
                    System.out.println("Please enter the ID of the user you will invite:");
                    String inviteeID = input.nextLine();
                    System.out.println("Please enter your message:");
                    String message = input.nextLine();
                    String invitationID = getController().createInvitation(eventID, getLocalStorage().getUserID(), inviteeID, message);
                    if (invitationID == null) {
                        printError("Something went wrong creating the invitation.");
                        break;
                    }
                    SingularInvitationModel newInvitation = getController().getSingularInvitation(invitationID, getLocalStorage().getUserID());
                    return new SingularInvitation(getLocalStorage(), newInvitation, getController());
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

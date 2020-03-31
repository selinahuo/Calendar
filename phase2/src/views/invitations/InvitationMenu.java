package views.invitations;

import controller.Controller;
import controller.viewmodels.ListModel;
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
                    // TODO invite a user to an event
                    return null;
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

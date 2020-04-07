package views.invitations;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.SingularInvitationModel;
import views.ListView;
import views.LocalStorage;
import views.View;

import java.util.Scanner;

public class InvitationList extends ListView {
    public InvitationList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] View individual invitation");
        System.out.println("[~] Back to invitation menu");
    }

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

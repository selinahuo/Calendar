package views.events;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.SingularInvitationModel;
import controller.viewmodels.SingularModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.invitations.SingularInvitation;
import views.users.UserMenu;

import java.util.Scanner;

public class EventList extends ListView {
    public EventList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] View individual event");
        System.out.println("[2] Clip event");
        System.out.println("[~] Back to event menu");
    }

    @Override
    public View run() {
        printTitle("Event List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Please enter an event ID:");
                    String eventID = input.nextLine();
                    SingularModel eventModel = getController().getSingularEvent(eventID, getLocalStorage().getUserID());
                    if (eventModel == null) {
                        printError("Event could not be found.");
                        break;
                    }
                    System.out.println("");
                    return new SingularEvent(getLocalStorage(), eventModel, getController());
                case "2":
                    System.out.print("Current ");
                    printClipBoard();
                    System.out.println("Enter event ID to clip:");
                    String clipEvent = input.nextLine();
                    getLocalStorage().setClipEvent(clipEvent);
                    System.out.print("New ");
                    printClipBoard();
                    break;
                case "~":
                    System.out.println("");
                    return new EventMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
                    System.out.println("");
            }
        }
    }
}

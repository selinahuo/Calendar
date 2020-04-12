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

/**
 * View for displaying and handling options related to a list of events
 */
public class EventList extends ListView {

    /**
     * Construct an EventList view
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model list view model used by this view
     * @param controller controller the view interacts with
     */
    public EventList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] View individual event");
        System.out.println("[2] Clip event");
        System.out.println("[~] Back to event menu");
    }

    /**
     * Displays a representation of a list of events.
     * Allows for following options to interact with entities: view an individual event, clip an event to clipboard
     * or navigate back to the event menu.
     *
     * @return the next view; event menu or an individual selected event view
     */
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

package views.notes;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.events.EventList;

import java.util.Scanner;

/**
 * View for displaying and handling options related to a list of tags
 */
public class TagList extends ListView {

    /**
     * Construct a MemoList view
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model list view model used by this view
     * @param controller controller the view interacts with
     */
    public TagList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Display all events attached to a tag");
        System.out.println("[2] Attach event to tag");
        System.out.println("[3] Delete a tag");
        System.out.println("[~] Back to Note menu");
    }

    /**
     * Displays a representation of a list of memos.
     * Allows for following options to interact with entities: view all events containing a certain tag, attach a tag
     * to an event, delete a tag, or navigate back to the event menu.
     *
     * @return the next view; Note menu or Event list
     */
    @Override
    public View run() {
        printTitle("Tag List");
        Scanner input = new Scanner(System.in);
        String userID = getLocalStorage().getUserID();

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Please input the tag ID");
                    String tagID = input.nextLine();
                    ListModel eventModel = getController().getEventsByTagIDAndOwnerID(tagID, getLocalStorage().getUserID());
                    return new EventList(getLocalStorage(), eventModel, getController());
                case "2":
                    System.out.println("Enter tag ID:");
                    String tagID2 = input.nextLine();
                    printClipBoard();
                    System.out.println("Enter event ID:");
                    String eventID2 = input.nextLine();
                    boolean changed3 = getController().addTagToEvent(tagID2, eventID2, userID);
                    if (changed3) {
                        System.out.println("The tag was successfully added to the event.\n");
                    } else {
                        System.out.println("An error occurred, the tag was not added to the event.\n");
                    }
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "3":
                    System.out.println("Enter tag ID:");
                    String tagID1 = input.nextLine();
                    boolean deleted = getController().deleteTag(tagID1, userID);
                    if (deleted){
                        System.out.println("The tag has successfully been deleted.\n");
                    } else {
                        System.out.println("An error occurred, the tag was not deleted.");
                        System.out.println("Reminder: a tag cannot be attached to any events to be deleted.\n");
                    }
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "~":
                    System.out.println("");
                    return new NoteMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }
}

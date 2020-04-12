package views.notes;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.events.EventList;

import java.util.Scanner;

public class MemoList extends ListView {

    public MemoList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Display all events containing a certain memo");
        System.out.println("[2] Attach memo to event");
        System.out.println("[3] Edit memo name");
        System.out.println("[4] Edit memo note");
        System.out.println("[5] Delete memo");
        System.out.println("[~] Back to Note menu");
    }

    @Override
    public View run() {
        printTitle("Memo List");
        Scanner input = new Scanner(System.in);
        String userID = getLocalStorage().getUserID();

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Enter memo ID:");
                    String memoID = input.nextLine();
                    ListModel eventModel = getController().getEventsByMemoIDAndOwnerID(memoID, getLocalStorage().getUserID());
                    System.out.println("");
                    return new EventList(getLocalStorage(), eventModel, getController());
                case "2":
                    System.out.println("Enter memo ID:");
                    String memoID2 = input.nextLine();
                    System.out.println("");
                    printClipBoard();
                    System.out.println("Enter event ID:");
                    String eventID = input.nextLine();
                    boolean success = getController().addMemoToEvent(memoID2, eventID, userID);
                    if (success) {
                        System.out.println("The memo was successfully added to the event.\n");
                    } else {
                        System.out.println("An error occurred, the memo was not added to the event.\n");
                    }
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "3":
                    System.out.println("Enter memo ID:");
                    String memoID3 = input.nextLine();
                    System.out.println("Enter new memo name:");
                    String newMemoName = input.nextLine();
                    boolean changed = getController().editMemoName(memoID3, newMemoName, userID);
                    if (changed) {
                        System.out.println("The name has successfully been changed.\n");
                    } else {
                        System.out.println("An error occurred, the name was not changed.\n");
                    }
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "4":
                    System.out.println("Enter memo ID:");
                    String memoID4 = input.nextLine();
                    System.out.println("Enter new memo note:");
                    String newMemoNote = input.nextLine();
                    boolean changed1 = getController().editMemoNote(memoID4, newMemoNote, userID);
                    if (changed1) {
                        System.out.println("The note has successfully been changed.\n");
                    } else {
                        System.out.println("An error occurred, the note was not changed.\n");
                    }
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "5":
                    System.out.println("Enter memo ID:");
                    String memoID5 = input.nextLine();
                    boolean deleted = getController().deleteMemo(memoID5, userID);
                    if (deleted) {
                        System.out.println("The memo has successfully been deleted.\n");
                    } else {
                        System.out.println("An error occurred, the memo was not deleted.");
                        System.out.println("Reminder: a memo cannot be attached to any events to be deleted.\n");
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

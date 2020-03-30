package views.notes;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import entities.Memo;
import views.LocalStorage;
import views.View;
import views.events.EventList;
import views.notes.NoteList;
import views.general.MainMenu;

import java.util.Scanner;

public class NoteMenu extends View {

    public NoteMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create memo");
        System.out.println("[2] List all events containing a specific memo");
        System.out.println("[3] List all memos");
        System.out.println("[4] Edit a memo");
        System.out.println("[5] Add/remove a memo from an event");
        System.out.println("[6] List all tags");
        System.out.println("[~] Return to main menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("User Menu");
        String userID = getLocalStorage().getUserID();
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    return null;
                case "2":
                    System.out.println("Enter memo name:");
                    String memoName = input.nextLine();
                    ListModel eventModel = getController().getEventsByMemoIDAndOwnerID(getController().getMemoByNameAndOwnerID(memoName, userID).getMemoID(), userID);
                    return new EventList(getLocalStorage(), eventModel, getController());
                case "3":
                    ListModel allMemos = getController().getMemosByOwnerID(userID);
                    return new NoteList(getLocalStorage(), allMemos, getController());
                case "4":
                    /*
                    System.out.println("Enter memo name:");
                    String memoName1 = input.nextLine();
                    String memoID = getController().getMemoByNameAndOwnerID(memoName1, userID).getMemoID();
                    System.out.println("Please select one of the following choices by entering a number:");
                    System.out.println("[1] Edit name");
                    System.out.println("[2] Edit note");
                    System.out.println("[~] Return to main menu");
                    String response = input.nextLine();
                    switch(response) {
                        case "1":
                            System.out.println("Enter new memo name:");
                            String newMemoName = input.nextLine();
                            boolean changed = getController().editMemoName(memoID, newMemoName, userID);
                            if (changed) {
                                System.out.println("The name has successfully been changed.");
                            } else {
                                System.out.println("An error occurred, the name was not changed.");
                            }
                        case "2":
                            System.out.println("Enter new memo note:");
                            String newMemoNote = input.nextLine();
                            boolean changed1 = getController().editMemoNote(memoID, memoName1, userID);
                            if (changed1) {
                                System.out.println("The note has successfully been changed.");
                            } else {
                                System.out.println("An error occurred, the note was not changed.");
                            }
                        case"~":
                            return new MainMenu(getLocalStorage(), null, getController());
                        default:
                            printInputError();
                    }
                     */
                case "5":
                    /*
                    System.out.println("Enter memo name:");
                    String memoName2 = input.nextLine();
                    String memoID2 = getController().getMemoByNameAndOwnerID(memoName2, userID).getMemoID();
                    System.out.println("Please select one of the following choices by entering a number:");
                    System.out.println("[1] Add to event");
                    System.out.println("[2] Remove from event");
                    System.out.println("[~] Return to main menu");
                    String answer = input.nextLine();
                    switch(answer) {
                        case "1":
                            System.out.println("Enter event name:");
                            String eventName = input.nextLine();
                            String eventID = getController().getEventByNameAndOwnerID(eventName, userID).getEventID();
                            boolean changed = getController().addMemoToEvent(userID, eventID, userID);
                            if (changed) {
                                System.out.println("The memo was successfully added to the event.");
                            } else {
                                System.out.println("An error occurred, the memo was not added to the event.");
                            }
                        case "2":
                            System.out.println("Enter new memo note:");
                            String newMemoNote = input.nextLine();
                            boolean changed1 = getController().editMemoNote(memoID, memoName1, userID);
                            if (changed1) {
                                System.out.println("The note has successfully been changed.");
                            } else {
                                System.out.println("An error occurred, the note was not changed.");
                            }
                        case"~":
                            return new MainMenu(getLocalStorage(), null, getController());
                        default:
                            printInputError();

                     */
                case "6":
                    ListModel allTags = getController().getTagsByOwnerID(userID);
                    return new NoteList(getLocalStorage(), allTags, getController());
                case "~":
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }
}


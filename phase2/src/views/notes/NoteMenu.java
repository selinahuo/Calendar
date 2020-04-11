package views.notes;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.general.MainMenu;

import java.util.Scanner;

public class NoteMenu extends View {

    public NoteMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create memo");
        System.out.println("[2] List all memos");
        System.out.println("[3] Add a memo to an event");
        System.out.println("[4] Create tag");
        System.out.println("[5] List all tags");
        System.out.println("[6] Add a tag to an event");
        System.out.println("[~] Return to main menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Note Menu");
        String userID = getLocalStorage().getUserID();
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("\n");
                    System.out.println("Please enter the name of the memo:");
                    String name = input.nextLine();
                    System.out.println("Please enter the memo's note:");
                    String memoNote = input.nextLine();
                    printClipBoard();
                    System.out.println("Please enter the ID of the event it will be attached to: (leave empty for no event)");
                    String eventID = input.nextLine();
                    String memoID = getController().createMemo(name, memoNote, userID);
                    if (memoID == null) {
                        printError("Something went wrong creating the memo.");
                        break;
                    }
                    if (!eventID.equals("")) {
                        boolean var = getController().addMemoToEvent(memoID, eventID, userID);
                        if (!var){
                            System.out.println("Something went wrong adding the memo to the event.");
                        }else{
                            System.out.println("The memo was successfully added to the event.");
                        }
                    }else{
                        System.out.println("The memo was not attached to an event.");
                    }
                    System.out.println("\n");
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "2":
                    System.out.println("\n");
                    ListModel allMemos = getController().getMemosByOwnerID(userID);
                    return new MemoList(getLocalStorage(), allMemos, getController());
                case "3":
                    System.out.println("\n");
                    System.out.println("Enter memo ID:");
                    String memoID2 = input.nextLine();
                    System.out.println("\n");
                    printClipBoard();
                    System.out.println("Enter event ID:");
                    String eventID1 = input.nextLine();
                    boolean changed2 = getController().addMemoToEvent(memoID2, eventID1, userID);
                    if (changed2) {
                        System.out.println("The memo was successfully added to the event.");
                    } else {
                        System.out.println("An error occurred, the memo was not added to the event.");
                    }
                    System.out.println("\n");
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "4":
                    System.out.println("\n");
                    System.out.println("Please enter the name of the tag:");
                    String tagName = input.nextLine();
                    printClipBoard();
                    System.out.println("Please enter the ID of the event it will be attached to: (leave empty for no event)");
                    String tagEventID = input.nextLine();
                    String tagID = getController().createTag(tagName, userID);
                    if (tagID == null) {
                        printError("Something went wrong creating the tag.");
                        break;
                    }
                    if (!tagEventID.equals("")){
                        boolean var1 = getController().addTagToEvent(tagID, tagEventID, userID);
                        if (!var1) {
                            System.out.println("Something went wrong adding the tag to the event.");
                        }else{
                            System.out.println("The tag was successfully added to the event.");
                        }
                    }else{
                        System.out.println("The tag was not attached to an event.");
                    }
                    System.out.println("\n");
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "5":
                    System.out.println("\n");
                    ListModel allTags = getController().getTagsByOwnerID(userID);
                    System.out.println("\n");
                    return new TagList(getLocalStorage(), allTags, getController());
                case "6":
                    System.out.println("\n");
                    System.out.println("Enter tag ID:");
                    String tagID2 = input.nextLine();
                    printClipBoard();
                    System.out.println("Enter event ID:");
                    String eventID2 = input.nextLine();
                    boolean changed3 = getController().addTagToEvent(tagID2, eventID2, userID);
                    if (changed3) {
                        System.out.println("The tag was successfully added to the event.");
                    } else {
                        System.out.println("An error occurred, the tag was not added to the event.");
                    }
                    System.out.println("\n");
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "~":
                    System.out.println("\n");
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();

            }
        }
    }
}


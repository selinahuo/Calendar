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
                    System.out.println("Please enter the name of the memo:");
                    String name = input.nextLine();
                    System.out.println("Please enter the memo's note:");
                    String memoNote = input.nextLine();
                    System.out.println("Please enter the ID of the event it will be attached to:");
                    String eventID = input.nextLine();
                    String memoID = getController().createMemo(name, memoNote, userID);
                    System.out.println(memoID);
                    if (memoID == null) {
                        printError("Something went wrong creating the memo.");
                        break;
                    }
                    boolean var = getController().addMemoToEvent(memoID, eventID, userID);
                    if (!var){
                        getController().deleteMemo(memoID, userID);
                        System.out.println("Something went wrong adding the memo to the event.");
                    }
                    System.out.println("\n");
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "2":
                    System.out.println("\n");
                    ListModel allMemos = getController().getMemosByOwnerID(userID);
                    return new MemoList(getLocalStorage(), allMemos, getController());
                case "3":
                    System.out.println("Enter memo ID:");
                    String memoID2 = input.nextLine();
                    System.out.println("Enter event ID:");
                    String eventID1 = input.nextLine();
                    boolean changed2 = getController().addMemoToEvent(memoID2, eventID1, userID);
                    if (changed2) {
                        System.out.println("The memo was successfully added to the event.");
                    } else {
                        System.out.println("An error occurred, the memo was not added to the event.");
                    }
                    case "4":
                        System.out.println("Please enter the name of the tag:");
                        String tagName = input.nextLine();
                        System.out.println("Please enter the ID of the event it will be attached to:");
                        String tagEventID = input.nextLine();
                        String tagID = getController().createTag(tagName, userID);
                        if (tagID == null) {
                            printError("Something went wrong creating the tag.");
                            break;
                        }
                        boolean var1 = getController().addTagToEvent(tagID, tagEventID, userID);
                        if (!var1) {
                            getController().deleteTag(tagID, userID);
                            System.out.println("Something went wrong adding the tag to the event.");
                        }
                        return new NoteMenu(getLocalStorage(), null, getController());
                    case "5":
                        ListModel allTags = getController().getTagsByOwnerID(userID);
                        return new TagList(getLocalStorage(), allTags, getController());
                    case "~":
                        return new MainMenu(getLocalStorage(), null, getController());
                    default:
                        printInputError();

            }
        }
    }
}


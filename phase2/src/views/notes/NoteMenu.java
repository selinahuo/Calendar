package views.notes;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.general.MainMenu;

import java.util.Scanner;

/**
 * View dedicated to displaying and handling memo entity functionality
 */
public class NoteMenu extends View {

    /**
     * Create an EventMenu view
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model view model used by this view, no model necessary for menu
     * @param controller controller the view interacts with
     */
    public NoteMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }


    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create memo");
        System.out.println("[2] List all memos");
        System.out.println("[3] Create tag");
        System.out.println("[4] List all tags");
        System.out.println("[~] Return to main menu");
    }

    /**
     * Displays and handles event entity interaction options. Options include, creating memos and tags, listing memos
     * and tags.
     * Allows user to navigate back to the main menu.
     *
     * @return next view; main menu, another note menu, tag list or an memo list.
     */
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
                        if (!var) {
                            System.out.println("Something went wrong adding the memo to the event.");
                        } else {
                            System.out.println("The memo was successfully added to the event.");
                        }
                    } else {
                        System.out.println("The memo was not attached to an event.");
                    }
                    System.out.println("");
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "2":
                    ListModel allMemos = getController().getMemosByOwnerID(userID);
                    System.out.println("");
                    return new MemoList(getLocalStorage(), allMemos, getController());
                case "3":
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
                    if (!tagEventID.equals("")) {
                        boolean var1 = getController().addTagToEvent(tagID, tagEventID, userID);
                        if (!var1) {
                            System.out.println("Something went wrong adding the tag to the event.");
                        } else {
                            System.out.println("The tag was successfully added to the event.");
                        }
                    } else {
                        System.out.println("The tag was not attached to an event.");
                    }
                    System.out.println("");
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "4":
                    ListModel allTags = getController().getTagsByOwnerID(userID);
                    System.out.println("");
                    return new TagList(getLocalStorage(), allTags, getController());
                case "~":
                    System.out.println("");
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();

            }
        }
    }
}


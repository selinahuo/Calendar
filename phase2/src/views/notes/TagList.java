package views.notes;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.events.EventList;

import java.util.Scanner;

public class TagList extends ListView {

    public TagList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Display all events attached to this tag");
        System.out.println("[2] Delete tag");
        System.out.println("[~] Back to Note menu");
    }

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
                    String tagID1 = input.nextLine();
                    boolean deleted = getController().deleteTag(tagID1, userID);
                    if (deleted){
                        System.out.println("The tag has successfully been deleted.");
                    }else{
                        System.out.println("An error occurred, the tag was not deleted.");
                    }
                    System.out.println("\n");
                    return new NoteMenu(getLocalStorage(), null, getController());
                case "~":
                    return new NoteMenu(getLocalStorage(), null, getController());
                default:
                    super.printInputError();
            }
        }
    }
}

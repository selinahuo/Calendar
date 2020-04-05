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
        System.out.println("[~] Back to Note menu");
    }

    @Override
    public View run() {
        printTitle("Memo List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Enter memo ID:");
                    String memoID3 = input.nextLine();
                    ListModel eventModel = getController().getEventsByMemoIDAndOwnerID(memoID3, getLocalStorage().getUserID());
                    return new EventList(getLocalStorage(), eventModel, getController());
                case "~":
                    return new NoteMenu(getLocalStorage(), null, getController());
                default:
                    super.printInputError();
            }
        }
    }
}

package views.notes;

import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;

import java.util.Scanner;

public class TagList extends ListView {

    public TagList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
//        System.out.println("[1] View individual tag");
        System.out.println("[~] Back to Note menu");
    }

    @Override
    public View run() {
        printTitle("Tag List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    return null;
                case "~":
                    return new NoteMenu(getLocalStorage(), null, getController());
                default:
                    super.printInputError();
            }
        }
    }
}

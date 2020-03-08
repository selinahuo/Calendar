package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import entities.Memo;
import view.LocalStorage;
import view.views.HomeView;
import view.views.View;
import view.views.event.EventListView;

import java.util.Scanner;

public class MemoMenuView extends View {
    public MemoMenuView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }


    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get memos");
        System.out.println("[2] Get events by Memo ID");
        System.out.println("[3] Create a new Memo");
        System.out.println("[4] Attach a memo to an event");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        super.printTitle("Memo Menu");
        Scanner input = new Scanner(System.in);
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    ListModel model = getController().getMemos(super.getLocalStorage().getUserID());
                    return new MemoListView(getLocalStorage(), model, getController());
                case "2":
                    System.out.println("Please enter the memo's ID:");
                    String memoID = input.nextLine();
                    ListModel eventModel = getController().getEventsByMemoID(memoID, getLocalStorage().getUserID());
                    return new EventListView(getLocalStorage(), eventModel, getController());
                case "3":
                    return new CreateNewMemo(getLocalStorage(), getModel(), getController());
                case "4":
                    return new AttachMemoToEvent(getLocalStorage(), getModel(), getController());
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }


}

package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.View;

import java.util.Scanner;

public class AttachMemoToEvent extends View{

    public AttachMemoToEvent(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Attach Memo to Event");
        System.out.println("MemoID:");
        String memoID = input.nextLine();
        System.out.println("EventID:");
        String eventID = input.nextLine();
        boolean success = super.getController().attachMemoToEvent(memoID,eventID,getLocalStorage().getUserID());
        if (success) {
            System.out.println("Memo was attached to the event successfully.");
        } else {
            super.printError("Something went wrong with Memo attachment");
        }
        return new MemoMenuView(super.getLocalStorage(), super.getModel(), super.getController());
    }
}
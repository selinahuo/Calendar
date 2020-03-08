package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.View;

import java.util.Scanner;

public class CreateNewMemo extends View {
    public CreateNewMemo(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Create Memo");
        System.out.println("New Memo name:");
        String memoName = input.nextLine();
        System.out.println("New Note:");
        String memoNote = input.nextLine();
        boolean success = super.getController().createMemo(memoName, memoNote, getLocalStorage().getUserID());
        if (success) {
            System.out.println("Memo was created successfully.");
        } else {
            super.printError("Something went wrong with Memo creation");
        }
        return new MemoMenuView(super.getLocalStorage(), super.getModel(), super.getController());
    }
}

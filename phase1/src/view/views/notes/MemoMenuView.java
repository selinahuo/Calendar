package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.HomeView;
import view.views.View;

import java.util.Scanner;

public class MemoMenuView extends View {
    public MemoMenuView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }


    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get Memo");
        System.out.println("[2] Get Event by Memo ID");
        System.out.println("[3] Create a new Memo");
        System.out.println("[4] Attach Memo to Event");
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
                    return null;
                case "2":
                    return null;
                case "3":
                    return null;
                case "4":
                    return null;
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }


}

package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.HomeView;
import view.views.View;

import java.util.Scanner;

public class NoteMenuView extends View {
    public NoteMenuView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }


    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Memo Menu");
        System.out.println("[2] Tag Menu");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        super.printTitle("Note Menu");
        Scanner input = new Scanner(System.in);
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    return new MemoMenuView(super.getLocalStorage(), super.getModel(),super.getController());
                case "2":
                    return new TagMenuView(super.getLocalStorage(), super.getModel(), super.getController());
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }


}

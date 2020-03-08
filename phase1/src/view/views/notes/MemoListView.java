package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import entities.CalendarEvent;
import entities.Memo;
import view.LocalStorage;
import view.views.HomeView;
import view.views.ListView;
import view.views.View;
import java.util.Scanner;

public class MemoListView extends ListView {
    public MemoListView(LocalStorage localStorage, ListModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }
    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        printTitle("Memo List:");
        Scanner input = new Scanner(System.in);

        for (String memo: getModel().getList()) {
            System.out.println(memo);
        }
        System.out.println("");

        while (true) {
            inputPrompt();

            String selection = input.nextLine();
            switch(selection) {
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}

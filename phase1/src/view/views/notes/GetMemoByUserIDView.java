package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import entities.CalendarEvent;
import entities.Memo;
import view.LocalStorage;
import view.views.HomeView;
import view.views.View;
import java.util.Scanner;

public class GetMemoByUserIDView extends View {
    public GetMemoByUserIDView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }
    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get Events by Memo ID");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        printTitle("Event List");
        Scanner input = new Scanner(System.in);

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    Memo[] memos = getController().getMemos(super.getLocalStorage().getUserID());
                    if (memos == null){
                    super.printError("Please enter the MemoID again");
                    return new GetEventByMemoID(getLocalStorage(), getModel(), getController());
                } else {
                    for(Memo memo: memos){
                        System.out.println(memo);
                    }
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                }
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}

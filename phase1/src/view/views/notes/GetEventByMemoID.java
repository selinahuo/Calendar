package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import entities.CalendarEvent;
import view.LocalStorage;
import view.views.HomeView;
import view.views.View;

import java.util.Scanner;

public class GetEventByMemoID extends View {
    public GetEventByMemoID(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }
    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] View Events in the MemoID");
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
                    super.printTitle("Get Event by MemoID");
                    System.out.println("Enter MemoID:");
                    String memoID = input.nextLine();
                    CalendarEvent[] events = super.getController().getEventsByMemoID(memoID, super.getLocalStorage().getUserID());
                    if (events == null){
                        super.printError("Please enter the MemoID again");
                        return new GetEventByMemoID(getLocalStorage(), getModel(), getController());
                    } else {
                        for(CalendarEvent event: events){
                            System.out.println(event);
                        }
                    }
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}

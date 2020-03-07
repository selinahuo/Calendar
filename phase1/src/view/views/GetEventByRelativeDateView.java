package view.views;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import view.LocalStorage;

import java.util.Scanner;

public class GetEventByRelativeDateView extends View{
    public GetEventByRelativeDateView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get past events");
        System.out.println("[2] Get current events");
        System.out.println("[3] Get future events");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Get event by relative date:");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            ListModel model = null;
            switch(selection) {
                case "1":
                    super.printTitle("Past events:");
                    model = getController().getPastEvents(getLocalStorage().getUserID());
                    break;
                case "2":
                    super.printTitle("Current events:");
                    model = getController().getCurrentEvents(getLocalStorage().getUserID());
                    break;
                case "3":
                    super.printTitle("Future events:");
                    model = getController().getFutureEvents(getLocalStorage().getUserID());
                    break;
                case "~":
                    return new HomeView(getLocalStorage(), getModel(), getController());
                default:
                    super.printInputError();
                    break;
            }
            if (model != null) {
                return new EventListView(getLocalStorage(), model, getController());
            }
        }
    }
}

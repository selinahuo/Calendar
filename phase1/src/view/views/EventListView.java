package view.views;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.SingularEventModel;
import view.LocalStorage;

import java.util.Scanner;

public class EventListView extends ListView {
    public EventListView(LocalStorage localStorage, ListModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] View individual event");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        printTitle("Event List");
        Scanner input = new Scanner(System.in);

        for (String event: getModel().getList()) {
            System.out.println(event);
        }
        System.out.println("");

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Please enter the event's ID:");
                    String eventID = input.nextLine();
                    SingularEventModel model = getController().getSingularEvent(eventID, getLocalStorage().getUserID());
                    if (model == null) {
                        super.printError("That event could not be found.");
                    } else {
                        return new SingularEventView(getLocalStorage(), model, getController());
                    }
                    break;
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}

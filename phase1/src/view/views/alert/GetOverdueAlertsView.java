package view.views.alert;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import view.LocalStorage;
import view.views.HomeView;
import view.views.View;
import view.views.ListView
import view.views.event.SingularEventView;

import java.util.Scanner;

public class GetOverdueAlertsView extends ListView {
    public GetOverdueAlertsView(LocalStorage localStorage, ListModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] View overdue alerts");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        printTitle("Overdue Alerts");
        Scanner input = new Scanner(System.in);

        for (String alert: getModel().getList()) {
            System.out.println(alert);
        }
        System.out.println("");

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Please enter userID)");
                    String userID = input.nextLine();
                    if (userID == null) {
                        super.printError("That user could not be found.");
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

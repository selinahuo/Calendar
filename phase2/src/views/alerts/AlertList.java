package views.alerts;
import controller.Controller;
import controller.viewmodels.ListModel;
import views.ListView;
import views.LocalStorage;
import views.View;
import views.alerts.AlertMenu;
import views.events.EventMenu;

import java.util.Scanner;

public class AlertList extends ListView {
    public AlertList(LocalStorage localStorage, ListModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[~] Back to Alert menu");
    }

    @Override
    public View run() {
        printTitle("Alert List");
        Scanner input = new Scanner(System.in);

        printList();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch (selection) {
                case "1":
                    return null;
                case "~":
                    return new AlertMenu(getLocalStorage(), null, getController());
                default:
                    super.printInputError();
            }
        }
    }
}
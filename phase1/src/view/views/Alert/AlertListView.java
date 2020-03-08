package view.views.alert;


import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.SingularEventModel;
import view.LocalStorage;
import view.views.HomeView;
import view.views.ListView;
import view.views.View;
import view.views.event.SingularEventView;

import java.util.Scanner;

public class AlertListView extends ListView {
    public AlertListView(LocalStorage localStorage, ListModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        printTitle("Alert List");
        Scanner input = new Scanner(System.in);

        for (String event : getModel().getList()) {
            System.out.println(event);
        }
        System.out.println("");

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch (selection) {
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}
package view.views;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import view.LocalStorage;

import java.util.Scanner;

public class GetEventByNameView extends View {
    public GetEventByNameView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Get Event by Name");
        System.out.println("Enter event name:");
        String eventName = input.nextLine();
        ListModel model = super.getController().getEventsByName(eventName, getLocalStorage().getUserID());
        if (model != null) {
            return new EventListView(getLocalStorage(), model, getController());
        } else {
            super.printError("Something went wrong with event creation");
        }
        return new EventMenuView(super.getLocalStorage(), super.getModel(), super.getController());
    }
}

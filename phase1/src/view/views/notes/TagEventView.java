package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.View;
import view.views.event.EventMenuView;

import java.util.Scanner;

public class TagEventView extends View {

    public TagEventView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Tag Event");
        System.out.println("Event ID:");
        String eventID = input.nextLine();
        System.out.println("Tag Name:");
        String tagName = input.nextLine();
        boolean success = super.getController().tagEvent(eventID,tagName,getLocalStorage().getUserID());
        if (success) {
            System.out.println("Event was tagged successfully.");
        } else {
            super.printError("Something went wrong with event tagging process");
        }
        return new TagMenuView(super.getLocalStorage(), super.getModel(), super.getController());
    }
}

package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.HomeView;
import view.views.View;
import view.views.event.EventListView;

import java.util.Scanner;

public class TagMenuView extends View {
    public TagMenuView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }


    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get Event by Tag name");
        System.out.println("[2] Tag Event");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        super.printTitle("Tag Menu");
        Scanner input = new Scanner(System.in);
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Please enter a tag name:");
                    String tagName = input.nextLine();
                    ListModel model = getController().getEventsByTagName(tagName, getLocalStorage().getUserID());
                    return new EventListView(getLocalStorage(), model, getController());
                case "2":
                    return new TagEventView(super.getLocalStorage(), super.getModel(), super.getController());
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }


}

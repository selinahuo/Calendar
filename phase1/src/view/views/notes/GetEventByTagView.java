package view.views.notes;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import entities.CalendarEvent;
import view.LocalStorage;
import view.views.HomeView;
import view.views.View;

import java.util.Scanner;

public class GetEventByTagView extends View {
    public GetEventByTagView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }
    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get Event by Tag name");
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
                    super.printTitle("Get Event by Tag name");
                    System.out.println("Enter TagID:");
                    String tagName = input.nextLine();
                    CalendarEvent[] events = super.getController().getEventsByTagName(tagName, super.getLocalStorage().getUserID());
                    if (events == null){
                        super.printError("Please enter the Tag name again");
                        return new GetEventByTagView(getLocalStorage(), getModel(), getController());
                    } else {
                        for(CalendarEvent event: events){
                            System.out.println(event);
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

package view.views.series;

import controller.CommandLineController;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.HomeView;
import view.views.View;
import view.views.event.EventListView;

import java.util.Scanner;

public class SeriesMenuView extends View {
    public SeriesMenuView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get events by series name");
        System.out.println("[2] Create a series from existing events");
        System.out.println("[3] Create a series from an event formula");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Event Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Please enter a series name:");
                    String seriesName = input.nextLine();
                    ListModel model = getController().getEventsBySeriesName(seriesName, getLocalStorage().getUserID());
                    return new EventListView(getLocalStorage(), model, getController());
                case "2":
                    return new CreateSeriesFromExistingEventsView(getLocalStorage(), getModel(), getController());
                case "3":
                    return new CreateSeriesFromFormulaView(super.getLocalStorage(), super.getModel(), super.getController());
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}

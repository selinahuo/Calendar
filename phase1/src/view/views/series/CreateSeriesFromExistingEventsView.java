package view.views.series;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;
import view.views.View;

import java.util.Scanner;

public class CreateSeriesFromExistingEventsView extends View {
    public CreateSeriesFromExistingEventsView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Create series from existing events");
        System.out.println("New series name:");
        String seriesName = input.nextLine();
        System.out.println("Enter eventIDs separated by commas (no spaces)");
        String eventIDString = input.nextLine();
        boolean success = super.getController().createSeriesFromEvents(eventIDString, seriesName, getLocalStorage().getUserID());
        if (success) {
            System.out.println("Series was created successfully.");
        } else {
            super.printError("Something went wrong with series creation");
        }
        return new SeriesMenuView(super.getLocalStorage(), super.getModel(), super.getController());
    }
}

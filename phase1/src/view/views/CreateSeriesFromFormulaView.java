package view.views;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;

import java.util.Scanner;

public class CreateSeriesFromFormulaView extends View {
    public CreateSeriesFromFormulaView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Create series from formula");
        System.out.println("New series name:");
        String seriesName = input.nextLine();
        System.out.println("First event's start time (YYYY-MM-DD-HH-MM):");
        String startStr = input.nextLine();
        System.out.println("First event's end time (YYYY-MM-DD-HH-MM):");
        String endStr = input.nextLine();
        System.out.println("Repeat every? (d - daily or w - weekly):");
        String frequency = input.nextLine();
        System.out.println("Number of events? (integer > 0)");
        int numEvents = input.nextInt();
        boolean success = super.getController().createSeriesFromFormula(seriesName, startStr, endStr, frequency,
                numEvents, getLocalStorage().getUserID());
        if (success) {
            System.out.println("Series was created successfully.");
        } else {
            super.printError("Something went wrong with series creation");
        }
        return new SeriesMenuView(super.getLocalStorage(), super.getModel(), super.getController());
    }
}

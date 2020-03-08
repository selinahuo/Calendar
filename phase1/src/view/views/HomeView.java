package view.views;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;

import view.views.event.EventMenuView;
import view.views.series.SeriesMenuView;

import java.util.Scanner;

public class HomeView extends View {
    public HomeView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Events");
        System.out.println("[2] Alerts");
        System.out.println("[3] Series");
        System.out.println("[4] Notes");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Home");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    return new EventMenuView(super.getLocalStorage(), super.getModel(), super.getController());
                case "2":
                    return new AlertMenuView(super.getLocalStorage(), super.getModel(), super.getController());
                case "3":
                    return new SeriesMenuView(getLocalStorage(), getModel(), getController());
                case "4":
                    System.out.println("Notes");
                    break;
                default:
                    super.printInputError();
            }
        }
    }
}

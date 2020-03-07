package view.views;

import controller.CommandLineController;
import controller.viewmodels.SingularEventModel;
import view.LocalStorage;

import java.util.Scanner;

public class SingularEventView extends View {
    private SingularEventModel model;

    public SingularEventView(LocalStorage localStorage, SingularEventModel model, CommandLineController controller) {
        super(localStorage, model, controller);
        this.model = model;
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        printTitle("Single Event");
        Scanner input = new Scanner(System.in);


        System.out.println(model.display());
        System.out.println("");

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}

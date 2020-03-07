package view.views;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;

import java.util.Scanner;

public class EventGetEventView extends View{

    public EventGetEventView(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following menus by typing the number:");
        System.out.println("[1] Get past event");
        System.out.println("[2] Get ongoing event");
        System.out.println("[3] Get future event");
        System.out.println("[4] Check individual event");
        System.out.println("[~] Go Home");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        super.printTitle("Show Event");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Past events:");
                    super.getController().getPastEvents(super.getLocalStorage().getUserID());
                    System.out.println("Type [<] for back to the Event Menu");
                    String secSelection = input.nextLine();
                    if (secSelection.equals("<")){
                        return new EventGetEventView(super.getLocalStorage(), super.getModel(), super.getController());
                    }
                case "2":
                    System.out.println("Current events:");
                    System.out.println(super.getController().getCurrentEvents(super.getLocalStorage().getUserID()));
                    System.out.println("Type [<] for back to the Event Menu");
                    String thirdSelection = input.nextLine();
                    if (thirdSelection.equals("<")){
                        return new EventMenuView(super.getLocalStorage(), super.getModel(), super.getController());
                    }
                case "3":
                    System.out.println("Future events");
                    System.out.println(super.getController().getFutureEvents(super.getLocalStorage().getUserID()));
                    System.out.println("Type [<] for back to the Event Menu");
                    String forthSelection = input.nextLine();
                    if (forthSelection.equals("<")){
                        return new EventMenuView(super.getLocalStorage(), super.getModel(), super.getController());
                    }
                case "4":
                    System.out.println("Enter event id:" +input.nextLine());
                case "~":
                    return new HomeView(super.getLocalStorage(), super.getModel(), super.getController());
                default:
                    super.printInputError();
            }
        }
    }
}

package views.events;

import controller.Controller;
import controller.viewmodels.SingularModel;
import views.BrowserIntegrations;
import views.LocalStorage;
import views.SingularView;
import views.View;

import java.util.Scanner;

public class SingularEvent extends SingularView {
    public SingularEvent(LocalStorage localStorage, SingularModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get event directions (using location)");
        System.out.println("[2] Get event weather (using location)");
        System.out.println("[3] Share event on Twitter");
        System.out.println("[4] Share event using Email");
        if (getLocalStorage().getUserID().equals(getModel().getEntityOwner())) {
            System.out.println("[e1] Edit name");
            System.out.println("[e2] Edit time");
            System.out.println("[e3] Edit location");
            System.out.println("[d] Delete event");
        }
        System.out.println("[~] Return to event menu");
    }

    @Override
    public View run() {
        printTitle("Event Single");
        Scanner input = new Scanner(System.in);

        printSingular();

        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    boolean dirSuccess = BrowserIntegrations.openWithBrowser(getController().getEventDirections(getModel().getEntityID()));
                    if (!dirSuccess) {
                        printError("Something went wrong opening your browser.");
                    }
                    break;
                case "2":
                    boolean weatherSuccess = BrowserIntegrations.openWithBrowser(getController().getEventWeather(getModel().getEntityID()));
                    if (!weatherSuccess) {
                        printError("Something went wrong opening your browser.");
                    }
                    break;
                case "3":
                    boolean twitterSuccess = BrowserIntegrations.openWithBrowser(getController().getEventTwitterShare(getModel().getEntityID()));
                    if (!twitterSuccess) {
                        printError("Something went wrong opening your browser.");
                    }
                    break;
                case "4":
                    boolean emailSuccess = BrowserIntegrations.openWithBrowser(getController().getEventEmailShare(getModel().getEntityID()));
                    if (!emailSuccess) {
                        printError("Something went wrong opening your browser.");
                    }
                    break;
                case "e1":
                    if (getLocalStorage().getUserID().equals(getModel().getEntityOwner())) {
                        System.out.println("Enter new name:");
                        String newName = input.nextLine();
                        getController().editEventName(getModel().getEntityID(), newName, getLocalStorage().getUserID());
                        System.out.println("Event edited\n");
                        SingularModel nameModel = getController().getSingularEvent(getModel().getEntityID(), getLocalStorage().getUserID());
                        return new SingularEvent(getLocalStorage(), nameModel, getController());
                    }
                    printInputError();
                    break;
                case "e2":
                    if (getLocalStorage().getUserID().equals(getModel().getEntityOwner())) {
                        System.out.println("Enter new start time (yyyy-MM-dd HH:mm):");
                        String newStart = input.nextLine();
                        System.out.println("Enter new end time (yyyy-MM-dd HH:mm):");
                        String newEnd = input.nextLine();
                        getController().editEventTime(getModel().getEntityID(), newStart, newEnd, getLocalStorage().getUserID());
                        System.out.println("Event edited\n");
                        SingularModel timeModel = getController().getSingularEvent(getModel().getEntityID(), getLocalStorage().getUserID());
                        return new SingularEvent(getLocalStorage(), timeModel, getController());
                    }
                    printInputError();
                    break;
                case "e3":
                    if (getLocalStorage().getUserID().equals(getModel().getEntityOwner())) {
                        System.out.println("Enter new location:");
                        String newLocation = input.nextLine();
                        getController().editEventLocation(getModel().getEntityID(), newLocation, getLocalStorage().getUserID());
                        System.out.println("Event edited\n");
                        SingularModel locationModel = getController().getSingularEvent(getModel().getEntityID(), getLocalStorage().getUserID());
                        return new SingularEvent(getLocalStorage(), locationModel, getController());
                    }
                    printInputError();
                    break;
                case "d":
                    if (getLocalStorage().getUserID().equals(getModel().getEntityOwner())) {
                        getController().deleteEvent(getModel().getEntityID(), getLocalStorage().getUserID());
                        System.out.println("Event deleted\n");
                        return new EventMenu(getLocalStorage(), null, getController());
                    }
                    printInputError();
                    break;
                case "~":
                    System.out.println("");
                    return new EventMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }
}

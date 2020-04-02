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
            System.out.println("[1] Delete event");
        } else {
            System.out.println("[a] Accept invitation");
            System.out.println("[d] Decline invitation");
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
                case "a":
//                    if (getModel().getAccept() == null) {
//                        System.out.println("Enter your response:");
//                        String acceptingResponse = input.nextLine();
//                        getController().acceptInvitation(getModel().getEntityID(), acceptingResponse, true, getLocalStorage().getUserID());
//
//                        System.out.println("");
//                        SingularInvitationModel acceptModel = getController().getSingularInvitation(getModel().getEntityID(), getLocalStorage().getUserID());
//                        return new SingularEvent(getLocalStorage(), acceptModel, getController());
//                    }
//                    printInputError();
//                    break;
                case "d":
//                    if (getModel().getAccept() == null) {
//                        System.out.println("Enter your response:");
//                        String declineResponse = input.nextLine();
//                        getController().acceptInvitation(getModel().getEntityID(), declineResponse, false, getLocalStorage().getUserID());
//
//                        System.out.println("");
//                        SingularInvitationModel acceptModel = getController().getSingularInvitation(getModel().getEntityID(), getLocalStorage().getUserID());
//                        return new SingularEvent(getLocalStorage(), acceptModel, getController());
//                    }
//                    printInputError();
//                    break;
                case "~":
                    System.out.println("");
                    return new EventMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }
}

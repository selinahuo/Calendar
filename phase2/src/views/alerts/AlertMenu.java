package views.alerts;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.general.MainMenu;
import java.util.Scanner;

/**
 * A view for display and handle the options for alert menu.
 * Functions including:
 * Get overdue Alerts, and showing List of alerts.
 */
public class AlertMenu extends View{

    /**
     * A constructor for the Alert Menu.
     *
     * @param localStorage local storage that saves current user's information
     * @param model view model
     * @param controller general controller that this view calls
     */
    public AlertMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt(){
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Get Overdue Alerts");
        System.out.println("[2] List Alerts");
        System.out.println("[3] Create a new Alert");
        System.out.println("[~] Return to main menu");
    }

    /**
     * Displays and handles alert entity interaction options. Options include, acknowledging alerts, listing alerts,
     * creating alerts.
     *
     * @return next view, main menu, or an alert list.
     */
    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Alert Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    ListModel overdueModel = getController().getOverdueAlerts(getLocalStorage().getUserID());
                    System.out.println("");
                    if (overdueModel != null) {
                        return new AlertList(getLocalStorage(), overdueModel, getController());
                    }
                    else {
                        printError("Oops, Something went wrong.");
                        return new AlertMenu(getLocalStorage(),getModel(),getController());
                    }
                case "2":
                    System.out.println("");
                    ListModel alertModel = getController().getAlertsByUserID(getLocalStorage().getUserID());
                    return new AlertList(getLocalStorage(), alertModel, getController());
                case "3":
                    System.out.println("");
                    return new CreateAlertView(getLocalStorage(), getModel(), getController());
                case "~":
                    System.out.println("");
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }
}
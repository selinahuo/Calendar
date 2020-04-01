package views.alerts;
import controller.Controller;

import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;

import views.general.MainMenu;

import java.time.LocalDateTime;
import java.util.Scanner;


public class EditAlertView extends View{
    public EditAlertView(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt(){
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Edit the Name of an Alert");
        System.out.println("[2] Edit the time of an Individual Alert");
        System.out.println("[~] Return to Alert menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Edit Alert Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Enter Alert ID:");
                    String alertID = input.nextLine();
                    System.out.println("Enter the new name for this alert:");
                    String newName = input.nextLine();
                    boolean modified = getController().editAlertName(alertID, newName, getLocalStorage().getUserID());
                    if (modified){
                        System.out.println("The Alert is being modified:");
                        System.out.println(getController().getAlertByIDAndUserID(alertID,getLocalStorage().getUserID()));
                    } else {
                        System.out.println("The Alert name change was not complete");
                    }
                    return new AlertMenu(getLocalStorage(),getModel(),getController());
                case "2":
                    System.out.println("Enter Alert ID:");
                    String EditTimeAlertID = input.nextLine();
                    // The new alert time
                    System.out.println("Enter new time year:");
                    int year = input.nextInt();
                    System.out.println("Enter new time month:");
                    int month = input.nextInt();
                    System.out.println("Enter new time day:");
                    int day = input.nextInt();
                    System.out.println("Enter new time hour:");
                    int hour = input.nextInt();
                    System.out.println("Enter new time minute:");
                    int minute = input.nextInt();
                    input.nextLine();
                    System.out.println("");
                    LocalDateTime alertTime = getController().alertTimeFromInteger(year,month,day,hour,minute);

                    boolean changed = getController().editAlertTimeAsIndividual(EditTimeAlertID, alertTime, getLocalStorage().getUserID());
                    if (changed) {
                        System.out.println("the new alert time is modified: ");
                        System.out.println(getController().getAlertByIDAndUserID(EditTimeAlertID,getLocalStorage().getUserID()));
                    } else {
                        System.out.println("Something went wrong.");
                    }
                    return new AlertMenu(getLocalStorage(),getModel(),getController());
                case "~":
                    return new AlertMenu(getLocalStorage(),getModel(),getController());
                default:
                    printInputError();
            }
        }
    }
}

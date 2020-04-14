package views;

import controller.Controller;
import controller.viewmodels.ViewModel;

/**
 * Abstract view responsible for user output and input
 */
abstract public class View {
    private final String BAD_INPUT = "Could not process your input, please try again\n";
    private final String TITLE = "\u001B[34m";
    private final String ERROR = "\u001B[31m";
    private final String RESET = "\u001B[0m";
    private final String CLIPBOARD = "\u001B[95m";

    private LocalStorage localStorage;
    private ViewModel model;
    private Controller controller;

    /**
     * Create a View
     *
     * @param localStorage session storage for views, similar to browser local storage
     * @param model view model used by this view
     * @param controller controller the view interacts with
     */
    public View(LocalStorage localStorage, ViewModel model, Controller controller) {
        this.localStorage = localStorage;
        this.model = model;
        this.controller = controller;
    }

    /**
     * Get this view's view model
     *
     * @return view's view model
     */
    public ViewModel getModel() {
        return model;
    }

    /**
     * Get this view's local storage
     *
     * @return view's local storage
     */
    public LocalStorage getLocalStorage() {
        return localStorage;
    }

    /**
     * Get this view's controller
     *
     * @return view's controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Display information to user. Can be dynamically set by view models supplied by controller/presenter.
     * Accept user input to interact with controller.
     * Gives user options for navigation to other views.
     *
     * @return the next view instance that is navigated to
     */
    public abstract View run();

    /**
     * Prints clipboard for storing entity IDs
     */
    public void printClipBoard() {
        String userID = localStorage.getClipUser();
        String eventID = localStorage.getClipEvent();
        String calendarID = localStorage.getClipCalendar();
        String clipString = "Clipboard: ";
        if (userID != "") {
            clipString += String.format("| User: %s%s%s ", CLIPBOARD, userID, RESET);
        }
        if (eventID != "") {
            clipString += String.format("| Event: %s%s%s ", CLIPBOARD, eventID, RESET);
        }
        if (calendarID != "") {
            clipString += String.format("| Calendar: %s%s%s ", CLIPBOARD, calendarID, RESET);
        }
        System.out.println(clipString + "\n");
    }

    /**
     * Prints output as a title in the view
     *
     * @param output output to print as title
     */
    public void printTitle(String output) {
        System.out.println(TITLE + output + RESET);
    }

    /**
     * Prints output as an error in the view
     *
     * @param output output to print as error
     */
    public void printError(String output) {
        System.out.println(ERROR + output + RESET);
    }

    /**
     * Prints an input error for user gives bad input
     */
    public void printInputError() {
        printError(BAD_INPUT);
    }
}

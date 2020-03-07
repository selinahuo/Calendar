package view.views;

import controller.CommandLineController;
import controller.viewmodels.ViewModel;
import view.LocalStorage;

abstract public class View {
    private final String BAD_INPUT = "Could not process your input, please try again";
    private final String TITLE = "\u001B[34m";
    private final String ERROR = "\u001B[31m";
    private final String RESET = "\u001B[0m";

    private LocalStorage localStorage;
    private ViewModel model;
    private CommandLineController controller;

    public View(LocalStorage localStorage, ViewModel model, CommandLineController controller) {
        this.localStorage = localStorage;
        this.model = model;
        this.controller = controller;
    }

    public ViewModel getModel() {
        return model;
    }

    public LocalStorage getLocalStorage() {
        return localStorage;
    }

    public CommandLineController getController() {
        return controller;
    }

    public abstract View run();

    public void printTitle(String output) {
        System.out.println(TITLE + output + RESET);
    }

    public void printError(String output) {
        System.out.println(ERROR + output + RESET);
    }

    public void printInputError() {
        printError(BAD_INPUT);
    }
}

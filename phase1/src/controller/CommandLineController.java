package controller;

import controller.viewmodels.ListModel;
import entities.CalendarEvent;
import usecases.IUseCaseManager;

public class CommandLineController {
    private IUseCaseManager useCaseManager;
//
//    public CommandLineController(IUseCaseManager useCaseManager) {
//        this.useCaseManager = useCaseManager;
//    }

    public String authenticateUser(String username, String password) {
        if (username.equals("Michael") && password.equals("123")) {
            return "userID";
        }
        return null;
    }

    public ListModel getEventsByName(String eventName, String userID) {
        return new ListModel(new String[0]);
        // call the use case list of events
        // convert each event to a string add it to an array
        // return a list model using that array
    }

    public ListModel getPastEvents(String userID) {
        CalendarEvent[] events = this.useCaseManager.getPastEvents(userID);
        return new ListModel(new String[0]);
    }

    public ListModel getCurrentEvents(String userID) {
        CalendarEvent[] events = this.useCaseManager.getCurrentEvents(userID);
        return new ListModel(new String[0]);
    }

    public ListModel getFutureEvents(String userID) {
        CalendarEvent[] events = this.useCaseManager.getFutureEvents(userID);
        return new ListModel(new String[0]);
    }
}

package controller;

import controller.viewmodels.ListModel;
import entities.CalendarEvent;
import usecases.IUseCaseManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class CommandLineController {
    private IUseCaseManager useCaseManager;

    public CommandLineController(IUseCaseManager useCaseManager) {
        this.useCaseManager = useCaseManager;
    }

    public String authenticateUser(String username, String password) {
        return this.useCaseManager.loginUser(username, password);
    }

    private GregorianCalendar convertStringToCalendar(String dateString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        GregorianCalendar calDate;
        try {
            Date date = df.parse(dateString);
            calDate = new GregorianCalendar();
            calDate.setTime(date);
        } catch (ParseException e) {
            return null;
        }
        return calDate;
    }

    public boolean createEvent(String eventName, String start, String end, String location, String userID) {
        GregorianCalendar calStart = convertStringToCalendar(start);
        GregorianCalendar calEnd = convertStringToCalendar(end);
        if (calStart != null && calEnd != null) {
            return this.useCaseManager.createEvent(eventName, calStart, calEnd, location, userID);
        }
        return false;
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

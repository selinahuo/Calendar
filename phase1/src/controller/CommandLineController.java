package controller;

import dataclasses.Quintuple;
import controller.viewmodels.ListModel;
import controller.viewmodels.SingularEventModel;
import entities.*;
import usecases.IUseCaseManager;
import view.views.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        CalendarEvent[] events = this.useCaseManager.getEventsByName(eventName, userID);
        return createListModel(events);
    }

    public ListModel getPastEvents(String userID) {
        CalendarEvent[] events = this.useCaseManager.getPastEvents(userID);
        return createListModel(events);
    }

    public ListModel getCurrentEvents(String userID) {
        CalendarEvent[] events = this.useCaseManager.getCurrentEvents(userID);
        return createListModel(events);
    }

    public ListModel getFutureEvents(String userID) {
        CalendarEvent[] events = this.useCaseManager.getFutureEvents(userID);
        return createListModel(events);
    }

    public SingularEventModel getSingularEvent(String eventID, String userID) {
        Quintuple<CalendarEvent, Alert, Memo, Tag, Series> eventData = this.useCaseManager.getSingularEvent(eventID, userID);
        String event = generateEventString(eventData.getFirst());
        String alert = generateAlertString(eventData.getSecond());
        String memo = generateMemoString(eventData.getThird());
        String tag = generateTagString(eventData.getFourth());
        String series = generateSeriesString(eventData.getFifth());
        return new SingularEventModel(event, alert, memo, tag, series);
    }

    public ListModel getEventsBySeriesName(String seriesName, String userID) {
        CalendarEvent[] events = this.useCaseManager.getEventsBySeriesName(seriesName, userID);
        return createListModel(events);
    }

    public boolean createSeriesFromEvents(String eventString, String seriesName, String userID) {
        String[] eventIDs = eventString.split(",");
        return this.useCaseManager.createSeriesFromEvents(eventIDs, seriesName, userID);
    }

    public boolean createSeriesFromFormula(String seriesName, String startStr, String endStr, String frequency,
                                           int numEvents, String userID) {
        GregorianCalendar start = convertStringToCalendar(startStr);
        GregorianCalendar end = convertStringToCalendar(endStr);
        return this.useCaseManager.createSeriesFromEventFormula(seriesName, start, end, frequency, numEvents, userID);
    }

    private ListModel createListModel(CalendarEvent[] events) {
        ListModel listModel = new ListModel();
        ArrayList<String> eventStrings = new ArrayList<>();
        for (CalendarEvent event: events) {
            String eventString = generateEventString(event);
            eventStrings.add(eventString);
        }
        listModel.setList(eventStrings);
        return listModel;
    }

    private ListModel createListModel(Alert[] alerts) {
        ListModel listModel = new ListModel();
        ArrayList<String> alertStrings = new ArrayList<>();
        for (Alert alert: alerts) {
            String eventString = generateAlertString(alert);
            alertStrings.add(eventString);
        }
        listModel.setList(alertStrings);
        return listModel;
    }

    private ListModel createListModel(Memo[] memos){
        ListModel listModel = new ListModel();
        ArrayList<String> memoString = new ArrayList<>();
        for (Memo memo: memos) {
            String memoStr = generateMemoString(memo);
            memoString.add(memoStr);
        }
        listModel.setList(memoString);
        return listModel;
    }

    private String generateEventString(CalendarEvent event) {
        if (event == null) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy | HH:mm");
        str.append("Event ID: ");
        str.append(event.getEventID());
        str.append(" - ");
        str.append(event.getName());
        str.append(" from ");
        str.append(fmt.format(event.getStart().getTime()) + " to " + fmt.format(event.getEnd().getTime()));
        str.append(" at ");
        str.append(event.getLocation());
        return str.toString();
    }

    private String generateAlertString(Alert alert) {
        if (alert == null) {
            return "";
        }
        SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy | HH:mm");
        StringBuilder str = new StringBuilder();
        str.append("Alert ID: " + alert.getAlertID() + " - " + alert.getAlertName());
        str.append(" is ringing since it's past " + fmt.format(alert.getNextRing().getTime()));

        return str.toString();
    }

    private String generateSeriesString(Series series) {
        if (series == null) {
            return "";
        }
        return "Series ID: " + series.getSeriesID() + " - " + series.getName();
    }

    private String generateMemoString(Memo memo) {
        if (memo == null) {
            return "";
        }
        return "Memo ID: " + memo.getMemoID() + " - " + memo.getName() + " - " + memo.getNote();
    }

    private String generateTagString(Tag tag) {
        if (tag == null) {
            return "";
        }
        return "Tag ID: " + tag.getTagID() + " - " + tag.getName();
    }

    public boolean CreateFrequencyAlert(String eventID, String alertName, String start, String frequency, String userID) {
        GregorianCalendar startTime = convertStringToCalendar(start);
        if (startTime != null) {
            return this.useCaseManager.createFrequencyAlertOnEvent(eventID, alertName,startTime,frequency,userID);
        }
        return false;
    }

    public boolean CreateIndividualAlert(String eventID, String alertName, String start, String userID) {
        GregorianCalendar startTime = convertStringToCalendar(start);
        if(startTime != null) {
            return this.useCaseManager.createIndividualAlertOnEvent(eventID, alertName,startTime,userID);
        }
        return false;
    }

    public ListModel getOverdueAlerts(String userID){
        Alert[] alerts = this.useCaseManager.getOverdueAlerts(userID);
        return createListModel(alerts);
    }

    public boolean acknowledgeAlert(String alertID, String userID) {
        return this.useCaseManager.acknowledgeAlert(alertID, userID);
    }

    public ListModel getMemos(String userID){
        return createListModel(this.useCaseManager.getMemos(userID));
    }

    public ListModel getEventsByMemoID(String memoID, String UserID){
        return createListModel(this.useCaseManager.getEventsByMemoID(memoID, UserID));
    }

   public ListModel getEventsByTagName(String tagName, String userID) {
        return createListModel(this.useCaseManager.getEventsByTagName(tagName, userID));
    }


    public boolean createMemo(String name, String note, String userID){
        return this.useCaseManager.createMemo(name, note, userID);
    }

    public boolean attachMemoToEvent(String memoID, String eventID, String userID){
        return this.useCaseManager.attachMemoToEvent(memoID,eventID,userID);
    }
    public boolean tagEvent(String eventID, String tagName, String userID){
        return this.useCaseManager.tagEvent(eventID,tagName,userID);
    }
}

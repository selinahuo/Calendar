package usecases;

import entities.Alert;
import entities.CalendarEvent;
import entities.Memo;
import sun.util.calendar.Gregorian;
import usecases.alerts.IAlertManager;
import usecases.events.IEventManager;
import usecases.notes.INoteManager;
import usecases.series.ISeriesManager;
import usecases.users.IUserManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

class UseCaseManager implements IUseCaseManager {
    private IEventManager eventManager;
    private IUserManager userManager;
    private IAlertManager alertManager;
    private ISeriesManager seriesManager;
    private INoteManager noteManager;

    public UseCaseManager(IEventManager eventManager, IUserManager userManager, IAlertManager alertManager, ISeriesManager seriesManager, INoteManager noteManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
        this.alertManager = alertManager;
        this.seriesManager = seriesManager;
        this.noteManager = noteManager;
    }

    @Override
    public String loginUser(String username, String password) {
        return this.userManager.authenticateUser(username, password);
    }

    @Override
    public boolean createEvent(String eventName, GregorianCalendar start, GregorianCalendar end, String location, String userID) {
        return this.eventManager.createEvent(eventName, start, end, location, userID);
    }

    @Override
    public CalendarEvent getSingularEvent(String eventID, String userID) {
        return null;
    }

    @Override
    public CalendarEvent getSingularEventByName(String name, String userID) {
        return null;
    }

    @Override
    public CalendarEvent[] getPastEvents(String userID) {
        Calendar calendar = Calendar.getInstance();
        GregorianCalendar now = (GregorianCalendar) calendar;
        return this.eventManager.getEventsByDateBeforeAndUserID(now, userID);
    }

    @Override
    public CalendarEvent[] getCurrentEvents(String userID) {
        GregorianCalendar now = now();
        return this.eventManager.getEventsByDateAndUserID(now, now, userID);
    }

    @Override
    public CalendarEvent[] getFutureEvents(String userID) {
        return this.eventManager.getEventsByDateAfterAndUserID(now(), userID);
    }

//    @Override
//    public CalendarEvent[] getEventByDate(GregorianCalendar date, String userID) {
//        return this.eventManager.getEventsByDateAndUserID();
//    }

    @Override
    public Alert[] getOverdueAlerts(String userID) {
        ArrayList<Alert> alertList = this.alertManager.getOverdueAlertsAfterDate(now(), userID);
        Alert[] alertArr = new Alert[alertList.size()];
        alertList.toArray(alertArr);
        return alertArr;
    }

    @Override
    public Alert getIndividualAlert(String alertID, String userID) {
        return null;
    }

    @Override
    public boolean createIndividualAlertOnEvent(String eventID, String alertName, GregorianCalendar start, String userID) {
        return this.alertManager.createIndividualAlertOnEvent(eventID, alertName, start, userID);
    }

    @Override
    public boolean createFrequencyAlertOnEvent(String eventID, String alertName, GregorianCalendar start, String frequency, String userID) {
        return this.alertManager.createFrequencyAlertOnEvent(eventID, alertName, userID, start, frequency);
    }

    @Override
    public boolean acknowledgeAlert(String alertID, String userID) {
        return this.alertManager.acknowledgeAlert(alertID, userID);
    }

    @Override
    public boolean createSeriesFromEvents(String[] eventIDs, String seriesName, String userID) {
        return this.seriesManager.createSeriesByCombiningEvents(seriesName, eventIDs, userID);
    }

    @Override
    public boolean createSeriesFromEventFormula(String seriesName, GregorianCalendar start, GregorianCalendar end, String frequency, int numEvents, String userID) {
        return this.seriesManager.createSeriesFromEventFormula(seriesName, start, end, frequency, numEvents, userID);
    }

    @Override
    public CalendarEvent[] getEventsBySeriesName(String seriesName, String userID) {
        return this.seriesManager.getEventsBySeriesNameAndUserID(seriesName, userID);
    }

    @Override
    public Memo[] getMemos(String userID) {
        return this.noteManager.getMemosByUserID(userID);
    }

    @Override
    public CalendarEvent[] getEventsByMemoID(String memoID, String userID) {
        return this.noteManager.getEventsByMemoIDAndUserID(memoID, userID);
    }

    @Override
    public CalendarEvent[] getEventsByTagName(String tagName, String userID) {
        return this.noteManager.getEventsByTagNameAndUserID(tagName, userID);
    }

    @Override
    public boolean createMemo(String name, String note, String userID) {
        return this.noteManager.createMemo(name, note, userID);
    }

    @Override
    public boolean attachMemoToEvent(String memoID, String eventID, String userID) {
        return this.noteManager.attachMemoToEvent(memoID, eventID, userID);
    }

    @Override
    public boolean tagEvent(String eventID, String tagName, String userID) {
        return this.noteManager.tagEvent(eventID, tagName, userID);
    }

    private GregorianCalendar now() {
        Calendar calendar = Calendar.getInstance();
        GregorianCalendar now = (GregorianCalendar) calendar;
        return now;
    }
}

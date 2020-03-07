package usecases;

import entities.Alert;
import entities.CalendarEvent;
import entities.Memo;
import usecases.alerts.IAlertManager;
import usecases.events.IEventManager;
import usecases.notes.INoteManager;
import usecases.series.ISeriesManager;
import usecases.users.IUserManager;

import java.util.GregorianCalendar;

class UseCaseManager implements IUseCaseManager {
    private IEventManager eventManager;
    private IUserManager userManager;
    private IAlertManager alertManager;
    private ISeriesManager seriesManager;
    private INoteManager noteManager;

    @Override
    public String loginUser(String username, String password) {
        return "";
    }

    @Override
    public boolean createEvent(CalendarEvent event, String userID) {
        return false;
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
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] getCurrentEvents(String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] getFutureEvents(String userID) {
        return new CalendarEvent[0];
    }

//    @Override
//    public CalendarEvent[] getEventByDate(GregorianCalendar date, String userID) {
//        return this.eventManager.getEventsByDateAndUserID();
//    }

    @Override
    public Alert[] getOverdueAlerts(String userID) {
        return new Alert[0];
    }

    @Override
    public Alert getIndividualAlert(String alertID, String userID) {
        return null;
    }

    @Override
    public boolean createIndividualAlertOnEvent(String eventID, String userID) {
        return false;
    }

    @Override
    public boolean createFrequencyAlertOnEvent(String eventID, String userID) {
        return false;
    }

    @Override
    public boolean acknowledgeAlert(String alertID, String userID) {
        return false;
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
}

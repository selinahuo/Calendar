package usecases;

import entities.Alert;
import entities.CalendarEvent;
import entities.Memo;
import usecases.alerts.IAlertManager;
import usecases.events.IEventManager;
import usecases.series.ISeriesManager;
import usecases.users.IUserManager;

import java.util.GregorianCalendar;

class UseCaseManager implements IUseCaseManager {
    private IEventManager eventManager;
    private IUserManager userManager;
    private IAlertManager alertManager;
    private ISeriesManager seriesManager;
    //    private ITagManager tagManager;

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

    @Override
    public CalendarEvent[] getEventByDate(GregorianCalendar date, String userID) {
        return new CalendarEvent[0];
    }

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
        return false;
    }

    @Override
    public boolean createSeriesFromEventFormula(String seriesName, GregorianCalendar start, GregorianCalendar end, String frequency, int numEvents, String userID) {
        return false;
    }

    @Override
    public CalendarEvent[] getEventsBySeriesName(String seriesName, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public Memo[] getMemos(String userID) {
        return new Memo[0];
    }

    @Override
    public CalendarEvent[] getEventsByMemoID(String memoID, String UserID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] getEventsByTagName(String tagName, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public boolean createMemo(String userID) {
        return false;
    }

    @Override
    public boolean attachMemoToEvents(String memoID, String[] eventIDs, String userID) {
        return false;
    }

    @Override
    public boolean tagEvent(String eventID, String tagName, String userID) {
        return false;
    }
}

package usecases;

import entities.Alert;
import entities.CalendarEvent;
import entities.Memo;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * IUseCaseManager provides a public interface for all use cases
 */
public interface IUseCaseManager {
    // === USER === //

    // returns the userID
    String loginUser(String username, String password);

    // === EVENTS === //
    boolean createEvent(CalendarEvent event, String userID);

    // get a user's singular event (series if part of, alert if part of, and memos and tags)
    CalendarEvent getSingularEvent(String eventID, String userID);
    CalendarEvent getSingularEventByName(String name, String userID);

    // get user's past events
    CalendarEvent[] getPastEvents(String userID);
    // get user's current events
    CalendarEvent[] getCurrentEvents(String userID);
    // get user's future events
    CalendarEvent[] getFutureEvents(String userID);
    // get event that start on date
    CalendarEvent[] getEventByDate(GregorianCalendar date, String userID);

    // === ALERTS === //

    // get user's overdue alerts
    Alert[] getOverdueAlerts(String userID);
    // get user's individual alert (this needs to inject event details into the implementation)
    Alert getIndividualAlert(String alertID, String userID);

    // user set's an alert for an event
    // TODO: include additional parameters, (date, name of event, etc.)
    boolean createIndividualAlertOnEvent(String eventID, String userID);
    boolean createFrequencyAlertOnEvent(String eventID, String userID);
    boolean acknowledgeAlert(String alertID, String userID);

    // === SERIES === //

    // create series from events
    boolean createSeriesFromEvents(String[] eventIDs, String seriesName, String userID);
    boolean createSeriesFromEventFormula(String seriesName, GregorianCalendar start, GregorianCalendar end,
                                         String frequency, int numEvents, String userID);

    // get event by series name
    CalendarEvent[] getEventsBySeriesName(String seriesName, String userID);

    // === NOTES === //

    // get a user's memos
    Memo[] getMemos(String userID);
    // get a user's event from list of memos
    CalendarEvent[] getEventsByMemoID(String memoID, String UserID);
    // get a user's event by tag name
    CalendarEvent[] getEventsByTagName(String tagName, String userID);

    // create a memo
    // TODO: add additional parameters (name, content, etc.)
    boolean createMemo(String userID);
    boolean attachMemoToEvents(String memoID, String[] eventIDs, String userID);
    // create a tag
    // TODO: this would check if a tag with the name exists if not create a new one. Tag the event with the appropriate ID
    boolean tagEvent(String eventID, String tagName, String userID);
}

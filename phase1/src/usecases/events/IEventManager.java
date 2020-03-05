package usecases.events;

import java.util.GregorianCalendar;

import entities.CalendarEvent;

/**
 * IEventManager provides a public interface for event operations
 */
public interface IEventManager {
    boolean createEvent(CalendarEvent event);

    CalendarEvent getEventByID(String id);
    CalendarEvent[] getEventsByIDs(String[] ids);
    CalendarEvent[] getEventsByNameAndUserID(String name, String userID);

    CalendarEvent[] getEventsByDateAndUserID(GregorianCalendar before, GregorianCalendar after, String userID);

    CalendarEvent[] getEventsByDateBeforeAndUserID(GregorianCalendar before, String userID);

    CalendarEvent[] getEventsByDateAfterAndUserID(GregorianCalendar after, String userID);

    // series
    CalendarEvent[] getEventsBySeriesIDAndUserID(String seriesID, String userID);
    boolean editEventSeriesID(String eventID, String newSeriesID);


    // notes
    CalendarEvent[] getEventsByTagIDAndUserID(String tagID, String userID);
    CalendarEvent[] getEventsByMemoIDAndUserID(String memoID, String userID);
    //edit

    // get event by alertID (and userID)
    CalendarEvent getEventByAlertIDAndUserID(String alertID, String userID);
//    CalendarEvent editEventAlertID(String eventID, String newAlertID);
}

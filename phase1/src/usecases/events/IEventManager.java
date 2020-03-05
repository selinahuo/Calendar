package usecases.events;

import java.util.Date;

import entities.CalendarEvent;

/**
 * IEventManager provides a public interface for event operations
 */
public interface IEventManager {
    boolean createEvent(CalendarEvent event);

    CalendarEvent getEventByID(String id);
    CalendarEvent[] getEventByIDs(String[] ids);
    CalendarEvent[] getEventByNameAndUserID(String name, String userID);

    CalendarEvent[] getEventByDateAndUserID(Date before, Date after, String userID);

    CalendarEvent[] getEventByDateBeforeAndUserID(Date before, String userID);

    CalendarEvent[] getEventByDateAfterAndIDs(Date after, String userID);

    // series
    CalendarEvent[] getEventsBySeriesIDAndUserID(String seriesID, String userID);
    CalendarEvent editEventSeriesID(String eventID, String newSeriesID);


    // notes
    CalendarEvent[] fetchEventsByTagIDAndUserID(String tagID, String userID);
    CalendarEvent[] fetchEventsByMemoIDAndUserID(String memoID, String userID);
    //edit

    // get event by alertID (and userID)
    CalendarEvent[] fetchEventsByAlertIDAndUserID(String alertID, String userID);
    CalendarEvent editEventAlertID(String eventID, String newAlertID);
}

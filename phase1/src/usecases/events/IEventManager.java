package usecases.events;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import entities.CalendarEvent;

/**
 * IEventManager provides a public interface for event operations
 */
public interface IEventManager {
    boolean createEvent(CalendarEvent event);
    boolean createEvent(String eventName, GregorianCalendar start, GregorianCalendar end, String location, String userID);

    CalendarEvent getEventByID(String id);
    CalendarEvent[] getEventsByIDs(String[] ids);
    CalendarEvent[] getEventsByNameAndUserID(String name, String userID);

    CalendarEvent[] getEventsByDateAndUserID(GregorianCalendar before, GregorianCalendar after, String userID);
    CalendarEvent[] getEventsByDateBeforeAndUserID(GregorianCalendar before, String userID);
    CalendarEvent[] getEventsByDateAfterAndUserID(GregorianCalendar after, String userID);

    CalendarEvent[] getEventsBySeriesIDAndUserID(String seriesID, String userID);
    boolean editEventSeriesID(String eventID, String newSeriesID);

    CalendarEvent[] getEventsByTagIDAndUserID(String tagID, String userID);
    CalendarEvent[] getEventsByMemoIDAndUserID(String memoID, String userID);
    boolean setEventMemoIDs(ArrayList<String> memoIDs, String eventID, String userID);
    boolean setEventTagIDs(ArrayList<String> tagIDs, String eventID, String userID);



    // get event by alertID (and userID)
    CalendarEvent getEventByAlertIDAndUserID(String alertID, String userID);
//    CalendarEvent editEventAlertID(String eventID, String newAlertID);
}

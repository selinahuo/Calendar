package usecases.events;

import entities.CalendarEvent;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Provides a public interface for event repository operations
 */
public interface IEventRepository {
    /**
     * Save a CalendarEvent.
     * @param event the event to save
     * @return true if event creation was successful, false otherwise
     */
    boolean saveEvent(CalendarEvent event);

    /**
     * Fetch an CalendarEvent by its ID.
     * @param id the ID to filter by.
     * @return the corresponding CalendarEvent or null if it does not exist
     */
    CalendarEvent fetchEventByID(String id);

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs and a matching name
     * @param name events must have this name
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventsByNameAndUserID(String name, String userID);

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs in the specified appropriate Date range
     * @param before returned CalendarEvents must start before this Date
     * @param after returned CalendarEvents must end after this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventsByDateAndUserID(GregorianCalendar before, GregorianCalendar after, String userID);

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs that end before a Date
     * @param before returned CalendarEvents must end before this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventsByDateBeforeAndUserID(GregorianCalendar before, String userID);

    /**
     * fetch all CalendarEvents that have an ID in a list of IDs that start after a Date
     * @param after returned CalendarEvents must start after this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventsByDateAfterAndUserID(GregorianCalendar after, String userID);

    /**
     * fetch all CalendarEvents that have matching series ID and user ID
     * @param seriesID returned CalendarEvents must belong to the series
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventBySeriesIDAndUserID(String seriesID, String userID);

    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param eventID returned CalendarEvents must belong to the series
     * @param newSeriesID new Series ID for the event
     * @return True if successfully updated, false otherwise
     */
    boolean editEventSeriesID(String eventID, String newSeriesID);


    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param tagID returned CalendarEvents must belong to the tag
     * @param userID returned CalendarEvents must belong to the tag
     * @return list of matching CalenderEvents
     */
    CalendarEvent[] fetchEventsByTagIDAndUserID(String tagID, String userID);
    boolean setEventTagIDs(ArrayList<String> tagIDs, String eventID, String userID);

    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param memoID returned CalendarEvents must belong to the memo
     * @param userID returned CalendarEvents must belong to the tag
     * @return list of matching CalenderEvents
     */
    CalendarEvent[] fetchEventsByMemoIDAndUserID(String memoID, String userID);

    boolean setEventMemoIDs(ArrayList<String> memoIDs, String eventID, String userID);

    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param alertID returned CalendarEvents must belong to the alert
     * @param userID returned CalendarEvents must belong to the tag
     * @return list of matching CalenderEvents
     */
    CalendarEvent fetchEventByAlertIDAndUserID(String alertID, String userID);
}

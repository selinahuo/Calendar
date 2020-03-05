package usecases.events;

import entities.CalendarEvent;

import java.util.Date;

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
    CalendarEvent[] fetchEventByNameAndUserID(String name, String userID);

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs in the specified appropriate Date range
     * @param before returned CalendarEvents must start before this Date
     * @param after returned CalendarEvents must end after this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventByDateAndUserID(Date before, Date after, String userID);

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs that end before a Date
     * @param before returned CalendarEvents must end before this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventByDateBeforeAndUserID(Date before, String userID);

    /**
     * fetch all CalendarEvents that have an ID in a list of IDs that start after a Date
     * @param after returned CalendarEvents must start after this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventByDateAfterAndUserID(Date after, String userID);

    // series
    CalendarEvent fetchEventBySeriesIDAndUserID(String seriesID, String userID);
    CalendarEvent editEventSeriesID(String eventID, String newSeriesID);


    // notes
    CalendarEvent fetchEventByTagIDAndUserID(String tagID, String userID);
    CalendarEvent fetchEventByMemoIDAndUserID(String memoID, String userID);
    //edit

    // get event by alertID (and userID)
    CalendarEvent fetchEventByAlertIDAndUserID(String alertID, String userID);
    // edit event's alertID
}

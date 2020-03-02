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
     * Fetch all CalendarEvents that have an ID in a list of IDs
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventByIDs(String[] ids);

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs and a matching name
     * @param name events must have this name
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventByNameAndIDs(String name, String[] ids);

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs in the specified appropriate Date range
     * @param before returned CalendarEvents must start before this Date
     * @param after returned CalendarEvents must end after this Date
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventByDateAndIDs(Date before, Date after, String[] ids);

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs that end before a Date
     * @param before returned CalendarEvents must end before this Date
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventByDateBeforeAndIDs(Date before, String[] ids);

    /**
     * fetch all CalendarEvents that have an ID in a list of IDs that start after a Date
     * @param after returned CalendarEvents must start after this Date
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] fetchEventByDateAfterAndIDs(Date after, String[] ids);
}

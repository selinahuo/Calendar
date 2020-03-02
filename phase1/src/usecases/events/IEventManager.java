package usecases.events;

import java.util.Date;

import entities.CalendarEvent;

/**
 * IEventManager provides a public interface for event operations
 */
public interface IEventManager {
    /**
     * Create and save a CalendarEvent.
     * @param event the event to save
     * @return true if event creation was successful, false otherwise
     */
    boolean createEvent(CalendarEvent event);

    /**
     * Get an CalendarEvent by its ID.
     * @param id the ID to filter by.
     * @return the corresponding CalendarEvent or null if it does not exist
     */
    CalendarEvent getEventByID(String id);

    /**
     * Get all CalendarEvents that have an ID in a list of IDs
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] getEventByIDs(String[] ids);

    /**
     * Get all CalendarEvents that have an ID in a list of IDs and a matching name
     * @param name events must have this name
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] getEventByNameAndIDs(String name, String[] ids);

    /**
     * Get all CalendarEvents that have an ID in a list of IDs in the specified appropriate Date range
     * @param before returned CalendarEvents must start before this Date
     * @param after returned CalendarEvents must end after this Date
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] getEventByDateAndIDs(Date before, Date after, String[] ids);

    /**
     * Get all CalendarEvents that have an ID in a list of IDs that end before a Date
     * @param before returned CalendarEvents must end before this Date
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] getEventByDateBeforeAndIDs(Date before, String[] ids);

    /**
     * Get all CalendarEvents that have an ID in a list of IDs that start after a Date
     * @param after returned CalendarEvents must start after this Date
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    CalendarEvent[] getEventByDateAfterAndIDs(Date after, String[] ids);
}

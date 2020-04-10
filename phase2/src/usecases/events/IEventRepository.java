package usecases.events;

import entities.CalendarEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Provides a public interface for event repository operations
 */
public interface IEventRepository {
    /**
     * Save an event
     * @param event to save
     */
    void saveEvent(CalendarEvent event);

    /**
     * Fetch event by its unique ID
     * @param eventID unique event ID
     * @return matching event entity
     */
    CalendarEvent fetchEventByEventID(String eventID);

    /**
     * Fetch event by its unique ID and owner ID
     * @param eventID unique event ID
     * @param ownerID unique owner ID
     * @return matching event entity
     */
    CalendarEvent fetchEventByEventIDAndOwnerID(String eventID, String ownerID);

    /**
     * Fetch event by its unique ID and user ID of owner or collaborators
     * @param eventID unique event ID
     * @param userID associated user ID
     * @return matching event entity
     */
    CalendarEvent fetchEventByEventIDAndUserID(String eventID, String userID);

    /**
     * Fetch event by an alert ID and user ID of owner
     * @param alertID alert ID to match
     * @param ownerID unique owner ID
     * @return matching event entity
     */
    CalendarEvent fetchEventByAlertIDAndOwnerID(String alertID, String ownerID);

    /**
     * Fetch events with a name and associated with a user
     * @param name name to match
     * @param userID associated user ID
     * @return list of event entities
     */
    ArrayList<CalendarEvent> fetchEventsByNameAndUserID(String name, String userID);

    /**
     * Fetch events that start before a date and associated with a user
     * @param start start before this datetime
     * @param userID associated user ID
     * @return list of matching event entities
     */
    ArrayList<CalendarEvent> fetchEventsStartBeforeAndUserID(LocalDateTime start, String userID);

    /**
     * Fetch events that start before a datetime, end after a datetime and associated with a user
     * @param start start before this datetime
     * @param end end after this datetime
     * @param userID associated user ID
     * @return list of matching event entities
     */
    ArrayList<CalendarEvent> fetchEventsStartBeforeAndEndAfterAndUserID(LocalDateTime start, LocalDateTime end,
                                                                        String userID);

    /**
     * Fetch events that start after a datetime and associated with a user
     * @param after start after this datetime
     * @param userID associated user ID
     * @return list of matching event entities
     */
    ArrayList<CalendarEvent> fetchEventsStartAfterAndUserID(LocalDateTime after, String userID);

    /**
     * Fetch events that start before and after two datetimes and associated with a user
     * @param before start before this datetime
     * @param after start after this datetime
     * @param userID associated user ID
     * @return list of matching event entities
     */
    ArrayList<CalendarEvent> fetchEventsStartBeforeAndStartAfterAndUserID(LocalDateTime before, LocalDateTime after,
                                                                          String userID);

    /**
     * Fetch events associated with alert and owner by a user
     * @param alertID associated alert ID
     * @param ownerID unique owner ID
     * @return list of matching event entities
     */
    ArrayList<CalendarEvent> fetchEventsByAlertIDAndOwnerID(String alertID, String ownerID);

    /**
     * Fetch events associated with calendar and owner
     * @param calendarID associated calendar ID
     * @param ownerID unique owner ID
     * @return list of matching event entities
     */
    ArrayList<CalendarEvent> fetchEventsByCalendarIDAndOwnerID(String calendarID, String ownerID);

    /**
     * Fetch events associated with tag and owner
     * @param tagID associated tag ID
     * @param ownerID unique owner ID
     * @return list of matching event entities
     */
    ArrayList<CalendarEvent> fetchEventsByTagIDAndOwnerID(String tagID, String ownerID);

    /**
     * Fetch events associated with memo and owner
     * @param memoID associated memo ID
     * @param ownerID unique owner ID
     * @return list of matching event entities
     */
    ArrayList<CalendarEvent> fetchEventsByMemoIDAndOwnerID(String memoID, String ownerID);

    /**
     * Fetch events associated with series and owner
     * @param seriesID associated series ID
     * @param ownerID unique owner ID
     * @return list of matching event entities
     */
    ArrayList<CalendarEvent> fetchEventsBySeriesIDAndOwnerID(String seriesID, String ownerID);


    /**
     * Edit an event name
     * @param eventID unique event ID
     * @param name new name to set
     * @param ownerID owner of the event
     * @return whether the edit was successful
     */
    boolean editEventName(String eventID, String name, String ownerID);

    /**
     * Edit an event start datetime
     * @param eventID unique event ID
     * @param start new start datetime
     * @param ownerID owner of the event
     * @return whether the edit was successful
     */
    boolean editEventStart(String eventID, LocalDateTime start, String ownerID);

    /**
     * Edit an event end datetime
     * @param eventID unique event ID
     * @param end new end datetime
     * @param ownerID owner of the event
     * @return whether the edit was successful
     */
    boolean editEventEnd(String eventID, LocalDateTime end, String ownerID);

    /**
     * Edit an event location
     * @param eventID unique event ID
     * @param location new location
     * @param ownerID owner of the event
     * @return whether the edit was successful
     */
    boolean editEventLocation(String eventID, String location, String ownerID);

    /**
     * Edit event's associated calendar
     * @param eventID unique event ID
     * @param calendarID new calendar ID or "" for no calendar
     * @param ownerID owner of the event
     * @return whether the edit was successful
     */
    boolean editEventCalendarID(String eventID, String calendarID, String ownerID);

    /**
     * Edit event's tags
     * @param eventID unique event ID
     * @param tagIDs list of associated tag IDs
     * @param ownerID owner of the event
     * @return whether the edit was successful
     */
    boolean editTagIDs(String eventID, ArrayList<String> tagIDs, String ownerID);

    /**
     * Edit event's associated memo
     * @param eventID unique event ID
     * @param memoID new memo ID or "" for no memo
     * @param ownerID owner of the event
     * @return whether the edit was successful
     */
    boolean editMemoID(String eventID, String memoID, String ownerID);

    /**
     * Edit event's associated series
     * @param eventID unique event ID
     * @param seriesID new series ID or "" for no series
     * @param ownerID owner of the event
     * @return whether the edit was successful
     */
    boolean editSeriesID(String eventID, String seriesID, String ownerID);

    /**
     * Edit event's associated alert
     * @param eventID unique event ID
     * @param alertID new alert ID or "" for no alert
     * @param ownerID owner of the event
     * @return whether edit was successful
     */
    boolean editAlertID(String eventID, String alertID, String ownerID);

    /**
     * Add a collaborator to event
     * @param eventID unique event ID
     * @param collaboratorID collaborator to add
     * @return whether edit was successful
     */
    boolean addCollaborator(String eventID, String collaboratorID);

    /**
     * Delete an event
     * @param eventID unique event ID
     * @param ownerID owner of the event
     * @return whether deletion was successful
     */
    boolean deleteEvent(String eventID, String ownerID);
}

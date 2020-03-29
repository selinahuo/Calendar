package usecases.events;

import entities.CalendarEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IEventRepository {
    // write
    void saveEvent(CalendarEvent event);

    // Fetch - singular
    CalendarEvent fetchEventByEventID(String eventID);
    CalendarEvent fetchEventByEventIDAndOwnerID(String eventID, String ownerID);
    CalendarEvent fetchEventByEventIDAndUserID(String eventID, String userID);

    // Alerts
    CalendarEvent fetchEventByAlertIDAndOwnerID(String alertID, String ownerID);

    // Fetch - plural
    // Name
    ArrayList<CalendarEvent> fetchEventsByNameAndUserID(String name, String userID);

    // Date
    ArrayList<CalendarEvent> fetchEventsStartBeforeAndUserID(LocalDateTime start, String userID);
    ArrayList<CalendarEvent> fetchEventsStartBeforeAndEndAfterAndUserID(LocalDateTime start, LocalDateTime end, String userID);

    ArrayList<CalendarEvent> fetchEventsStartAfterAndUserID(LocalDateTime after, String userID);
    ArrayList<CalendarEvent> fetchEventsStartBeforeAndStartAfterAndUserID(LocalDateTime before, LocalDateTime after, String userID);

    // Calendar
    ArrayList<CalendarEvent> fetchEventsByCalendarIDAndOwnerID(String calendarID, String ownerID);

    // Tags
    ArrayList<CalendarEvent> fetchEventsByTagIDAndOwnerID(String tagID, String ownerID);

    // Memos
    ArrayList<CalendarEvent> fetchEventsByMemoIDAndOwnerID(String memoID, String ownerID);

    // Series
    ArrayList<CalendarEvent> fetchEventsBySeriesIDAndOwnerID(String seriesID, String ownerID);

    // edit
    boolean editEventName(String eventID, String name, String ownerID);
    boolean editEventStart(String eventID, LocalDateTime start, String ownerID);
    boolean editEventEnd(String eventID, LocalDateTime end, String ownerID);
    boolean editEventLocation(String eventID, String location, String ownerID);
    boolean editEventCalendarID(String eventID, String calendarID, String ownerID);
    boolean editTagIDs(String eventID, ArrayList<String> tagIDs, String ownerID);
    boolean editMemoID(String eventID, String memoID, String ownerID);
    boolean editSeriesID(String eventID, String seriesID, String ownerID);
    boolean editAlertID(String eventID, String alertID, String ownerID);
    boolean addCollaborator(String eventID, String collaboratorID);

    //delete
    boolean deleteEvent(String eventID, String ownerID);
}

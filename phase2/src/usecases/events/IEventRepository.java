package usecases.events;

import entities.CalendarEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IEventRepository {
    // write
    boolean saveEvent(CalendarEvent event);

    // Fetch - singular
    CalendarEvent fetchEventByEventID(String eventID);
    CalendarEvent fetchEventByEventIDAndOwnerID(String eventID, String ownerID);
    CalendarEvent fetchEventByEventIDAndCollaboratorID(String eventID, String collaboratorID);
    CalendarEvent fetchEventByEventIDAndUserID(String eventID, String userID);

    // Alerts
    CalendarEvent fetchEventByAlertIDAndOwnerID(String alertID, String ownerID);

    // Fetch - plural
    // Generic
    ArrayList<CalendarEvent> fetchEventsByOwnerID(String ownerID);
    ArrayList<CalendarEvent> fetchEventsByCollaboratorID(String collaboratorID);
    ArrayList<CalendarEvent> fetchEventsByUserID(String userID);

    // Name
    ArrayList<CalendarEvent> fetchEventsByNameAndOwnerID(String name, String ownerID);
    ArrayList<CalendarEvent> fetchEventsByNameAndCollaboratorID(String name, String collaboratorID);
    ArrayList<CalendarEvent> fetchEventsByNameAndUserID(String name, String userID);

    // Date
    ArrayList<CalendarEvent> fetchEventsStartBeforeAndUserID(LocalDateTime start, String userID);
    ArrayList<CalendarEvent> fetchEventsStartBeforeAndEndAfterAndUserID(LocalDateTime start, LocalDateTime end, String userID);

    ArrayList<CalendarEvent> fetchEventsStartAfterAndUserID(LocalDateTime after, String userID);
    ArrayList<CalendarEvent> fetchEventsStartBeforeAndStartAfterAndUserID(LocalDateTime before, LocalDateTime after, String userID);

    ArrayList<CalendarEvent> fetchEventsEndBeforeAndUserID(LocalDateTime before, String userID);

    // Calendar
    ArrayList<CalendarEvent> fetchEventsByCalendarID(String calendarID);

    // Tags
    ArrayList<CalendarEvent> fetchEventsByTagIDAndOwnerID(String tagID, String ownerID);
    ArrayList<CalendarEvent> fetchEventsByTagIDAndCollaboratorID(String tagID, String collaboratorID);
    ArrayList<CalendarEvent> fetchEventsByTagIDAndUserID(String tagID, String userID);

    // Memos
    ArrayList<CalendarEvent> fetchEventsByMemoIDAndOwnerID(String memoID, String ownerID);
    ArrayList<CalendarEvent> fetchEventsByMemoIDAndCollaboratorID(String memoID, String collaboratorID);
    ArrayList<CalendarEvent> fetchEventsByMemoIDAndUserID(String memoID, String userID);

    // Series
    ArrayList<CalendarEvent> fetchEventsBySeriesIDAndOwnerID(String seriesID, String ownerID);

    // edit
    boolean editEventName(String eventID, String name, String ownerID);
    boolean editEventStart(String eventID, LocalDateTime start, String ownerID);
    boolean editEventEnd(String eventID, LocalDateTime end, String ownerID);
    boolean editEventLocation(String eventID, String Location, String ownerID);
    boolean editEventCalendarID(String eventID, String calendarID, String ownerID);
    boolean editEventCollaborators(String eventID, ArrayList<String> collaborators, String ownerID);
    boolean editTagIDs(String eventID, ArrayList<String> tagIDs, String ownerID);
    boolean editMemoID(String eventID, String memoID, String ownerID);
    boolean editSeriesID(String eventID, String seriesID, String ownerID);
    boolean editAlertID(String eventID, String alertID, String ownerID);

    //delete
    boolean deleteEvent(String eventID, String ownerID);
}

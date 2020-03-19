package dataaccess;

import entities.CalendarEvent;
import usecases.events.IEventRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SerializableEventRepository extends SerializableRepository<CalendarEvent> implements IEventRepository {
    public SerializableEventRepository() { super("events.ser"); }

    @Override
    public boolean saveEvent(CalendarEvent event) {
        return false;
    }

    @Override
    public CalendarEvent fetchEventByEventID(String eventID) {
        return fetchSingular((CalendarEvent event) -> {
            return event.getEventID() != null && event.getEventID().equals(eventID);
        });
    }

    @Override
    public CalendarEvent fetchEventByEventIDAndOwnerID(String eventID, String ownerID) {
        return null;
    }

    @Override
    public CalendarEvent fetchEventByEventIDAndCollaboratorID(String eventID, String collaboratorID) {
        return null;
    }

    @Override
    public CalendarEvent fetchEventByEventIDAndUserID(String eventID, String userID) {
        return null;
    }

    @Override
    public CalendarEvent fetchEventByAlertIDAndOwnerID(String alertID, String ownerID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByOwnerID(String ownerID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByCollaboratorID(String collaboratorID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByUserID(String userID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByNameAndOwnerID(String name, String ownerID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByNameAndCollaboratorID(String name, String ownerID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByNameAndUserID(String name, String userID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsStartBeforeAndEndAfterAndUserID(LocalDateTime start, LocalDateTime end, String userID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsStartAfterAndUserID(LocalDateTime after, String userID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsStartAfterAndStartBeforeAndUserID(LocalDateTime after, LocalDateTime before, String userID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsEndBeforeAndUserID(LocalDateTime before, String userID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByCalendarID(String calendarID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByTagIDAndOwnerID(String tagID, String ownerID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByTagIDAndCollaboratorID(String tagID, String collaboratorID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByTagIDAndUserID(String tagID, String userID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByMemoIDAndOwnerID(String memoID, String ownerID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByMemoIDAndCollaboratorID(String memoID, String collaboratorID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByMemoIDAndUserID(String memoID, String userID) {
        return null;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsBySeriesIDAndOwnerID(String seriesID, String ownerID) {
        return null;
    }

    @Override
    public boolean editEventName(String eventID, String name, String ownerID) {
        return false;
    }

    @Override
    public boolean editEventStart(String eventID, LocalDateTime start, String ownerID) {
        return false;
    }

    @Override
    public boolean editEventEnd(String eventID, LocalDateTime end, String ownerID) {
        return false;
    }

    @Override
    public boolean editEventLocation(String eventID, String Location, String ownerID) {
        return false;
    }

    @Override
    public boolean editEventCalendarID(String eventID, String calendarID, String ownerID) {
        return false;
    }

    @Override
    public boolean editEventCollaborators(String eventID, ArrayList<String> collaborators, String ownerID) {
        return false;
    }

    @Override
    public boolean editTagIDs(String eventID, ArrayList<String> tagIDs, String ownerID) {
        return false;
    }

    @Override
    public boolean editMemoIDs(String eventID, ArrayList<String> memoIDs, String ownerID) {
        return false;
    }

    @Override
    public boolean editSeriesID(String eventID, String seriesID, String ownerID) {
        return false;
    }

    @Override
    public boolean editAlertID(String eventID, String alertID, String ownerID) {
        return false;
    }

    @Override
    public boolean deleteEvent(String eventID, String ownerID) {
        return false;
    }
}

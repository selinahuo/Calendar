package usecases.events;

import entities.CalendarEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventManager {
    private IEventRepository repository;

    public EventManager(IEventRepository repository) {
        this.repository = repository;
    }

    // save
    public String createEvent(String name, LocalDateTime start, LocalDateTime end, String location, String userID, String calendarID) {
        CalendarEvent event = new CalendarEvent(name, start, end, location, userID, calendarID);
        boolean success = repository.saveEvent(event);
        if (success) {
            return event.getEventID();
        } else {
            return null;
        }
    }

    // get - singular
    public CalendarEvent getEventByIDAndUserID(String eventID, String userID) {
        return repository.fetchEventByEventIDAndUserID(eventID, userID);
    }
    public CalendarEvent getEventByAlertIDAndOwnerID(String alertID, String ownerID) {
        return repository.fetchEventByAlertIDAndOwnerID(alertID, ownerID);
    }

    // get - plural
    public ArrayList<CalendarEvent> getEventsBySeriesIDAndOwnerID(String seriesID, String ownerID) {
        return repository.fetchEventsBySeriesIDAndOwnerID(seriesID, ownerID);
    }
    // edit
    public boolean editSeriesID(String eventID, String seriesID, String ownerID) {
        return repository.editSeriesID(eventID, seriesID, ownerID);
    }
    // delete
}

package usecases.events;

import entities.CalendarEvent;

import java.time.LocalDateTime;

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
    // edit
    // delete
}

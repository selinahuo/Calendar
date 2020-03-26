package usecases.events;

import entities.CalendarEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private IEventRepository repository;
    private List<IEventDeletionObserver> observers = new ArrayList<>();

    public EventManager(IEventRepository repository) {
        this.repository = repository;
    }

    public void addObservers(IEventDeletionObserver deletionObserver) {
        observers.add(deletionObserver);
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
    public ArrayList<CalendarEvent> getEventsByNameAndUserID(String name, String userID) {
        return repository.fetchEventsByNameAndUserID(name, userID);
    }

    public ArrayList<CalendarEvent> getEventsBySeriesIDAndOwnerID(String seriesID, String ownerID) {
        return repository.fetchEventsBySeriesIDAndOwnerID(seriesID, ownerID);
    }

    // edit
    public boolean editSeriesID(String eventID, String seriesID, String ownerID) {
        return repository.editSeriesID(eventID, seriesID, ownerID);
    }

    public ArrayList<CalendarEvent> getEventsByOwnerID(String ownerID){
        return repository.fetchEventsByOwnerID(ownerID);
    }

    // delete
    public boolean deleteEvent(String eventID, String ownerID) {
        boolean deleted = repository.deleteEvent(eventID, ownerID);
        if (deleted) {
            for (IEventDeletionObserver observer: observers) {
                observer.handleEventDeletion(eventID);
            }
        }
        return deleted;
    }
}

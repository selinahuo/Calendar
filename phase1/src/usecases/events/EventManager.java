package usecases.events;

import entities.CalendarEvent;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.UUID;

class EventManager implements IEventManager {
    private IEventRepository repository;

    public EventManager(IEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean createEvent(CalendarEvent event) {
        return this.repository.saveEvent(event);
    }

    @Override
    public boolean createEvent(String eventName, GregorianCalendar start, GregorianCalendar end, String location, String userID) {
        CalendarEvent event = new CalendarEvent(UUID.randomUUID().toString(), eventName, start, end, location, userID);
        return this.repository.saveEvent(event);
    }

    @Override
    public CalendarEvent getEventByID(String id) {
        return this.repository.fetchEventByID(id);
    }

    @Override
    public CalendarEvent[] getEventsByIDs(String[] ids) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] getEventsByNameAndUserID(String name, String userID) {
        return this.repository.fetchEventsByNameAndUserID(name, userID);
    }

    @Override
    public CalendarEvent[] getEventsByDateAndUserID(GregorianCalendar before, GregorianCalendar after, String userID) {
        return this.repository.fetchEventsByDateAndUserID(before, after, userID);
    }

    @Override
    public CalendarEvent[] getEventsByDateBeforeAndUserID(GregorianCalendar before, String userID) {
        return this.repository.fetchEventsByDateBeforeAndUserID(before, userID);
    }

    @Override
    public CalendarEvent[] getEventsByDateAfterAndUserID(GregorianCalendar after, String userID) {
        return this.repository.fetchEventsByDateAfterAndUserID(after, userID);
    }

    @Override
    public CalendarEvent[] getEventsBySeriesIDAndUserID(String seriesID, String userID) {
        return this.repository.fetchEventBySeriesIDAndUserID(seriesID, userID);
    }

    @Override
    public boolean editEventSeriesID(String eventID, String newSeriesID) {
        return false;
    }

    @Override
    public CalendarEvent[] getEventsByTagIDAndUserID(String tagID, String userID) {
        return this.repository.fetchEventsByTagIDAndUserID(tagID, userID);
    }

    @Override
    public boolean setEventTagIDs(ArrayList<String> tagIDs, String eventID, String userID) {
        return this.repository.setEventTagIDs(tagIDs, eventID, userID);
    }

    @Override
    public CalendarEvent[] getEventsByMemoIDAndUserID(String memoID, String userID) {
        return this.repository.fetchEventsByMemoIDAndUserID(memoID, userID);
    }

    @Override
    public boolean setEventMemoIDs(ArrayList<String> memoIDs, String eventID, String userID) {
        return this.repository.setEventMemoIDs(memoIDs, eventID, userID);
    }

    @Override
    public CalendarEvent getEventByAlertIDAndUserID(String alertID, String userID) {
        return this.repository.fetchEventByAlertIDAndUserID(alertID, userID);
    }
}

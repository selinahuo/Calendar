package usecases.events;

import entities.CalendarEvent;

import java.util.Date;

class EventManager implements IEventManager {
    private IEventRepository repository;

    @Override
    public boolean createEvent(CalendarEvent event) {
        return false;
    }

    @Override
    public CalendarEvent getEventByID(String id) {
        return null;
    }

    @Override
    public CalendarEvent[] getEventByIDs(String[] ids) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] getEventByNameAndUserID(String name, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] getEventByDateAndUserID(Date before, Date after, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] getEventByDateBeforeAndUserID(Date before, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] getEventByDateAfterAndIDs(Date after, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] getEventsBySeriesIDAndUserID(String seriesID, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent editEventSeriesID(String eventID, String newSeriesID) {
        return null;
    }

    @Override
    public CalendarEvent[] fetchEventsByTagIDAndUserID(String tagID, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByMemoIDAndUserID(String memoID, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByAlertIDAndUserID(String alertID, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent editEventAlertID(String eventID, String newAlertID) {
        return null;
    }
}

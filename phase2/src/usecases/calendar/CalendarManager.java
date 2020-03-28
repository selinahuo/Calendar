package usecases.calendar;

import entities.CalendarEvent;
import entities.Calendars;
import entities.Memo;
import usecases.events.EventManager;
import usecases.events.IEventDeletionObserver;

import java.util.ArrayList;

public class CalendarManager implements IEventDeletionObserver {
    private ICalendarRepository repository;
    private EventManager eventManager;

    public CalendarManager(ICalendarRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    // save
    public String createCalendar(String userID, String calendarName) {
        Calendars calendar = new Calendars(userID, calendarName);
        repository.saveCalendar(calendar);
        return calendar.getCalendarID();
    }

    // get plural
    public ArrayList<Calendars> getCalendarsByOwnerID(String ownerID) {
        return repository.fetchCalendarsByOwnerID(ownerID);
    }

    public boolean editCalendarName(String calendarID, String name, String ownerID) {
        return repository.editCalendarName(calendarID, name, ownerID);
    }

    // Add/remove events
    public boolean addEventToCalendar(String eventID, String calendarID, String ownerID) {
        Calendars calendar = repository.fetchCalendarByCalendarIDAndOwnerID(calendarID, ownerID);
        if (calendar != null) {
            boolean success = eventManager.editCalendarID(eventID, calendarID, ownerID);
            if (success) {
                repository.editCalendarCountAdd(calendarID, ownerID);
            }
            return success;
        }
        return false;
    }

    public boolean removeEventFromCalendar(String eventID, String ownerID) {
        CalendarEvent event = eventManager.getEventByIDAndUserID(eventID, ownerID);
        if (event == null) {
            return false;
        }
        String calendarID = event.getCalendarID();
        if (calendarID == "") {
            return true;
        }
        eventManager.editCalendarID(eventID, "", ownerID);
        repository.editCalendarCountRemove(calendarID, ownerID);
        return true;
    }

    public boolean deleteCalendar(String calendarID, String ownerID) {
        Calendars calendar = repository.fetchCalendarByCalendarIDAndOwnerID(calendarID, ownerID);
        if (calendar.getCount() <= 0) {
            return repository.deleteCalendar(calendarID);
        }
        return false;
    }

    @Override
    public void handleEventDeletion(CalendarEvent event) {
        repository.editCalendarCountRemove(event.getCalendarID(), event.getUserID());
    }
}

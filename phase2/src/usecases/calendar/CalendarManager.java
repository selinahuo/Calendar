package usecases.calendar;

import entities.CalendarEvent;
import entities.Calendars;
import usecases.events.EventManager;
import usecases.events.IEventDeletionObserver;

import java.util.ArrayList;

public class CalendarManager implements IEventDeletionObserver {
    private ICalendarRepository repository;
    private EventManager eventManager;

    /**
     * Constructor for CalendarManager.
     *
     * @param repository the repository associated with Calendar
     * @param eventManager the eventManager of the calendar events that the Calendar is associated to
     */
    public CalendarManager(ICalendarRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    /**
     * Create an Individual Calendar.
     *
     * @param userID the user that that this calendar belongs to
     * @param calendarName the name of this calendar
     * @return id of the calendar created
     */
    public String createCalendar(String userID, String calendarName) {
        Calendars calendar = new Calendars(userID, calendarName);
        repository.saveCalendar(calendar);
        return calendar.getCalendarID();
    }

    /**
     * Get a Calendar by its ID and the ownerID
     *
     * @param calendarID the calendar's unique ID
     * @param ownerID the owner of a calendar
     * @return calendar or null if it does not exist
     */
    public Calendars getCalendarByIDAndOwnerID(String calendarID, String ownerID) {
        return repository.fetchCalendarByCalendarIDAndOwnerID(calendarID, ownerID);
    }

    /**
     * Get all the calendar that the user belongs to
     *
     * @param ownerID the owner of the list of calendars returned
     * @return list of calendars that belongs to the user
     */
    public ArrayList<Calendars> getCalendarsByOwnerID(String ownerID) {
        return repository.fetchCalendarsByOwnerID(ownerID);
    }

    /**
     * Edit the calendar's name.
     *
     * @param calendarID the calendarID of the calendar being modified
     * @param newName the new name of this calendar
     * @param ownerID the userID of the user that is associated with this calendar
     * @return true if modification is successful
     */
    public boolean editCalendarName(String calendarID, String newName, String ownerID) {
        return repository.editCalendarName(calendarID, newName, ownerID);
    }

    /**
     * Add event to the calendar
     *
     * @param eventID the ID of the event being added to the calendar
     * @param calendarID the ID of the calendar where the event is being added
     * @param ownerID the userID of the user that is associated with this calendar
     * @return true if modification is successful
     */
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

    /**
     * Remove event from the calendar
     *
     * @param eventID the ID of the event being removed from the calendar
     * @param ownerID the userID of the user that is associated with this calendar
     * @return true if modification is successful
     */
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

    /**
     * Delete the Calendar
     *
     * @param calendarID the ID of the calendar being deleted
     * @param ownerID the userID of the user who has the calendar
     * @return true is modification is successful
     */
    public boolean deleteCalendar(String calendarID, String ownerID) {
        Calendars calendar = repository.fetchCalendarByCalendarIDAndOwnerID(calendarID, ownerID);
        if (calendar.getCount() <= 0) {
            return repository.deleteCalendar(calendarID);
        }
        return false;
    }

    /**
     * Decrement count of calendar with deleted event
     *
     * @param event the event being deleted
     */
    @Override
    public void handleEventDeletion(CalendarEvent event) {
        repository.editCalendarCountRemove(event.getCalendarID(), event.getUserID());
    }
}

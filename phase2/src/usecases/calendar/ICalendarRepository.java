package usecases.calendar;

import entities.CalendarEvent;
import entities.Calendars;
import java.util.ArrayList;

/**
 * Provides a public interface for calendar repository operations
 */
public interface ICalendarRepository {

    /**
     * Saves an calendar to the alert repository.
     *
     * @param calendar the calendar instance that is being saved
     */
    void saveCalendar(Calendars calendar);

    //Fetch - singular - Calendars

    /**
     * Returns an calendar instance of the given calendarID and ownerID.
     *
     * @param calendarID the calendarID of the desired alert
     * @param ownerID the userID of the user that is associated with the desired Calendar
     * @return the desired calendar instance
     */
    Calendars fetchCalendarByCalendarIDAndOwnerID(String calendarID, String ownerID);

    // Fetch - plural

    /**
     * Returns calendars of the given user.
     *
     * @param ownerID the userID of the user that is associated with the desired Calendars
     * @return a list of calendars of the user
     */
    ArrayList<Calendars> fetchCalendarsByOwnerID(String ownerID);

    //edit

    /**
     * Returns whether the modification of Calendar's name is successful.
     *
     * @param calendarID the calendarID of the Calendar instance that is being modified
     * @param name the new name of the Calendar
     * @param ownerID the userID of the user that is associated with this Calendar instance
     * @return true if modification is successful, else return false
     */
    boolean editCalendarName(String calendarID, String name, String ownerID);

    /**
     * Returns whether the adding the event count to the calendar is successful.
     *
     * @param calendarID the calendarID of the Calendar instance that is being modified
     * @param ownerID the userID of the user that is associated with this Calendar instance
     * @return true if modification is successful, else return false
     */
    boolean editCalendarCountAdd(String calendarID, String ownerID);

    /**
     * Returns whether the removing the event count to the calendar is successful.
     *
     * @param calendarID the calendarID of the Calendar instance that is being modified
     * @param ownerID the userID of the user that is associated with this Calendar instance
     * @return true if modification is successful, else return false
     */
    boolean editCalendarCountRemove(String calendarID, String ownerID);

    /**
     * Return whether calendar deletion is successful.
     *
     * @param calendarID the calendarID of the calendar instance that is being deleted
     * @return true if deletion is successful, else return false
     */
    boolean deleteCalendar(String calendarID);
}


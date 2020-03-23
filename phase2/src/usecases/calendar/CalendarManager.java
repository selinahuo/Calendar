package usecases.calendar;

import entities.Calendars;

import java.util.ArrayList;

public class CalendarManager {

    private ICalendarRepository repository;

    public CalendarManager(ICalendarRepository repository) {
        this.repository = repository;
    }

    // save
    public String createCalendar(String userID, String calendarName) {
        Calendars calendar = new Calendars(userID, calendarName);
        boolean success = repository.saveCalendar(calendar);
        if (success) {
            return calendar.getCalendarID();
        } else {
            return null;
        }
    }

    // get - singular
    public Calendars getCalendarByCalendarID(String calendarID) {
        return repository.fetchCalendarByCalendarID(calendarID);
    }

    // get plural
    public ArrayList<Calendars> getCalendarByOwnerID(String ownerID) {
        return repository.fetchCalendarByOwnerID(ownerID);
    }

}

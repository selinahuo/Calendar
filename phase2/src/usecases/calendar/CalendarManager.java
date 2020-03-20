package usecases.calendar;

import entities.Calendar;

import java.util.ArrayList;

public class CalendarManager {

    private ICalendarRepository repository;

    public CalendarManager(ICalendarRepository repository) {
        this.repository = repository;
    }

    // save
    public String createCalendar(String userID, String calendarName) {
        Calendar calendar = new Calendar(userID, calendarName);
        boolean success = repository.saveCalendar(calendar);
        if (success) {
            return calendar.getCalendarID();
        } else {
            return null;
        }
    }

    // get - singular
    public Calendar getCalendarByCalendarID(String calendarID) {
        return repository.fetchCalendarByCalendarID(calendarID);
    }

    // get plural
    public ArrayList<Calendar> getCalendarByOwnerID(String ownerID) {
        return repository.fetchCalendarByOwnerID(ownerID);
    }

}

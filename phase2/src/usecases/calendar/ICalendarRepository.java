package usecases.calendar;

import entities.CalendarEvent;
import entities.Calendars;
import java.util.ArrayList;

public interface ICalendarRepository {
    void saveCalendar(Calendars calendar);

    //Fetch - singular
    Calendars fetchCalendarByCalendarIDAndOwnerID(String calendarID, String ownerID);

    // Fetch - plural
    ArrayList<Calendars> fetchCalendarsByOwnerID(String ownerID);

    //edit
    boolean editCalendarName(String calendarID, String name, String ownerID);
    boolean editCalendarCountAdd(String calendarID, String ownerID);
    boolean editCalendarCountRemove(String calendarID, String ownerID);

    boolean moveEventToCalendarByCalendarID(CalendarEvent Event, String calendarID);

    boolean moveEventToCalendarByCalendarName(CalendarEvent Event, String name);

    boolean removeEventFromCalendarByCalendarID(CalendarEvent Event, String calendarID);

    boolean removeEventFromCalendarByCalendarName(CalendarEvent Event, String name);

    boolean getEvents(String calendarID);

    //Delete
    boolean deleteCalendar(String calendarID);
}


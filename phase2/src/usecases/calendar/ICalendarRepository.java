package usecases.calendar;

import entities.Calendars;
import java.util.ArrayList;

public interface ICalendarRepository {


    boolean saveCalendar(Calendars calendar);

    //Fetch - singular
    Calendars fetchCalendarByCalendarID(String calendarID);

    // Fetch - plural
    ArrayList<Calendars> fetchCalendarsByOwnerID(String ownerID);

    //edit
    boolean editCalendarName(String calendarID, String name);

    //Delete
    boolean deleteCalendar(String calendarID);

}


package usecases.calendar;

import entities.Calendar;
import java.util.ArrayList;

public interface ICalendarRepository {


    boolean saveCalendar(Calendar calendar);

    //Fetch - singular
    Calendar fetchCalendarByCalendarID(String calendarID);

    // Fetch - plural
    ArrayList<Calendar> fetchCalendarByOwnerID(String ownerID);

    //edit
    boolean editCalendarName(String calendarID, String name);

    //Delete
    boolean deleteCalendar(String calendarID);

}


package dataaccess;

import entities.Calendar;
import usecases.calendar.ICalendarRepository;

import java.util.ArrayList;

public class SerializableCalendarRepository extends SerializableRepository<Calendar> implements ICalendarRepository {


    public SerializableCalendarRepository(String serFile) {
        super("calendars.ser");
    }

    @Override
    public boolean saveCalendar(Calendar calendar) {
        return false;
    }

    @Override
    public Calendar fetchCalendarByCalendarID(String calendarID) {
        return null;
    }

    @Override
    public ArrayList<Calendar> fetchCalendarByOwnerID(String ownerID) {
        return null;
    }

    @Override
    public boolean editCalendarName(String calendarID, String name) {
        return false;
    }

    @Override
    public boolean deleteCalendar(String calendarID) {
        return false;
    }
}
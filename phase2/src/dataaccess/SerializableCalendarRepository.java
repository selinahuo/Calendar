package dataaccess;

import entities.Alert;
import entities.Calendars;
import usecases.calendar.ICalendarRepository;

import java.util.ArrayList;

public class SerializableCalendarRepository extends SerializableRepository<Calendars> implements ICalendarRepository {


    public SerializableCalendarRepository(String serFile) {
        super("calendars.ser");
    }

    @Override
    public boolean saveCalendar(Calendars calendar) {
        ArrayList<Calendars> calendars = deserialize();
        calendars.add(calendar);
        serialize(calendars);
        return true;
    }

    @Override
    public Calendars fetchCalendarByCalendarID(String calendarID) {
        return fetchSingular((Calendars calendars) -> calendars.getCalendarID() != null
                && calendars.getCalendarID().equals(calendarID));
    }

    @Override
    public ArrayList<Calendars> fetchCalendarByOwnerID(String ownerID) {
        return fetchPlural((Calendars calendars) -> calendars.getOwnerID() != null &&
                calendars.getOwnerID().equals(ownerID));
    }

    @Override
    public boolean editCalendarName(String calendarID, String name) {

        ArrayList<Calendars> calendarsArr = deserialize();
        for (Calendars calendar: calendarsArr) {
            if (calendar.getCalendarID().equals(calendarID)) {
                calendar.setCalendarName(name);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteCalendar(String calendarID) {
        return false;
    }
}
package dataaccess;

import entities.Calendars;
import usecases.calendar.ICalendarRepository;

import java.util.ArrayList;

class SerializableCalendarRepository extends SerializableRepository<Calendars> implements ICalendarRepository {
    SerializableCalendarRepository() {
        super("./calendars.ser");
    }

    @Override
    public void saveCalendar(Calendars calendar) {
        saveItem(calendar);
    }

    @Override
    public Calendars fetchCalendarByCalendarIDAndOwnerID(String calendarID, String ownerID) {
        return fetchSingular((Calendars c) -> c.getCalendarID().equals(calendarID) && c.getOwnerID().equals(ownerID));
    }

    @Override
    public ArrayList<Calendars> fetchCalendarsByOwnerID(String ownerID) {
        return fetchPlural((Calendars c) -> c.getOwnerID().equals(ownerID));
    }

    @Override
    public boolean editCalendarName(String calendarID, String name, String ownerID) {
        return editSingular(
                (Calendars c) -> c.getCalendarID().equals(calendarID) && c.getOwnerID().equals(ownerID),
                (Calendars c) -> c.setCalendarName(name)
        );
    }

    @Override
    public boolean editCalendarCountAdd(String calendarID, String ownerID) {
        return editSingular(
                (Calendars c) -> c.getCalendarID().equals(calendarID) && c.getOwnerID().equals(ownerID),
                Calendars::addCount
        );
    }

    @Override
    public boolean editCalendarCountRemove(String calendarID, String ownerID) {
        return editSingular(
                (Calendars c) -> c.getCalendarID().equals(calendarID) && c.getOwnerID().equals(ownerID),
                Calendars::removeCount
        );
    }

    @Override
    public boolean deleteCalendar(String calendarID) {
        return deleteSingular((Calendars c) -> c.getCalendarID().equals(calendarID));
    }
}
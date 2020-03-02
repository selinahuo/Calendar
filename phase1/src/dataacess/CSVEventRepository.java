package dataacess;

import entities.CalendarEvent;
import usecases.events.IEventRepository;

import java.util.Date;

public class CSVEventRepository implements IEventRepository {
    /**
     * Save a CalendarEvent.
     * @param event the event to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean saveEvent(CalendarEvent event) {
        System.out.println(event.getName());
        return true;
    }

    /**
     * Fetch an CalendarEvent by its ID.
     *
     * @param id the ID to filter by.
     * @return the corresponding CalendarEvent or null if it does not exist
     */
    @Override
    public CalendarEvent fetchEventByID(String id) {
        return null;
    }

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs
     *
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventByIDs(String[] ids) {
        return new CalendarEvent[0];
    }

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs and a matching name
     *
     * @param name events must have this name
     * @param ids  returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventByNameAndIDs(String name, String[] ids) {
        return new CalendarEvent[0];
    }

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs in the specified appropriate Date range
     *
     * @param before returned CalendarEvents must start before this Date
     * @param after  returned CalendarEvents must end after this Date
     * @param ids    returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventByDateAndIDs(Date before, Date after, String[] ids) {
        return new CalendarEvent[0];
    }

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs that end before a Date
     *
     * @param before returned CalendarEvents must end before this Date
     * @param ids    returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventByDateBeforeAndIDs(Date before, String[] ids) {
        return new CalendarEvent[0];
    }

    /**
     * fetch all CalendarEvents that have an ID in a list of IDs that start after a Date
     *
     * @param after returned CalendarEvents must start after this Date
     * @param ids   returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventByDateAfterAndIDs(Date after, String[] ids) {
        return new CalendarEvent[0];
    }
}

package usecases.events;

import entities.CalendarEvent;

import java.util.Date;

class EventManager implements IEventManager {
    private IEventRepository repository;

    EventManager(IEventRepository repository) {
        this.repository = repository;
    }

    /**
     * Create and save a CalendarEvent.
     * @param event the event to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean createEvent(CalendarEvent event) {
        this.repository.saveEvent(event);
        return true;
    }

    /**
     * Get a CalendarEvent by its ID.
     *
     * @param id the ID to filter by.
     * @return the corresponding CalendarEvent or null if it does not exist
     */
    @Override
    public CalendarEvent getEventByID(String id) {
        return null;
    }

    /**
     * Get all CalendarEvents that have an ID in a list of IDs
     *
     * @param ids returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] getEventByIDs(String[] ids) {
        return new CalendarEvent[0];
    }

    /**
     * Get all CalendarEvents that have an ID in a list of IDs and a matching name
     *
     * @param name events must have this name
     * @param ids  returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] getEventByNameAndIDs(String name, String[] ids) {
        return null;
    }

    /**
     * Get all CalendarEvents that have an ID in a list of IDs in the specified appropriate Date range
     *
     * @param before returned CalendarEvents must start before this Date
     * @param after  returned CalendarEvents must end after this Date
     * @param ids    returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] getEventByDateAndIDs(Date before, Date after, String[] ids) {
        return null;
    }

    /**
     * Get all CalendarEvents that have an ID in a list of IDs that end before a Date
     *
     * @param before returned CalendarEvents must end before this Date
     * @param ids    returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] getEventByDateBeforeAndIDs(Date before, String[] ids) {
        return null;
    }

    /**
     * Get all CalendarEvents that have an ID in a list of IDs that start after a Date
     *
     * @param after returned CalendarEvents must start after this Date
     * @param ids   returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] getEventByDateAfterAndIDs(Date after, String[] ids) {
        return null;
    }
}

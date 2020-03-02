package usecases.event;

import entities.CalendarEvent;

/**
 * Provides a public interface for event repository operations
 */
public interface IEventRepository {
    /**
     * Save a CalendarEvent in the repository.
     * @param event the event to be saved
     */
    void save(CalendarEvent event);

    /**
     * Get a CalendarEvent by ID.
     * @param id the ID to retrieve by
     * @return matching CalendarEvent
     */
    CalendarEvent getByID(String id);
}

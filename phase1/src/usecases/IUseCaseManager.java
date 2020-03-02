package usecases;

import entities.CalendarEvent;

/**
 * IUseCaseManager provides a public interface for all use cases
 */
public interface IUseCaseManager {
    /**
     * Create a CalendarEvent and return it.
     * @param event the event to be created
     * @return the event that was created
     */
    CalendarEvent createEvent(CalendarEvent event);
}

package usecases.event;

import entities.CalendarEvent;

/**
 * IEventManager provides a public interface for event operations
 */
public interface IEventManager {
    /**
     * Create and save a CalendarEvent.
     * @param event the event to save
     * @return the created CalendarEvent
     */
    CalendarEvent createEvent(CalendarEvent event);
}

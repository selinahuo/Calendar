package usecases.events;

import entities.CalendarEvent;

/**
 * Observer interface for managers that must subscribe to event deletion
 */
public interface IEventDeletionObserver {
    /**
     * React to event deletion
     * @param event event which was deleted and reacted to
     */
    public void handleEventDeletion(CalendarEvent event);
}

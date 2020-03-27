package usecases.events;

import entities.CalendarEvent;

public interface IEventDeletionObserver {
    public void handleEventDeletion(CalendarEvent event);
}

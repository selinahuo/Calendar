package usecases.event;

import entities.CalendarEvent;

public interface IEventManager {
    CalendarEvent createEvent(CalendarEvent event);
}

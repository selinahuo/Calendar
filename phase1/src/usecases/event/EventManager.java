package usecases.event;

import entities.CalendarEvent;

public class EventManager implements IEventManager {
    private IEventRepository repository;

    public EventManager(IEventRepository repository) {
        this.repository = repository;
    }

    public CalendarEvent createEvent(CalendarEvent event) {
        this.repository.save(event);
        return event;
    }
}

package usecases.event;

import entities.CalendarEvent;

class EventManager implements IEventManager {
    private IEventRepository repository;

    EventManager(IEventRepository repository) {
        this.repository = repository;
    }

    public CalendarEvent createEvent(CalendarEvent event) {
        this.repository.save(event);
        return event;
    }
}

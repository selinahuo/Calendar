package usecases.event;

import entities.CalendarEvent;

public interface IEventRepository {
    void save(CalendarEvent event);
    CalendarEvent getByID(String id);
}

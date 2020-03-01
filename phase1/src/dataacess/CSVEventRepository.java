package dataacess;

import entities.CalendarEvent;
import usecases.event.IEventRepository;

public class CSVEventRepository implements IEventRepository {
    public void save(CalendarEvent eventData) {
        System.out.println(eventData.getName());
    }
    public CalendarEvent getByID(String id) {
        return new CalendarEvent(id, "Test Event", null, null, "Example");
    }
}

package dataaccess;

import entities.CalendarEvent;
import entities.Tag;
import usecases.events.IEventRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SerializableEventRepository implements IEventRepository {

    private ArrayList<CalendarEvent> calendarEvents;

    public SerializableEventRepository() {
        this.calendarEvents = new ArrayList<CalendarEvent>();
    }

    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream("calenderEvents");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.calendarEvents);
            oos.close();
            fos.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void deserialize() {
        try {
            FileInputStream fis = new FileInputStream("calenderEvents");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.calendarEvents = (ArrayList<CalendarEvent>) ois.readObject();
            ois.close();
            fis.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    /**
     * Saves an event.
     *
     * @param event the event to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean saveEvent(CalendarEvent event) {
        deserialize();
        this.calendarEvents.add(event);
        serialize();
        return true;
    }

    /**
     * Fetch an event by its id.
     *
     * @param id the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    @Override
    public CalendarEvent fetchEventByID(String id) {
        deserialize();
        for (CalendarEvent calenderEvent : calendarEvents) {
            if (calenderEvent.getEventID() == id) {
                return calenderEvent;
            }
        }
        return null;
    }

    @Override
    public CalendarEvent[] fetchEventsByNameAndUserID(String name, String userID) {
        deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getName().equals(name) && calendarEvent.getUserID().equals(userID)) {
                events.add(calendarEvent);
            }
        }
        CalendarEvent[] arr_events = new CalendarEvent[events.size()];
        arr_events = events.toArray(arr_events);
        return arr_events;
    }

    @Override
    public CalendarEvent[] fetchEventsByDateAndUserID(GregorianCalendar before, GregorianCalendar after, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByDateBeforeAndUserID(GregorianCalendar before, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByDateAfterAndUserID(GregorianCalendar after, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventBySeriesIDAndUserID(String seriesID, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public boolean editEventSeriesID(String eventID, String newSeriesID) {
        return false;
    }

    @Override
    public CalendarEvent[] fetchEventsByTagIDAndUserID(String tagID, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByMemoIDAndUserID(String memoID, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent fetchEventByAlertIDAndUserID(String alertID, String userID) {
        return null;
    }
}

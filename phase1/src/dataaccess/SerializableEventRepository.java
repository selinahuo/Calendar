package dataaccess;

import entities.CalendarEvent;
import usecases.events.IEventRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SerializableEventRepository implements IEventRepository {

    private ArrayList<CalendarEvent> calendarEvents;

    public SerializableEventRepository() {
        this.calendarEvents = new ArrayList<CalendarEvent>();
    }

    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream("events.ser");
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
            FileInputStream fis = new FileInputStream("events.ser");
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
            if (calenderEvent.getEventID().equals(id)) {
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

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs in the specified appropriate Date range
     * @param before returned CalendarEvents must start before this Date
     * @param after returned CalendarEvents must end after this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventsByDateAndUserID(GregorianCalendar before, GregorianCalendar after, String userID) {
        deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getUserID().equals(userID)) {
                if (calendarEvent.getStart().before(before)
                        && calendarEvent.getEnd().after(after)) {
                    events.add(calendarEvent);
                }
            }
        }
        CalendarEvent[] arr_events = new CalendarEvent[events.size()];
        arr_events = events.toArray(arr_events);
        return arr_events;
    }

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs that end before a Date
     * @param before returned CalendarEvents must end before this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventsByDateBeforeAndUserID(GregorianCalendar before, String userID) {
        deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getUserID().equals(userID)) {
                if (calendarEvent.getEnd().before(before)) {
                    events.add(calendarEvent);
                }
            }
        }
        CalendarEvent[] arr_events = new CalendarEvent[events.size()];
        arr_events = events.toArray(arr_events);
        return arr_events;
    }

    /**
     * fetch all CalendarEvents that have an ID in a list of IDs that start after a Date
     * @param after returned CalendarEvents must start after this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventsByDateAfterAndUserID(GregorianCalendar after, String userID) {
        deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getUserID().equals(userID)) {
                if (calendarEvent.getStart().after(after)) {
                    events.add(calendarEvent);
                }
            }
        }
        CalendarEvent[] arr_events = new CalendarEvent[events.size()];
        arr_events = events.toArray(arr_events);
        return arr_events;
    }

    /**
     * fetch all CalendarEvents that have matching series ID and user ID
     * @param seriesID returned CalendarEvents must belong to the series
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventBySeriesIDAndUserID(String seriesID, String userID) {
        deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getUserID().equals(userID) && calendarEvent.getSeriesID().equals(seriesID)) {
                events.add(calendarEvent);
            }
        }
        CalendarEvent[] arr_events = new CalendarEvent[events.size()];
        arr_events = events.toArray(arr_events);
        return arr_events;
    }

    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param eventID returned CalendarEvents must belong to the series
     * @param newSeriesID new Series ID for the event
     * @return True if successfully updated, false otherwise
     */
    @Override
    public boolean editEventSeriesID(String eventID, String newSeriesID) {
        deserialize();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getEventID().equals(eventID)) {
                calendarEvent.setSeriesID(newSeriesID);
                return true;
            }
        }
        return false;
    }

    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param tagID returned CalendarEvents must belong to the tag
     * @param userID returned CalendarEvents must belong to the tag
     * @return list of matching CalenderEvents
     */
    @Override
    public CalendarEvent[] fetchEventsByTagIDAndUserID(String tagID, String userID) {
        deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getTagIDs().equals(tagID) && calendarEvent.getUserID().equals(userID)) {
                events.add(calendarEvent);
            }
        }
        CalendarEvent[] arr_events = new CalendarEvent[events.size()];
        arr_events = events.toArray(arr_events);
        return arr_events;
    }

    @Override
    public boolean setEventTagIDs(ArrayList<String> tagIDs, String eventID, String userID) {
        deserialize();
        CalendarEvent eventToEdit = this.fetchEventByID(eventID);
        if (eventToEdit == null || !eventToEdit.getUserID().equals(userID)) {
            return false;
        }
        eventToEdit.setTagIDs(tagIDs);
        serialize();
        return true;
    }

    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param memoID returned CalendarEvents must belong to the memo
     * @param userID returned CalendarEvents must belong to the tag
     * @return list of matching CalenderEvents
     */
    @Override
    public CalendarEvent[] fetchEventsByMemoIDAndUserID(String memoID, String userID) {
        deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getMemoIDs().contains(memoID) && calendarEvent.getUserID().equals(userID)) {
                events.add(calendarEvent);
            }
        }
        CalendarEvent[] arr_events = new CalendarEvent[events.size()];
        arr_events = events.toArray(arr_events);
        return arr_events;
    }

    @Override
    public boolean setEventMemoIDs(ArrayList<String> memoIDs, String eventID, String userID) {
        deserialize();
        CalendarEvent eventToEdit = this.fetchEventByID(eventID);
        if (eventToEdit == null || !eventToEdit.getUserID().equals(userID)) {
            return false;
        }
        eventToEdit.setMemoIDs(memoIDs);
        serialize();
        return true;
    }

    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param alertID returned CalendarEvents must belong to the alert
     * @param userID returned CalendarEvents must belong to the tag
     * @return list of matching CalenderEvents
     */
    @Override
    public CalendarEvent fetchEventByAlertIDAndUserID(String alertID, String userID) {
        deserialize();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getMemoIDs().contains(alertID) && calendarEvent.getUserID().equals(userID)) {
                return calendarEvent;
            }
        }
        return null;
    }
}

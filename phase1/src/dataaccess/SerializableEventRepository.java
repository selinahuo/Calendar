package dataaccess;

import entities.CalendarEvent;
import usecases.events.IEventRepository;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SerializableEventRepository extends SerializableRepository <CalendarEvent> implements IEventRepository {
    public SerializableEventRepository() {
        super("events.ser");
    }

    /**
     * Saves an event.
     *
     * @param event the event to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean saveEvent(CalendarEvent event) {
        ArrayList<CalendarEvent> events = deserialize();
        events.add(event);
        serialize(events);
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
        ArrayList<CalendarEvent> events = deserialize();
        for (CalendarEvent calenderEvent : events) {
            if (calenderEvent.getEventID().equals(id)) {
                return calenderEvent;
            }
        }
        return null;
    }

    @Override
    public CalendarEvent[] fetchEventsByNameAndUserID(String name, String userID) {
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getName().equals(name) && calendarEvent.getUserID().equals(userID)) {
                events.add(calendarEvent);
            }
        }
        CalendarEvent[] arrEvents = new CalendarEvent[events.size()];
        events.toArray(arrEvents);
        return arrEvents;
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
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getUserID().equals(userID)) {
                if (calendarEvent.getStart().before(before)
                        && calendarEvent.getEnd().after(after)) {
                    events.add(calendarEvent);
                }
            }
        }
        CalendarEvent[] arrEvents = new CalendarEvent[events.size()];
        events.toArray(arrEvents);
        return arrEvents;
    }

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs that end before a Date
     * @param before returned CalendarEvents must end before this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventsByDateBeforeAndUserID(GregorianCalendar before, String userID) {
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getUserID().equals(userID)) {
                if (calendarEvent.getEnd().before(before)) {
                    events.add(calendarEvent);
                }
            }
        }
        CalendarEvent[] arrEvents = new CalendarEvent[events.size()];
        events.toArray(arrEvents);
        return arrEvents;
    }

    /**
     * fetch all CalendarEvents that have an ID in a list of IDs that start after a Date
     * @param after returned CalendarEvents must start after this Date
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventsByDateAfterAndUserID(GregorianCalendar after, String userID) {
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getUserID().equals(userID)) {
                if (calendarEvent.getStart().after(after)) {
                    events.add(calendarEvent);
                }
            }
        }
        CalendarEvent[] arrEvents = new CalendarEvent[events.size()];
        events.toArray(arrEvents);
        return arrEvents;
    }

    /**
     * fetch all CalendarEvents that have matching series ID and user ID
     * @param seriesID returned CalendarEvents must belong to the series
     * @param userID returned CalendarEvents must belong to this user
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventBySeriesIDAndUserID(String seriesID, String userID) {
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            System.out.println(calendarEvent.getName());
            if (calendarEvent.getUserID().equals(userID) && calendarEvent.getSeriesID().equals(seriesID)) {
                events.add(calendarEvent);
            }
        }
        CalendarEvent[] arrEvents = new CalendarEvent[events.size()];
        events.toArray(arrEvents);
        return arrEvents;
    }

    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param eventID returned CalendarEvents must belong to the series
     * @param newSeriesID new Series ID for the event
     * @return True if successfully updated, false otherwise
     */
    @Override
    public boolean editEventSeriesID(String eventID, String newSeriesID) {
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getEventID().equals(eventID)) {
                calendarEvent.setSeriesID(newSeriesID);
                serialize(calendarEvents);
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
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getTagIDs().equals(tagID) && calendarEvent.getUserID().equals(userID)) {
                events.add(calendarEvent);
            }
        }
        CalendarEvent[] arrEvents = new CalendarEvent[events.size()];
        events.toArray(arrEvents);
        return arrEvents;
    }

    @Override
    public boolean setEventTagIDs(ArrayList<String> tagIDs, String eventID, String userID) {
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getEventID().equals(eventID) && calendarEvent.getUserID().equals(userID)) {
                calendarEvent.setTagIDs(tagIDs);
                serialize(calendarEvents);
                return true;
            }
        }
        return false;
    }

    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param memoID returned CalendarEvents must belong to the memo
     * @param userID returned CalendarEvents must belong to the tag
     * @return list of matching CalenderEvents
     */
    @Override
    public CalendarEvent[] fetchEventsByMemoIDAndUserID(String memoID, String userID) {
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getMemoIDs().contains(memoID) && calendarEvent.getUserID().equals(userID)) {
                events.add(calendarEvent);
            }
        }
        CalendarEvent[] arrEvents = new CalendarEvent[events.size()];
        events.toArray(arrEvents);
        return arrEvents;
    }

    @Override
    public boolean setEventMemoIDs(ArrayList<String> memoIDs, String eventID, String userID) {
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getEventID().equals(eventID) && calendarEvent.getUserID().equals(userID)) {
                calendarEvent.setMemoIDs(memoIDs);
                serialize(calendarEvents);
                return true;
            }
        }
        return false;
    }

    /**
     * update the Series ID of the CalendarEvent that have the matching ID
     * @param alertID returned CalendarEvents must belong to the alert
     * @param userID returned CalendarEvents must belong to the tag
     * @return list of matching CalenderEvents
     */
    @Override
    public CalendarEvent fetchEventByAlertIDAndUserID(String alertID, String userID) {
        ArrayList<CalendarEvent> calendarEvents = deserialize();
        for (CalendarEvent calendarEvent: calendarEvents) {
            if (calendarEvent.getAlertID().equals(alertID) && calendarEvent.getUserID().equals(userID)) {
                return calendarEvent;
            }
        }
        return null;
    }
}

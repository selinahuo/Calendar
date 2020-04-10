package usecases.events;

import dataclasses.Tuple;
import entities.CalendarEvent;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager responsible for event use cases and helpers for other managers
 */
public class EventManager {
    private IEventRepository repository;
    private List<IEventDeletionObserver> observers = new ArrayList<>();

    /**
     * Construct an EventManager
     * @param repository injected event repository
     */
    public EventManager(IEventRepository repository) {
        this.repository = repository;
    }

    /**
     * Add event deletion observers to notify after deleting events
     * @param deletionObserver observer to add
     */
    public void addObservers(IEventDeletionObserver deletionObserver) {
        observers.add(deletionObserver);
    }

    /**
     * Create an event
     * @param name name of the event
     * @param start start datetime of event
     * @param end end datetime of event
     * @param location location of event
     * @param userID ID of event owner
     * @return new event ID
     */
    public String createEvent(String name, LocalDateTime start, LocalDateTime end, String location, String userID) {
        CalendarEvent event = new CalendarEvent(name, start, end, location, userID);
        repository.saveEvent(event);
        return event.getEventID();
    }

    /**
     * Create a holiday event using the first weekday of a month
     * @param name name of event
     * @param year year of the month
     * @param month the relative of month of the event
     * @param weekDay which first weekday to choose
     * @param location event location
     * @param userID ID of event owner
     * @return new event ID
     */
    public String createEventByFirstWeekDay(String name, int year, Month month, DayOfWeek weekDay, String location,
                                            String userID) {
        LocalDateTime day = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime start = day.with(firstInMonth(weekDay));
        LocalDateTime end = LocalDateTime.from(start).plusDays(1);
        return createEvent(name, start, end, location, userID);
    }

    /**
     * Get event by ID and associated user
     * @param eventID unique event ID
     * @param userID associated user ID
     * @return matching event entity
     */
    public CalendarEvent getEventByIDAndUserID(String eventID, String userID) {
        return repository.fetchEventByEventIDAndUserID(eventID, userID);
    }

    /**
     * Get event by ID and owner
     * @param eventID unique event ID
     * @param ownerID event owner ID
     * @return matching event entity
     */
    public CalendarEvent getEventByIDAndOwnerID(String eventID, String ownerID) {
        return repository.fetchEventByEventIDAndOwnerID(eventID, ownerID);
    }

    /**
     * Get event by associated alert and owner
     * @param alertID associated alert ID
     * @param ownerID event owner ID
     * @return matching event entity
     */
    public CalendarEvent getEventByAlertIDAndOwnerID(String alertID, String ownerID) {
        return repository.fetchEventByAlertIDAndOwnerID(alertID, ownerID);
    }

    /**
     * Get URL for directions to event's location
     * @param eventID unique event ID
     * @return URL string for event directions or null if event does not exist
     */
    public String getEventDirections(String eventID) {
        CalendarEvent event = repository.fetchEventByEventID(eventID);
        if (event == null) {
            return null;
        }
        return EventLinkGenerator.getEventDirections(event);
    }

    /**
     * Get URL for weather at event location
     * @param eventID unique event ID
     * @return URL string for event weather or null if event does not exist
     */
    public String getEventWeather(String eventID) {
        CalendarEvent event = repository.fetchEventByEventID(eventID);
        if (event == null) {
            return null;
        }
        return EventLinkGenerator.getEventWeather(event);
    }

    /**
     * Get URL for sharing an event on twitter
     * @param eventID ID of the event to share
     * @return URL string or null if event does not exist
     */
    public String getEventTwitterShare(String eventID) {
        CalendarEvent event = repository.fetchEventByEventID(eventID);
        if (event == null) {
            return null;
        }
        return EventLinkGenerator.getEventTwitterShare(event);
    }

    /**
     * Get URL for sharing an event through email
     * @param eventID ID of event to share
     * @return URL string or null if event does not exist
     */
    public String getEventEmailShare(String eventID) {
        CalendarEvent event = repository.fetchEventByEventID(eventID);
        if (event == null) {
            return null;
        }
        return EventLinkGenerator.getEventEmailShare(event);
    }

    /**
     * Get events that start before datetime and associated with user
     * @param start start before this datetime
     * @param userID associated user ID
     * @return list of matching event entities
     */
    public ArrayList<CalendarEvent> getEventsStartBeforeAndUserID(LocalDateTime start, String userID) {
        return repository.fetchEventsStartBeforeAndUserID(start, userID);
    }

    /**
     * Get events that start before and end after datetimes and associated with a user
     * @param before start before this datetime
     * @param after end after this datetime
     * @param userID associated user ID
     * @return list of matching event entities
     */
    public ArrayList<CalendarEvent> getEventsStartBeforeAndEndAfterAndUserID(LocalDateTime before, LocalDateTime after,
                                                                             String userID) {
        return repository.fetchEventsStartBeforeAndEndAfterAndUserID(before, after, userID);
    }

    /**
     * Get events that start after datetime and associated with a user
     * @param after start after this datetime
     * @param userID associated user ID
     * @return list of matching event entities
     */
    public ArrayList<CalendarEvent> getEventsStartAfterAndUserID(LocalDateTime after, String userID) {
        return repository.fetchEventsStartAfterAndUserID(after, userID);
    }

    /**
     * Get times and events in that time period for hour of a date and associated with a user
     * @param date date of events
     * @param hour hour of events
     * @param userID associated user ID
     * @return Tuple returning a list of times and a list of a list of events corresponding to each time
     */
    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByHourAndUserID(LocalDate date,
                                                                                                         int hour,
                                                                                                         String userID)
    {
        LocalDateTime hourStart = date.atTime(hour, 0);
        LocalDateTime hourEnd = LocalDateTime.from(hourStart).plusHours(1);
        ArrayList<CalendarEvent> hourEvents = repository.fetchEventsStartBeforeAndStartAfterAndUserID(hourEnd,
                hourStart, userID);

        ArrayList<LocalDateTime> times = new ArrayList<>();
        times.add(hourStart);
        ArrayList<ArrayList<CalendarEvent>> events = new ArrayList<>();
        events.add(hourEvents);
        return new Tuple<>(times, events);
    }

    /**
     * Get times and events in that time period for a day and associated with a user
     * @param date date of the events
     * @param userID associated user ID
     * @return Tuple returning a list of times and a list of a list of events corresponding to each time
     */
    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByDayAndUserID(LocalDate date,
                                                                                                        String userID) {
        LocalDateTime dayStart = date.atTime(0, 0);
        LocalDateTime dayEnd = LocalDateTime.from(dayStart).plusDays(1);
        ArrayList<CalendarEvent> dayEvents = repository.fetchEventsStartBeforeAndStartAfterAndUserID(dayEnd, dayStart,
                userID);

        ArrayList<LocalDateTime> times = new ArrayList<>();
        times.add(dayStart);
        ArrayList<ArrayList<CalendarEvent>> events = new ArrayList<>();
        events.add(dayEvents);
        return new Tuple<>(times, events);
    }

    /**
     * Get times and events in that time period for a week, broken down by day, and associated with a user
     * @param date date of the events
     * @param userID associated user ID
     * @return Tuple returning a list of times and a list of a list of events corresponding to each time
     */
    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByWeekAndUserID(LocalDate date,
                                                                                                         String userID)
    {
        LocalDateTime dayStart = date.atTime(0, 0);
        LocalDateTime dayEnd = LocalDateTime.from(dayStart).plusDays(1);
        ArrayList<LocalDateTime> times = new ArrayList<>();
        ArrayList<ArrayList<CalendarEvent>> events = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            ArrayList<CalendarEvent> dayEvents = repository.fetchEventsStartBeforeAndStartAfterAndUserID(dayEnd,
                    dayStart, userID);
            times.add(dayStart);
            events.add(dayEvents);
            dayStart = LocalDateTime.from(dayEnd);
            dayEnd = LocalDateTime.from(dayStart).plusDays(1);
        }
        return new Tuple<>(times, events);
    }

    /**
     * Get times and events in that time period for a month, broken down by day, and associated with a user
     * @param year year of the time frame
     * @param month month of the time frame
     * @param userID associated user ID
     * @return Tuple returning a list of times and a list of a list of events corresponding to each time
     */
    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByMonthAndUserID(int year,
                                                                                                          Month month,
                                                                                                          String userID)
    {
        LocalDate day = LocalDate.of(year, month, 1);
        LocalDateTime dayStart = day.atStartOfDay();
        LocalDateTime dayEnd = LocalDateTime.from(dayStart).plusDays(1);
        int daysInMonth = day.lengthOfMonth();

        ArrayList<LocalDateTime> times = new ArrayList<>();
        ArrayList<ArrayList<CalendarEvent>> events = new ArrayList<>();

        for (int i = 1; i <= daysInMonth; i++) {
            ArrayList<CalendarEvent> dayEvents = repository.fetchEventsStartBeforeAndStartAfterAndUserID(dayEnd,
                    dayStart, userID);
            times.add(dayStart);
            events.add(dayEvents);
            dayStart = LocalDateTime.from(dayEnd);
            dayEnd = LocalDateTime.from(dayStart).plusDays(1);
        }
        return new Tuple<>(times, events);
    }

    /**
     * Get events by name and associated user
     * @param name name of event
     * @param userID associated user ID
     * @return list of matching event entities
     */
    public ArrayList<CalendarEvent> getEventsByNameAndUserID(String name, String userID) {
        return repository.fetchEventsByNameAndUserID(name, userID);
    }

    /**
     * Get events by series and owner
     * @param seriesID ID of series
     * @param ownerID ID of owner
     * @return list of matching event entities
     */
    public ArrayList<CalendarEvent> getEventsBySeriesIDAndOwnerID(String seriesID, String ownerID) {
        return repository.fetchEventsBySeriesIDAndOwnerID(seriesID, ownerID);
    }

    /**
     * Get events by memo and owner
     * @param memoID ID of memo
     * @param ownerID ID of owner
     * @return list of matching event entities
     */
    public ArrayList<CalendarEvent> getEventsByMemoIDAndOwnerID(String memoID, String ownerID) {
        return repository.fetchEventsByMemoIDAndOwnerID(memoID, ownerID);
    }

    /**
     * Get events by tag and owner
     * @param tagID ID of tag
     * @param ownerID ID of owner
     * @return list of matching event entities
     */
    public ArrayList<CalendarEvent> getEventsByTagIDAndOwnerID(String tagID, String ownerID) {
        return repository.fetchEventsByTagIDAndOwnerID(tagID, ownerID);
    }

    /**
     * Get events by calendar and owner
     * @param calendarID ID of calendar
     * @param ownerID ID of owner
     * @return list of matching event entities
     */
    public ArrayList<CalendarEvent> getEventsByCalendarIDAndOwnerID(String calendarID, String ownerID) {
        return repository.fetchEventsByCalendarIDAndOwnerID(calendarID, ownerID);
    }

    /**
     * Get events by alert and owner
     * @param alertID ID of alert
     * @param ownerID ID of owner
     * @return list of matching event entities
     */
    public ArrayList<CalendarEvent> getEventsByAlertIDAndOwnerID(String alertID, String ownerID) {
        return repository.fetchEventsByAlertIDAndOwnerID(alertID, ownerID);
    }

    /**
     * Edit event's name
     * @param eventID ID of event
     * @param name new name
     * @param ownerID ID of owner of event
     * @return whether edit was successful
     */
    public boolean editEventName(String eventID, String name, String ownerID) {
        return repository.editEventName(eventID, name, ownerID);
    }

    /**
     * Edit event's time
     * @param eventID ID of event
     * @param start new start datetime
     * @param end new end datetime
     * @param ownerID ID of owner of event
     * @return whether edit was successful
     */
    public boolean editEventTime(String eventID, LocalDateTime start, LocalDateTime end, String ownerID) {
        boolean startSuccess = repository.editEventStart(eventID, start, ownerID);
        if (startSuccess) {
            repository.editEventEnd(eventID, end, ownerID);
            return true;
        }
        return false;
    }

    /**
     * Edit event's location
     * @param eventID ID of event
     * @param location new location
     * @param ownerID ID of owner of event
     * @return whether edit was successful
     */
    public boolean editEventLocation(String eventID, String location, String ownerID) {
        return repository.editEventLocation(eventID, location, ownerID);
    }

    /**
     * Edit event's series
     * @param eventID ID of event
     * @param seriesID ID of series or "" for no series
     * @param ownerID ID of owner of event
     * @return whether edit was successful
     */
    public boolean editSeriesID(String eventID, String seriesID, String ownerID) {
        return repository.editSeriesID(eventID, seriesID, ownerID);
    }

    /**
     * Edit event's memo
     * @param eventID ID of event
     * @param memoID ID of memo or "" for no memo
     * @param ownerID ID of owner of event
     * @return whether edit was successful
     */
    public boolean editMemoID(String eventID, String memoID, String ownerID) {
        return repository.editMemoID(eventID, memoID, ownerID);
    }

    /**
     * Edit event's tag
     * @param eventID ID of event
     * @param tagIDs list of ID of tags
     * @param ownerID ID of owner of event
     * @return whether edit was successful
     */
    public boolean editTagIDs(String eventID, ArrayList<String> tagIDs, String ownerID) {
        return repository.editTagIDs(eventID, tagIDs, ownerID);
    }

    /**
     * Edit event's calendar
     * @param eventID ID of event
     * @param calendarID ID of calendar or "" for no calendar
     * @param ownerID ID of owner of event
     * @return whether edit was successful
     */
    public boolean editCalendarID(String eventID, String calendarID, String ownerID) {
        return repository.editEventCalendarID(eventID, calendarID, ownerID);
    }

    /**
     * Edit event's alert
     * @param eventID ID of event
     * @param alertID ID of alert or "" for no alert
     * @param ownerID ID of owner of event
     * @return whether edit was successful
     */
    public boolean editAlertID(String eventID, String alertID, String ownerID) {
        return repository.editAlertID(eventID, alertID, ownerID);
    }

    /**
     * Add a collaborator to event
     * @param eventID ID of event
     * @param collaboratorID ID of collaborator to add
     * @return whether addition was successful
     */
    public boolean addCollaborator(String eventID, String collaboratorID) {
        return repository.addCollaborator(eventID, collaboratorID);
    }

    /**
     * Delete an event and alert observers
     * @param eventID ID of event to delete
     * @param ownerID ID of owner of event
     * @return whether event was deleted successfully
     */
    public boolean deleteEvent(String eventID, String ownerID) {
        CalendarEvent eventToDelete = repository.fetchEventByEventIDAndOwnerID(eventID, ownerID);
        if (eventToDelete == null) {
            return false;
        }
        repository.deleteEvent(eventID, ownerID);
        // Alert observers of event deletion
        for (IEventDeletionObserver observer: observers) {
            observer.handleEventDeletion(eventToDelete);
        }
        return true;
    }
}

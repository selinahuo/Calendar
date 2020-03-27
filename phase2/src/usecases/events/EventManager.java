package usecases.events;

import dataclasses.Tuple;
import entities.CalendarEvent;

import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventManager {
    private IEventRepository repository;
    private List<IEventDeletionObserver> observers = new ArrayList<>();

    public EventManager(IEventRepository repository) {
        this.repository = repository;
    }

    public void addObservers(IEventDeletionObserver deletionObserver) {
        observers.add(deletionObserver);
    }

    // save
    public String createEvent(String name, LocalDateTime start, LocalDateTime end, String location, String userID, String calendarID) {
        CalendarEvent event = new CalendarEvent(name, start, end, location, userID, calendarID);
        boolean success = repository.saveEvent(event);
        if (success) {
            return event.getEventID();
        } else {
            return null;
        }
    }

    public String createEventByFirstWeekDay(String name, int year, Month month, DayOfWeek weekDay, String location, String userID, String calendarID) {
        LocalDateTime day = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime start = day.with(firstInMonth(weekDay));
        LocalDateTime end = LocalDateTime.from(start);
        end.plusDays(1);
        return createEvent(name, start, end, location, userID, calendarID);
    }

    // get - singular
    public CalendarEvent getEventByIDAndUserID(String eventID, String userID) {
        return repository.fetchEventByEventIDAndUserID(eventID, userID);
    }
    public CalendarEvent getEventByAlertIDAndOwnerID(String alertID, String ownerID) {
        return repository.fetchEventByAlertIDAndOwnerID(alertID, ownerID);
    }

//    public String getEventDirections(String eventID) {
//        CalendarEvent event = repository.fetchEventByEventID(eventID);
//        if (event == null) {
//            return null;
//        }
//        try {
//            String url = "https://www.google.com/maps/dir//" + event.getLocation();
//            String encodedURL = URLEncoder.encode( url, "UTF-8" );
//            return encodedURL;
//        } catch(Exception e) {
//            return null;
//        }
//    }
//    public String getEventWeather(String eventID) {
//        CalendarEvent event = repository.fetchEventByEventID(eventID);
//        if (event == null) {
//            return null;
//        }
//        try {
//            String url = "https://www.google.com/search?q=" + event.getLocation() + " weather";
//            String encodedURL = URLEncoder.encode( url, "UTF-8" );
//            return encodedURL;
//        } catch(Exception e) {
//            return null;
//        }
//    }
//    public String getShareTwitterShareLink(String eventID) {
//        CalendarEvent event = repository.fetchEventByEventID(eventID);
//        if (event == null) {
//            return null;
//        }
//        try {
//            String eventString = String.format("%s @ %s from %s to %s.",
//                    event.getName(),
//                    event.getLocation(),
//                    event.getStart().toString(),
//                    event.getEnd().toString()
//            );
//            String url = "https://twitter.com/?status=" + eventString;
//            String encodedURL = URLEncoder.encode( url, "UTF-8" );
//            return encodedURL;
//        } catch(Exception e) {
//            return null;
//        }
//    }
//
//    public String getShareEMailShareLink(String eventID) {
//        CalendarEvent event = repository.fetchEventByEventID(eventID);
//        if (event == null) {
//            return null;
//        }
//        try {
//            String eventString = String.format("%s @ %s from %s to %s.",
//                    event.getName(),
//                    event.getLocation(),
//                    event.getStart().toString(),
//                    event.getEnd().toString()
//            );
//            String url = "mailto:?subject=" + eventString;
//            String encodedURL = URLEncoder.encode( url, "UTF-8" );
//            return encodedURL;
//        } catch(Exception e) {
//            return null;
//        }
//    }

    // get - plural
    public ArrayList<CalendarEvent> getEventsStartBeforeAndUserID(LocalDateTime start, String userID) {
        return repository.fetchEventsStartBeforeAndUserID(start, userID);
    }
    public ArrayList<CalendarEvent> getEventsStartBeforeAndEndAfterAndUserID(LocalDateTime before, LocalDateTime after, String userID) {
        return repository.fetchEventsStartBeforeAndEndAfterAndUserID(before, after, userID);
    }
    public ArrayList<CalendarEvent> getEventsStartAfterAndUserID(LocalDateTime after, String userID) {
        return repository.fetchEventsStartAfterAndUserID(after, userID);
    }
    public ArrayList<CalendarEvent> getEventsByOwnerID(String ownerID){
        return repository.fetchEventsByOwnerID(ownerID);
    }

    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByHourAndUserID(LocalDate date, int hour, String userID) {
        LocalDateTime hourStart = date.atTime(hour, 0);
        LocalDateTime hourEnd = LocalDateTime.from(hourStart).plusHours(1);
        ArrayList<CalendarEvent> hourEvents = repository.fetchEventsStartBeforeAndStartAfterAndUserID(hourEnd, hourStart, userID);

        ArrayList<LocalDateTime> times = new ArrayList<>();
        times.add(hourStart);
        ArrayList<ArrayList<CalendarEvent>> events = new ArrayList<>();
        events.add(hourEvents);
        return new Tuple<>(times, events);
    }

    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByDayAndUserID(LocalDate date, String userID) {
        LocalDateTime dayStart = date.atTime(0, 0);
        LocalDateTime dayEnd = LocalDateTime.from(dayStart).plusDays(1);
        ArrayList<CalendarEvent> dayEvents = repository.fetchEventsStartBeforeAndStartAfterAndUserID(dayStart, dayEnd, userID);

        ArrayList<LocalDateTime> times = new ArrayList<>();
        times.add(dayStart);
        ArrayList<ArrayList<CalendarEvent>> events = new ArrayList<>();
        events.add(dayEvents);
        return new Tuple<>(times, events);
    }

    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByWeekAndUserID(LocalDate date, String userID) {
        LocalDateTime dayStart = date.atTime(0, 0);
        LocalDateTime dayEnd = LocalDateTime.from(dayStart).plusDays(1);
        ArrayList<LocalDateTime> times = new ArrayList<>();
        ArrayList<ArrayList<CalendarEvent>> events = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            ArrayList<CalendarEvent> dayEvents = repository.fetchEventsStartBeforeAndStartAfterAndUserID(dayStart, dayEnd, userID);
            times.add(dayStart);
            events.add(dayEvents);
            dayStart = LocalDateTime.from(dayEnd);
            dayEnd = LocalDateTime.from(dayStart).plusDays(1);
        }
        return new Tuple<>(times, events);
    }

    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByMonthAndUserID(int year, Month month, String userID) {
        LocalDate day = LocalDate.of(year, month, 1);
        LocalDateTime dayStart = day.atStartOfDay();
        LocalDateTime dayEnd = LocalDateTime.from(dayStart).plusDays(1);
        int daysInMonth = day.lengthOfMonth();

        ArrayList<LocalDateTime> times = new ArrayList<>();
        ArrayList<ArrayList<CalendarEvent>> events = new ArrayList<>();

        for (int i = 1; i <= daysInMonth; i++) {
            ArrayList<CalendarEvent> dayEvents = repository.fetchEventsStartBeforeAndStartAfterAndUserID(dayStart, dayEnd, userID);
            times.add(dayStart);
            events.add(dayEvents);
            dayStart = LocalDateTime.from(dayEnd);
            dayEnd = LocalDateTime.from(dayStart).plusDays(1);
        }
        return new Tuple<>(times, events);
    }
    public ArrayList<CalendarEvent> getEventsByNameAndUserID(String name, String userID) {
        return repository.fetchEventsByNameAndUserID(name, userID);
    }
    public ArrayList<CalendarEvent> getEventsBySeriesIDAndOwnerID(String seriesID, String ownerID) {
        return repository.fetchEventsBySeriesIDAndOwnerID(seriesID, ownerID);
    }

    // edit
    public boolean editEventName(String eventID, String name, String ownerID) {
        return repository.editEventName(eventID, name, ownerID);
    }
    public boolean editEventTime(String eventID, LocalDateTime start, LocalDateTime end, String ownerID) {
        boolean startSuccess = repository.editEventStart(eventID, start, ownerID);
        boolean endSuccess = repository.editEventEnd(eventID, end, ownerID);
        return startSuccess && endSuccess;
    }
    public boolean editEventLocation(String eventID, String location, String ownerID) {
        return repository.editEventLocation(eventID, location, ownerID);
    }
    public boolean editSeriesID(String eventID, String seriesID, String ownerID) {
        return repository.editSeriesID(eventID, seriesID, ownerID);
    }

    // delete
    public boolean deleteEvent(String eventID, String ownerID) {
        boolean deleted = repository.deleteEvent(eventID, ownerID);
        if (deleted) {
            for (IEventDeletionObserver observer: observers) {
                observer.handleEventDeletion(eventID);
            }
        }
        return deleted;
    }
}

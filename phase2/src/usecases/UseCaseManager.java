package usecases;

import dataclasses.Quintuple;
import dataclasses.Sextuple;
import dataclasses.Tuple;
import entities.*;
import usecases.alerts.AlertManager;
import usecases.calendar.CalendarManager;
import usecases.events.EventManager;
import usecases.invitations.InvitationManager;
import usecases.notes.MemoManager;
import usecases.notes.TagManager;
import usecases.series.SeriesManager;
import usecases.time.TimeManager;
import usecases.users.UserManager;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class UseCaseManager {
    private AlertManager alertManager;
    private CalendarManager calendarManager;
    private EventManager eventManager;
    private InvitationManager invitationManager;
    private MemoManager memoManager;
    private TagManager tagManager;
    private SeriesManager seriesManager;
    private UserManager userManager;

    public UseCaseManager(AlertManager alertManager, CalendarManager calendarManager, EventManager eventManager, InvitationManager invitationManager, MemoManager memoManager, TagManager tagManager, SeriesManager seriesManager, UserManager userManager) {
        this.alertManager = alertManager;
        this.calendarManager = calendarManager;
        this.eventManager = eventManager;
        this.invitationManager = invitationManager;
        this.memoManager = memoManager;
        this.tagManager = tagManager;
        this.seriesManager = seriesManager;
        this.userManager = userManager;
    }

    // ALERTS

    // CALENDARS

    // EVENTS
    public String createEvent(String name, LocalDateTime start, LocalDateTime end, String location, String userID, String calendarID) {
        return eventManager.createEvent(name, start, end, location, userID, calendarID);
    }

    public String createEventByFirstWeekDay(String name, int year, Month month, DayOfWeek weekDay, String location, String userID, String calendarID) {
        return eventManager.createEventByFirstWeekDay(name, year, month, weekDay, location, userID, calendarID);
    }

    public Sextuple<CalendarEvent, ArrayList<Memo>, ArrayList<Tag>, Alert, Calendars, Series> getSingularEvent(String eventID, String userID) {
//        CalendarEvent event = this.eventManager.getEventByIDAndUserID(eventID, userID);
//        if (event != null) {
//            Alert alert = null;
//            ArrayList<Memo> memo = null;
//            ArrayList<Tag> tag = null;
//            Series series = null;
//            if (event.getAlertID() != null && event.getUserID().equals(userID)) {
////                alert = this.alertManager.getAlertByIDAndUserID(event.getAlertID(), userID);
//            }
//            if (event.getMemoIDs() != null && event.getMemoIDs().size() != 0) {
//                memo = this.noteManager.getMemoById(event.getMemoIDs().get(0));
//            }
//            if (event.getTagIDs() != null && event.getTagIDs().size() != 0) {
//                tag = this.noteManager.getTagByID(event.getTagIDs().get(0));
//            }
//            if (event.getSeriesID() != null && !event.getSeriesID().equals("")) {
//                series = this.seriesManager.getSeriesByIDAndUserID(event.getSeriesID(), userID);
//            }
//            return new Quintuple<CalendarEvent, Alert, Memo, Tag, Series>(event, alert, memo, tag, series);
//        }
        return null;
    }

    public String getEventDirections(String eventID) {
        return eventManager.getEventDirections(eventID);
    }

    public String getEventWeather(String eventID) {
        return eventManager.getEventWeather(eventID);
    }

    public String getEventTwitterShare(String eventID) {
        return eventManager.getEventTwitterShare(eventID);
    }

    public String getEventEmailShare(String eventID) {
        return eventManager.getEventEmailShare(eventID);
    }

    public ArrayList<CalendarEvent> getEventsByNameAndUserID(String name, String userID) {
        return eventManager.getEventsByNameAndUserID(name, userID);
    }

    public ArrayList<CalendarEvent> getPastEvents(String userID) {
        LocalDateTime now = TimeManager.getTime();
        return eventManager.getEventsStartBeforeAndUserID(now, userID);
    }
    public ArrayList<CalendarEvent> getCurrentEvents(String userID) {
        LocalDateTime now = TimeManager.getTime();
        return eventManager.getEventsStartBeforeAndEndAfterAndUserID(now, now, userID);
    }
    public ArrayList<CalendarEvent> getFutureEvents(String userID) {
        LocalDateTime now = TimeManager.getTime();
        return eventManager.getEventsStartAfterAndUserID(now, userID);
    }

    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByHour(LocalDate date, int hour, String userID) {
        return eventManager.getEventsByHourAndUserID(date, hour, userID);
    }

    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByDay(LocalDate date, String userID) {
        return eventManager.getEventsByDayAndUserID(date, userID);
    }

    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByWeek(LocalDate date, String userID) {
        return eventManager.getEventsByWeekAndUserID(date, userID);
    }
    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByMonth(int year, Month month, String userID) {
        return eventManager.getEventsByMonthAndUserID(year, month, userID);
    }

    public boolean editEventName(String eventID, String name, String ownerID) {
        return eventManager.editEventName(eventID, name, ownerID);
    }
    public boolean editEventTime(String eventID, LocalDateTime start, LocalDateTime end, String ownerID) {
        return eventManager.editEventTime(eventID, start, end, ownerID);
    }
    public boolean editEventLocation(String eventID, String location, String ownerID) {
        return eventManager.editEventLocation(eventID, location, ownerID);
    }

    public boolean deleteEvent(String eventID, String ownerID) {
        return eventManager.deleteEvent(eventID, ownerID);
    }

    // INVITATIONS

    // NOTES

    // SERIES

    // TIME
    public LocalDateTime getTime() {
        return TimeManager.getTime();
    }
    public void setTime(LocalDateTime time) {
        TimeManager.setTime(time);
    }
    public void resetTime() {
        TimeManager.reset();
    }

    // USERS
    public String loginUser(String username, String password) {
        return userManager.authenticateUser(username, password);
    }
    public boolean createUser(String username, String password) {
        return userManager.createUser(username, password);
    }
    public ArrayList<User> getUsers() {
        return userManager.getUsers();
    }
}

package usecases;

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
    public ArrayList<Alert> getAlertsByUserID(String userID) {
        return alertManager.getAlertByUserID(userID);
    }

    public ArrayList<Alert> getOverdueAlerts(String userID){
        LocalDateTime now = TimeManager.getTime();
        return alertManager.getOverdueAlertsAfterDate(now, userID);
    }

    public Alert createIndividualAlert(String eventID, String alertName, LocalDateTime start, String userID) {
        return alertManager.createIndividualAlert(eventID, alertName, start, userID);
    }
    public Alert createFrequencyAlert(String eventID, String alertName, String userID,
                                      LocalDateTime startTime, String frequency) {
        return alertManager.createFrequencyAlertOnEvent(eventID, alertName, userID, startTime, frequency);
    }

    public boolean acknowledgeAlert(String alertID, String userID) {
        return alertManager.acknowledgeAlert(alertID, userID);
    }

    public boolean editAlertName(String alertID, String name, String userID){
        return alertManager.editAlertName(alertID, name, userID);
    }
    public boolean editAlertTimeAsIndividual(String alertID, LocalDateTime time, String userID){
        return alertManager.editAlertTimeAsIndividual(alertID, time, userID);
    }

    public Alert getAlertByIDAndUserID(String alertID, String userID){
        return alertManager.getAlertByIDAndUserID(alertID, userID);
    }
    public boolean deleteAlertByID(String alertID, String userID){
        return alertManager.deleteAlertByIDAndUserID(alertID, userID);
    }


    // CALENDARS
    public String createCalendar(String userID, String calendarName) {
        return calendarManager.createCalendar(userID, calendarName);
    }

    public ArrayList<Calendars> getCalendarsByOwnerID(String ownerID) {
        return calendarManager.getCalendarsByOwnerID(ownerID);
    }

    public boolean editCalendarName(String calendarID, String newName, String ownerID) {
        return calendarManager.editCalendarName(calendarID, newName, ownerID);
    }

    public boolean addEventToCalendar(String eventID, String calendarID, String ownerID) {
        return calendarManager.addEventToCalendar(eventID, calendarID, ownerID);
    }

    public boolean removeEventFromCalendar(String eventID, String ownerID) {
        return calendarManager.removeEventFromCalendar(eventID, ownerID);
    }

    public boolean deleteCalendar(String calendarID, String ownerID) {
        return calendarManager.deleteCalendar(calendarID, ownerID);
    }

    public ArrayList<CalendarEvent> getEventsByCalendarIDAndOwnerID(String calendarID, String ownerID) {
        return eventManager.getEventsByAlertIDAndOwnerID(calendarID, ownerID);
    }

    // EVENTS
    public String createEvent(String name, LocalDateTime start, LocalDateTime end, String location, String userID) {
        return eventManager.createEvent(name, start, end, location, userID);
    }

    public String createEventByFirstWeekDay(String name, int year, Month month, DayOfWeek weekDay, String location, String userID) {
        return eventManager.createEventByFirstWeekDay(name, year, month, weekDay, location, userID);
    }

    public Sextuple<CalendarEvent, Memo, ArrayList<Tag>, Alert, Calendars, Series> getSingularEvent(String eventID, String userID) {
        CalendarEvent event = this.eventManager.getEventByIDAndUserID(eventID, userID);
        if (event == null) {
            return null;
        }

        Memo memo = memoManager.getMemoByMemoID(event.getMemoID());
        ArrayList<Tag> tags = new ArrayList<>();
        for (String tagID: event.getTagIDs()) {
            tags.add(tagManager.getTagByTagID(tagID));
        }

        Alert alert = null;
        Series series = null;
        Calendars calendar = null;
        if (event.getUserID().equals(userID)) {
            alert = alertManager.getAlertByIDAndUserID(event.getAlertID(), userID);
            System.out.println(event.getSeriesID());
            series = seriesManager.getSeriesBySeriesIDAndUserID(event.getSeriesID(), userID);
            calendar = calendarManager.getCalendarByIDAndOwnerID(event.getCalendarID(), userID);
        }
        return new Sextuple<CalendarEvent, Memo, ArrayList<Tag>, Alert, Calendars, Series>(event, memo, tags, alert, calendar, series);
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

    public ArrayList<CalendarEvent> getEventsByTagIDAndOwnerID(String tagID, String ownerID){
        return eventManager.getEventsByTagIDAndOwnerID(tagID, ownerID);
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

    public ArrayList<CalendarEvent> getEventsByAlertID(String alertID, String ownerID) {
        return eventManager.getEventsByAlertIDAndOwnerID(alertID, ownerID);
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
    public String createInvitation(String eventID, String inviterID, String inviteeID, String initialMessage) {
        return invitationManager.createInvitation(eventID, inviterID, inviteeID, initialMessage);
    }
    public ArrayList<Invitation> getIncomingInvitations(String inviteeID) {
        return invitationManager.getInvitationsByInviteeID(inviteeID);
    }
    public ArrayList<Invitation> getOutgoingInvitations(String inviterID) {
        return invitationManager.getInvitationsByInviterID(inviterID);
    }
    public Invitation getInvitation(String invitationID, String userID) {
        return invitationManager.getInvitationByIDAndUserID(invitationID, userID);
    }
    public boolean acceptInvitation(String invitationID, String respondingMessage, Boolean accept, String userID) {
        return invitationManager.acceptInvitation(invitationID, respondingMessage, accept, userID);
    }
    public boolean deleteInvitation(String invitationID, String userID) {
        return invitationManager.deleteInvitation(invitationID, userID);
    }

    // NOTES
    public ArrayList<CalendarEvent> getEventsByMemoIDAndOwnerID(String memoID, String ownerID){
        return memoManager.getEventsByMemoIDAndOwnerID(memoID, ownerID);
    }

    public Memo getMemoByMemoIDAndOwnerID(String memoID, String ownerID){
        return  memoManager.getMemoByMemoIDAndOwnerID(memoID, ownerID);
    }
    public String createMemo(String name, String note, String userID){
        return memoManager.createMemo(name, note, userID);
    }
    public String createTag(String name, String userID){
        return tagManager.createTag(name, userID);
    }

    public ArrayList<Memo> getMemosByOwnerID(String ownerID){
        return memoManager.getMemosByOwnerID(ownerID);
    }

    public boolean editMemoName(String memoID, String name, String ownerID){
        return memoManager.editMemoName(memoID, name, ownerID);
    }
    public boolean editMemoNote(String memoID, String note, String ownerID){
        return memoManager.editMemoNote(memoID, note, ownerID);
    }

    public boolean addMemoToEvent(String memoID, String eventID, String ownerID){
        return memoManager.addMemoToEvent(memoID, eventID, ownerID);
    }
    public ArrayList<Tag> getTagsByOwnerID(String ownerID){
        return tagManager.getTagsByOwnerID(ownerID);
    }
    public boolean removeMemoFromEvent(String eventID, String ownerID){
        return memoManager.removeMemoFromEvent(eventID, ownerID);
    }
    public boolean addTagToEvent(String tagID, String eventID, String ownerID){
        return tagManager.addTagToEvent(tagID, eventID, ownerID);
    }


    // SERIES
    public boolean createSeriesByCombiningEvents(String seriesName, String[] eventIDs, String userID) {
        return seriesManager.createSeriesByCombiningEvents(seriesName, eventIDs, userID);
    }

    public boolean createSeriesFromEventFormula(String seriesName, LocalDateTime start, LocalDateTime end, String frequency, int numEvents, String userID) {
        return seriesManager.createSeriesFromEventFormula(seriesName, start, end, frequency, numEvents, userID);
    }

    public ArrayList<Series> getSeriesByUserID(String userID) {
        return seriesManager.getSeriesByUserID(userID);
    }

    public ArrayList<Series> getSeriesBySeriesName(String seriesName, String userID) {
        return seriesManager.getSeriesBySeriesName(seriesName, userID);
    }

    public boolean editSeriesName(String seriesID, String seriesName, String userID){
        return seriesManager.editSeriesName(seriesID, seriesName, userID);
    }

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

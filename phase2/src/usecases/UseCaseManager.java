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

/**
 * A facade for the other managers.
*/
public class UseCaseManager {
    private AlertManager alertManager;
    private CalendarManager calendarManager;
    private EventManager eventManager;
    private InvitationManager invitationManager;
    private MemoManager memoManager;
    private TagManager tagManager;
    private SeriesManager seriesManager;
    private UserManager userManager;

    /**
     * Construct an UseCaseManager
     * @param alertManager injected alertManager dependency
     * @param calendarManager injected calendarManager dependency
     * @param eventManager injected eventManager dependency
     * @param invitationManager injected invitationManager dependency
     * @param memoManager injected memoManager dependency
     * @param tagManager injected tagManager dependency
     * @param seriesManager injected seriesManager dependency
     * @param userManager injected userManager dependency
     */
    public UseCaseManager(AlertManager alertManager, CalendarManager calendarManager, EventManager eventManager,
                          InvitationManager invitationManager, MemoManager memoManager, TagManager tagManager,
                          SeriesManager seriesManager, UserManager userManager) {
        this.alertManager = alertManager;
        this.calendarManager = calendarManager;
        this.eventManager = eventManager;
        this.invitationManager = invitationManager;
        this.memoManager = memoManager;
        this.tagManager = tagManager;
        this.seriesManager = seriesManager;
        this.userManager = userManager;
    }

    /**
     * @see AlertManager#getAlertByUserID(String) 
     */
    public ArrayList<Alert> getAlertsByUserID(String userID) {
        return alertManager.getAlertByUserID(userID);
    }

    /**
     * Get overdue alerts using application's time.
     * @see TimeManager#getTime()
     * @see AlertManager#getOverdueAlertsAfterDate(LocalDateTime, String) 
     */
    public ArrayList<Alert> getOverdueAlerts(String userID){
        LocalDateTime now = TimeManager.getTime();
        return alertManager.getOverdueAlertsAfterDate(now, userID);
    }

    /**
     * @see AlertManager#createIndividualAlert(String, String, LocalDateTime, String) 
     */
    public Alert createIndividualAlert(String eventID, String alertName, LocalDateTime start, String userID) {
        return alertManager.createIndividualAlert(eventID, alertName, start, userID);
    }

    /**
     * @see AlertManager#createFrequencyAlertOnEvent(String, String, String, LocalDateTime, String) 
     */
    public Alert createFrequencyAlert(String eventID, String alertName, String userID,
                                      LocalDateTime startTime, String frequency) {
        return alertManager.createFrequencyAlertOnEvent(eventID, alertName, userID, startTime, frequency);
    }

    /**
     * @see AlertManager#acknowledgeAlert(String, String)
     */
    public boolean acknowledgeAlert(String alertID, String userID) {
        return alertManager.acknowledgeAlert(alertID, userID);
    }

    /**
     * @see AlertManager#editAlertName(String, String, String)
     */
    public boolean editAlertName(String alertID, String name, String userID){
        return alertManager.editAlertName(alertID, name, userID);
    }

    /**
     * @see AlertManager#editAlertTimeAsIndividual(String, LocalDateTime, String)
     */
    public boolean editAlertTimeAsIndividual(String alertID, LocalDateTime time, String userID){
        return alertManager.editAlertTimeAsIndividual(alertID, time, userID);
    }

    /**
     * @see AlertManager#getAlertByIDAndUserID(String, String)
     */
    public Alert getAlertByIDAndUserID(String alertID, String userID){
        return alertManager.getAlertByIDAndUserID(alertID, userID);
    }

    /**
     * @see AlertManager#deleteAlertByIDAndUserID(String, String) 
     */
    public boolean deleteAlertByID(String alertID, String userID){
        return alertManager.deleteAlertByIDAndUserID(alertID, userID);
    }


    /**
     * @see CalendarManager#createCalendar(String, String)
     */
    public String createCalendar(String userID, String calendarName) {
        return calendarManager.createCalendar(userID, calendarName);
    }

    /**
     * @see CalendarManager#getCalendarsByOwnerID(String)
     */
    public ArrayList<Calendars> getCalendarsByOwnerID(String ownerID) {
        return calendarManager.getCalendarsByOwnerID(ownerID);
    }

    /**
     * @see CalendarManager#editCalendarName(String, String, String)
     */
    public boolean editCalendarName(String calendarID, String newName, String ownerID) {
        return calendarManager.editCalendarName(calendarID, newName, ownerID);
    }

    /**
     * @see CalendarManager#addEventToCalendar(String, String, String)
     */
    public boolean addEventToCalendar(String eventID, String calendarID, String ownerID) {
        return calendarManager.addEventToCalendar(eventID, calendarID, ownerID);
    }

    /**
     * @see CalendarManager#removeEventFromCalendar(String, String)
     */
    public boolean removeEventFromCalendar(String eventID, String ownerID) {
        return calendarManager.removeEventFromCalendar(eventID, ownerID);
    }

    /**
     * @see CalendarManager#deleteCalendar(String, String)
     */
    public boolean deleteCalendar(String calendarID, String ownerID) {
        return calendarManager.deleteCalendar(calendarID, ownerID);
    }

    /**
     * @see EventManager#getEventsByCalendarIDAndOwnerID(String, String)
     */
    public ArrayList<CalendarEvent> getEventsByCalendarIDAndOwnerID(String calendarID, String ownerID) {
        return eventManager.getEventsByAlertIDAndOwnerID(calendarID, ownerID);
    }

    /**
     * @see EventManager#createEvent(String, LocalDateTime, LocalDateTime, String, String)
     */
    public String createEvent(String name, LocalDateTime start, LocalDateTime end, String location, String userID) {
        return eventManager.createEvent(name, start, end, location, userID);
    }

    /**
     * @see EventManager#createEventByFirstWeekDay(String, int, Month, DayOfWeek, String, String)
     */
    public String createEventByFirstWeekDay(String name, int year, Month month, DayOfWeek weekDay, String location,
                                            String userID) {
        return eventManager.createEventByFirstWeekDay(name, year, month, weekDay, location, userID);
    }

    /**
     * Get a singular event and associated entities
     * @param eventID ID of event
     * @param userID ID of user associated with event
     * @return Sextuple of event, memo, list of tag, alert, calendar and series entities
     */
    public Sextuple<CalendarEvent, Memo, ArrayList<Tag>, Alert, Calendars, Series> getSingularEvent(String eventID,
                                                                                                    String userID) {
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
        // Only set return specific entities if user owns event
        if (event.getUserID().equals(userID)) {
            alert = alertManager.getAlertByIDAndUserID(event.getAlertID(), userID);
            series = seriesManager.getSeriesBySeriesIDAndUserID(event.getSeriesID(), userID);
            calendar = calendarManager.getCalendarByIDAndOwnerID(event.getCalendarID(), userID);
        }
        return new Sextuple<>(event, memo, tags, alert, calendar, series);
    }

    /**
     * @see EventManager#getEventDirections(String)
     */
    public String getEventDirections(String eventID) {
        return eventManager.getEventDirections(eventID);
    }

    /**
     * @see EventManager#getEventWeather(String)
     */
    public String getEventWeather(String eventID) {
        return eventManager.getEventWeather(eventID);
    }

    /**
     * @see EventManager#getEventTwitterShare(String)
     */
    public String getEventTwitterShare(String eventID) {
        return eventManager.getEventTwitterShare(eventID);
    }

    /**
     * @see EventManager#getEventEmailShare(String)
     */
    public String getEventEmailShare(String eventID) {
        return eventManager.getEventEmailShare(eventID);
    }

    /**
     * @see EventManager#getEventsByNameAndUserID(String, String)
     */
    public ArrayList<CalendarEvent> getEventsByNameAndUserID(String name, String userID) {
        return eventManager.getEventsByNameAndUserID(name, userID);
    }

    /**
     * @see EventManager#getEventsByTagIDAndOwnerID(String, String)
     */
    public ArrayList<CalendarEvent> getEventsByTagIDAndOwnerID(String tagID, String ownerID){
        return eventManager.getEventsByTagIDAndOwnerID(tagID, ownerID);
    }

    /**
     * @see EventManager#getEventsBySeriesIDAndOwnerID(String, String)
     */
    public ArrayList<CalendarEvent> getEventsBySeriesIDAndOwnerID(String seriesID, String ownerID) {
        return eventManager.getEventsBySeriesIDAndOwnerID(seriesID, ownerID);
    }

    /**
     * Get events that start before current application time
     * @see TimeManager#getTime()
     * @see EventManager#getEventsStartBeforeAndUserID(LocalDateTime, String)
     */
    public ArrayList<CalendarEvent> getPastEvents(String userID) {
        LocalDateTime now = TimeManager.getTime();
        return eventManager.getEventsStartBeforeAndUserID(now, userID);
    }

    /**
     * Get events that start before and end after current application time
     * @see TimeManager#getTime()
     * @see EventManager#getEventsStartBeforeAndEndAfterAndUserID(LocalDateTime, LocalDateTime, String)
     */
    public ArrayList<CalendarEvent> getCurrentEvents(String userID) {
        LocalDateTime now = TimeManager.getTime();
        return eventManager.getEventsStartBeforeAndEndAfterAndUserID(now, now, userID);
    }

    /**
     * Get events that start after current application time
     * @see TimeManager#getTime()
     * @see EventManager#getEventsStartAfterAndUserID(LocalDateTime, String) 
     */
    public ArrayList<CalendarEvent> getFutureEvents(String userID) {
        LocalDateTime now = TimeManager.getTime();
        return eventManager.getEventsStartAfterAndUserID(now, userID);
    }

    /**
     * @see EventManager#getEventsByHourAndUserID(LocalDate, int, String)
     */
    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByHour(LocalDate date,
                                                                                                int hour,
                                                                                                String userID) {
        return eventManager.getEventsByHourAndUserID(date, hour, userID);
    }

    /**
     * @see EventManager#getEventsByDayAndUserID(LocalDate, String)
     */
    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByDay(LocalDate date,
                                                                                               String userID) {
        return eventManager.getEventsByDayAndUserID(date, userID);
    }

    /**
     * @see EventManager#getEventsByWeekAndUserID(LocalDate, String)
     */
    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByWeek(LocalDate date,
                                                                                                String userID) {
        return eventManager.getEventsByWeekAndUserID(date, userID);
    }
    
    /**
     * @see EventManager#getEventsByMonthAndUserID(int, Month, String) 
     */
    public Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> getEventsByMonth(int year, Month month,
                                                                                                 String userID) {
        return eventManager.getEventsByMonthAndUserID(year, month, userID);
    }

    /**
     * @see EventManager#getEventsByAlertIDAndOwnerID(String, String)
     */
    public ArrayList<CalendarEvent> getEventsByAlertID(String alertID, String ownerID) {
        return eventManager.getEventsByAlertIDAndOwnerID(alertID, ownerID);
    }


    /**
     * @see EventManager#editEventName(String, String, String)
     */
    public boolean editEventName(String eventID, String name, String ownerID) {
        return eventManager.editEventName(eventID, name, ownerID);
    }

    /**
     * @see EventManager#editEventTime(String, LocalDateTime, LocalDateTime, String)
     */
    public boolean editEventTime(String eventID, LocalDateTime start, LocalDateTime end, String ownerID) {
        return eventManager.editEventTime(eventID, start, end, ownerID);
    }

    /**
     * @see EventManager#editEventLocation(String, String, String)
     */
    public boolean editEventLocation(String eventID, String location, String ownerID) {
        return eventManager.editEventLocation(eventID, location, ownerID);
    }

    /**
     * @see EventManager#deleteEvent(String, String)
     */
    public boolean deleteEvent(String eventID, String ownerID) {
        return eventManager.deleteEvent(eventID, ownerID);
    }

    /**
     * @see InvitationManager#createInvitation(String, String, String, String) 
     */
    public String createInvitation(String eventID, String inviterID, String inviteeID, String initialMessage) {
        return invitationManager.createInvitation(eventID, inviterID, inviteeID, initialMessage);
    }

    /**
     * @see InvitationManager#getInvitationsByInviteeID(String)
     */
    public ArrayList<Invitation> getIncomingInvitations(String inviteeID) {
        return invitationManager.getInvitationsByInviteeID(inviteeID);
    }

    /**
     * @see InvitationManager#getInvitationsByInviterID(String)
     */
    public ArrayList<Invitation> getOutgoingInvitations(String inviterID) {
        return invitationManager.getInvitationsByInviterID(inviterID);
    }

    /**
     * @see InvitationManager#getInvitationByIDAndUserID(String, String)
     */
    public Invitation getInvitation(String invitationID, String userID) {
        return invitationManager.getInvitationByIDAndUserID(invitationID, userID);
    }

    /**
     * @see InvitationManager#acceptInvitation(String, String, Boolean, String)
     */
    public boolean acceptInvitation(String invitationID, String respondingMessage, Boolean accept, String userID) {
        return invitationManager.acceptInvitation(invitationID, respondingMessage, accept, userID);
    }

    /**
     * @see InvitationManager#deleteInvitation(String, String)
     */
    public boolean deleteInvitation(String invitationID, String userID) {
        return invitationManager.deleteInvitation(invitationID, userID);
    }

    /**
     * @see MemoManager#getEventsByMemoIDAndOwnerID(String, String)
     */
    public ArrayList<CalendarEvent> getEventsByMemoIDAndOwnerID(String memoID, String ownerID){
        return memoManager.getEventsByMemoIDAndOwnerID(memoID, ownerID);
    }

    /**
     * @see MemoManager#getMemoByMemoIDAndOwnerID(String, String)
     */
    public Memo getMemoByMemoIDAndOwnerID(String memoID, String ownerID){
        return  memoManager.getMemoByMemoIDAndOwnerID(memoID, ownerID);
    }

    /**
     * @see MemoManager#deleteMemo(String, String)
     */
    public boolean deleteMemo(String memoID, String ownerID){
        return memoManager.deleteMemo(memoID, ownerID);
    }
    public boolean deleteTag(String tagID, String ownerID) {
        return tagManager.deleteTag(tagID, ownerID);
    }

    /**
     * @see MemoManager#createMemo(String, String, String)
     */
    public String createMemo(String name, String note, String userID){
        return memoManager.createMemo(name, note, userID);
    }

    /**
     * @see TagManager#createTag(String, String)
     */
    public String createTag(String name, String userID){
        return tagManager.createTag(name, userID);
    }

    /**
     * @see MemoManager#getMemosByOwnerID(String)
     */
    public ArrayList<Memo> getMemosByOwnerID(String ownerID){
        return memoManager.getMemosByOwnerID(ownerID);
    }

    /**
     * @see MemoManager#editMemoName(String, String, String) 
     */
    public boolean editMemoName(String memoID, String name, String ownerID){
        return memoManager.editMemoName(memoID, name, ownerID);
    }

    /**
     * @see MemoManager#editMemoNote(String, String, String) 
     */
    public boolean editMemoNote(String memoID, String note, String ownerID){
        return memoManager.editMemoNote(memoID, note, ownerID);
    }

    /**
     * @see MemoManager#addMemoToEvent(String, String, String)
     */
    public boolean addMemoToEvent(String memoID, String eventID, String ownerID){
        return memoManager.addMemoToEvent(memoID, eventID, ownerID);
    }

    /**
     * @see TagManager#getTagsByOwnerID(String)
     */
    public ArrayList<Tag> getTagsByOwnerID(String ownerID){
        return tagManager.getTagsByOwnerID(ownerID);
    }

    /**
     * @see MemoManager#removeMemoFromEvent(String, String)
     */
    public boolean removeMemoFromEvent(String eventID, String ownerID){
        return memoManager.removeMemoFromEvent(eventID, ownerID);
    }

    /**
     * @see TagManager#addTagToEvent(String, String, String)
     */
    public boolean addTagToEvent(String tagID, String eventID, String ownerID){
        return tagManager.addTagToEvent(tagID, eventID, ownerID);
    }

    /**
     * @see TagManager#removeTagFromEvent(String, String, String)
     */
    public boolean removeTagFromEvent(String tagID, String eventID, String ownerID) {
        return tagManager.removeTagFromEvent(tagID, eventID, ownerID);
    }

    /**
     * @see SeriesManager#createSeriesByCombiningEvents(String, ArrayList, String)
     */
    public boolean createSeriesByCombiningEvents(String seriesName, ArrayList<String> eventIDs, String userID) {
        return seriesManager.createSeriesByCombiningEvents(seriesName, eventIDs, userID);
    }

    /**
     * @see SeriesManager#createSeriesFromEventFormula(String, LocalDateTime, LocalDateTime, String, int, String)
     */
    public boolean createSeriesFromEventFormula(String seriesName, LocalDateTime start, LocalDateTime end,
                                                String frequency, int numEvents, String userID) {
        return seriesManager.createSeriesFromEventFormula(seriesName, start, end, frequency, numEvents, userID);
    }

    /**
     * @see SeriesManager#getSeriesByUserID(String)
     */
    public ArrayList<Series> getSeriesByUserID(String userID) {
        return seriesManager.getSeriesByUserID(userID);
    }

    /**
     * @see SeriesManager#getSeriesBySeriesName(String, String)
     */
    public ArrayList<Series> getSeriesBySeriesName(String seriesName, String userID) {
        return seriesManager.getSeriesBySeriesName(seriesName, userID);
    }

    /**
     * @see SeriesManager#editSeriesName(String, String, String)
     */
    public boolean editSeriesName(String seriesID, String seriesName, String userID){
        return seriesManager.editSeriesName(seriesID, seriesName, userID);
    }

    /**
     * @see TimeManager#getTime()
     */
    public LocalDateTime getTime() {
        return TimeManager.getTime();
    }

    /**
     * @see TimeManager#setTime(LocalDateTime)
     */
    public void setTime(LocalDateTime time) {
        TimeManager.setTime(time);
    }

    /**
     * @see TimeManager#reset()
     */
    public void resetTime() {
        TimeManager.reset();
    }
    
    /**
     * @see UserManager#authenticateUser(String, String)
     */
    public String loginUser(String username, String password) {
        return userManager.authenticateUser(username, password);
    }

    /**
     * @see UserManager#createUser(String, String)
     */
    public boolean createUser(String username, String password) {
        return userManager.createUser(username, password);
    }

    /**
     * @see UserManager#getUsers()
     */
    public ArrayList<User> getUsers() {
        return userManager.getUsers();
    }
}

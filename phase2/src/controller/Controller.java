package controller;

import controller.viewmodels.ListModel;
import controller.viewmodels.SingularInvitationModel;
import controller.viewmodels.SingularModel;
import dataclasses.Sextuple;
import dataclasses.Tuple;
import entities.*;
import usecases.UseCaseManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * Class responsible for "adapting" user input and output and forwarding user requests to use case layer.
 * Adapts data for presentation and to form most convenient for use cases or user interfaces.
 */
public class Controller {
    private UseCaseManager useCaseManager;

    /**
     * Construct a Controller
     *
     * @param useCaseManager injected UseCaseManager instance
     */
    public Controller(UseCaseManager useCaseManager) {
        this.useCaseManager = useCaseManager;
    }

    // ALERTS
    public ListModel getAlertsByUserID(String userID) {
        return AlertAdapter.createAlertListModel(useCaseManager.getAlertsByUserID(userID));
    }
    public ListModel getOverdueAlerts(String userID) {
        return AlertAdapter.createAlertListModel(useCaseManager.getOverdueAlerts(userID));
    }

    public String createIndividualAlert(String eventID, String alertName, String time, String userID ){
        return AlertAdapter.createAlertString(useCaseManager.createIndividualAlert(eventID, alertName,
                GlobalAdapter.stringToDateTime(time), userID));
    }

    public String createFrequencyAlert(String eventID, String alertName, String userID,
                                       String startTime, String frequency) {
        return AlertAdapter.createAlertString(useCaseManager.createFrequencyAlert(eventID, alertName,
                userID, GlobalAdapter.stringToDateTime(startTime), frequency));
    }

    public boolean acknowledgeAlert(String alertID, String userID) {
        return useCaseManager.acknowledgeAlert(alertID, userID);
    }

    public boolean editAlertName(String alertID, String name, String userID){
        return useCaseManager.editAlertName(alertID, name, userID);
    }

    public String getAlertByIDAndUserID(String alertID, String userID){
        return AlertAdapter.createAlertString(useCaseManager.getAlertByIDAndUserID(alertID, userID));
    }

    public boolean editAlertTimeAsIndividual(String alertID, String time, String userID){
        return useCaseManager.editAlertTimeAsIndividual(alertID, GlobalAdapter.stringToDateTime(time), userID);
    }

    public boolean deleteAlertByID(String alertID, String userID){
        return useCaseManager.deleteAlertByID(alertID, userID);
    }

    // CALENDARS

    /**
     * Get list model of calendars with the user
     *
     * @param ownerID user to filter calendars by
     * @return ListModel of calendars
     */
    public ListModel getCalendarsByUserID(String ownerID){
        return CalendarAdapter.createCalendarListModel(useCaseManager.getCalendarsByOwnerID(ownerID));
    }

    /**
     * Create an Individual Calendar.
     *
     * @param userID the user that that this calendar belongs to
     * @param calendarName the name of this calendar
     * @return id of the calendar created
     */
    public String createCalendar(String userID, String calendarName){
        return useCaseManager.createCalendar(userID, calendarName);
    }

    /**
     * Edit the calendar's name.
     *
     * @param calendarID the calendarID of the calendar being modified
     * @param newName the new name of this calendar
     * @param ownerID the userID of the user that is associated with this calendar
     * @return true if modification is successful
     */
    public boolean editCalendarName(String calendarID, String newName, String ownerID) {
        return useCaseManager.editCalendarName(calendarID, newName, ownerID);
    }

    /**
     * Add event to the calendar
     *
     * @param eventID the ID of the event being added to the calendar
     * @param calendarID the ID of the calendar where the event is being added
     * @param ownerID the userID of the user that is associated with this calendar
     * @return true if modification is successful
     */
    public boolean addEventToCalendar(String eventID, String calendarID, String ownerID) {
        return useCaseManager.addEventToCalendar(eventID, calendarID, ownerID);
    }

    /**
     * Remove event from the calendar
     *
     * @param eventID the ID of the event being removed from the calendar
     * @param ownerID the userID of the user that is associated with this calendar
     * @return true if modification is successful
     */
    public boolean removeEventFromCalendar(String eventID, String ownerID) {
        return useCaseManager.removeEventFromCalendar(eventID, ownerID);
    }

    /**
     * Delete the Calendar
     *
     * @param calendarID the ID of the calendar being deleted
     * @param ownerID the userID of the user who has the calendar
     * @return true is modification is successful
     */
    public boolean deleteCalendar(String calendarID, String ownerID) {
        return useCaseManager.deleteCalendar(calendarID, ownerID);
    }

    /**
     * Get list model of events with in the calendar
     *
     * @param calendarID the calendar that the events are in
     * @param ownerID the user where the events belong to
     * @return ListModel of events
     */
    public ListModel getEventsByCalendarIDAndOwnerID(String calendarID, String ownerID) {
        return EventAdapter.createEventListModel(useCaseManager.getEventsByCalendarIDAndOwnerID(calendarID, ownerID));
    }

    // EVENTS

    /**
     * Create an event
     * @param name event name
     * @param start start date string
     * @param end end date string
     * @param location event location
     * @param userID ID of user creating event
     * @return eventID if successful, or null otherwise
     */
    public String createEvent(String name, String start, String end, String location, String userID) {
        return useCaseManager.createEvent(name, GlobalAdapter.stringToDateTime(start),
                GlobalAdapter.stringToDateTime(end), location, userID);
    }

    /**
     * Create a holiday using the first weekday of a specific month
     *
     * @param name event name
     * @param year year of the event
     * @param month month of the event
     * @param weekDay which first weekday of month
     * @param location event location
     * @param userID ID of user creating event
     * @return event ID if successful; null otherwise
     */
    public String createHolidayEvent(String name, int year, int month, String weekDay, String location, String userID) {
        return useCaseManager.createEventByFirstWeekDay(name, year, GlobalAdapter.intToMonth(month),
                GlobalAdapter.stringToWeekDay(weekDay), location, userID);
    }

    /**
     * Get a singular (view) model for an event
     *
     * @param eventID ID of event
     * @param userID ID of associated used
     * @return singular event model
     */
    public SingularModel getSingularEvent(String eventID, String userID) {
        Sextuple<CalendarEvent, Memo, ArrayList<Tag>, Alert, Calendars, Series> meta =
                useCaseManager.getSingularEvent(eventID, userID);
        if (meta == null) {
            return null;
        }
        return EventAdapter.createEventSingularModel(meta.getFirst(), meta.getSecond(), meta.getThird(),
                meta.getFourth(), meta.getFifth(), meta.getSixth());
    }

    /**
     * Get event directions URL
     *
     * @param eventID ID of event
     * @return URL of event directions
     */
    public String getEventDirections(String eventID) {
        return useCaseManager.getEventDirections(eventID);
    }

    /**
     * Get event weather URL
     *
     * @param eventID ID of event
     * @return URL for event weather
     */
    public String getEventWeather(String eventID) {
        return useCaseManager.getEventWeather(eventID);
    }

    /**
     * Get event Twitter share URL
     *
     * @param eventID ID of event
     * @return URL to share event on Twitter
     */
    public String getEventTwitterShare(String eventID) {
        return useCaseManager.getEventTwitterShare(eventID);
    }

    /**
     * Get event email share URL
     *
     * @param eventID ID of event
     * @return event email share URL
     */
    public String getEventEmailShare(String eventID) {
        return useCaseManager.getEventEmailShare(eventID);
    }

    /**
     * Edit an event name
     *
     * @param eventID ID of event
     * @param name new name
     * @param ownerID ID of event owner
     * @return whether edit was successful
     */
    public boolean editEventName(String eventID, String name, String ownerID) {
        return useCaseManager.editEventName(eventID, name, ownerID);
    }

    /**
     * Edit an event location
     *
     * @param eventID ID of event
     * @param location new location
     * @param ownerID ID of owner of event
     * @return whether edit was successful
     */
    public boolean editEventLocation(String eventID, String location, String ownerID) {
        return useCaseManager.editEventLocation(eventID, location, ownerID);
    }

    /**
     * Edit an event's times
     *
     * @param eventID ID of event
     * @param start new start time string
     * @param end new end time string
     * @param ownerID ID of owner of event
     * @return whether edit was successful
     */
    public boolean editEventTime(String eventID, String start, String end, String ownerID) {
        LocalDateTime startTime = GlobalAdapter.stringToDateTime(start);
        LocalDateTime endTime = GlobalAdapter.stringToDateTime(end);
        return useCaseManager.editEventTime(eventID, startTime, endTime, ownerID);
    }

    /**
     * Delete an event
     *
     * @param eventID ID of event to delete
     * @param ownerID ID of owner of event
     * @return whether deletion was successful
     */
    public boolean deleteEvent(String eventID, String ownerID) {
        return useCaseManager.deleteEvent(eventID, ownerID);
    }

    /**
     * Get list model of events with a name
     *
     * @param name name to filter events by
     * @param userID ID of user associated with the events
     * @return ListModel of events
     */
    public ListModel getEventsByName(String name, String userID) {
        return EventAdapter.createEventListModel(useCaseManager.getEventsByNameAndUserID(name, userID));
    }

    /**
     * Get list model of events in the past
     *
     * @param userID ID of user associated with events
     * @return list model of events
     */
    public ListModel getPastEvents(String userID) {
        return EventAdapter.createEventListModel(useCaseManager.getPastEvents(userID));
    }

    /**
     * Get list model of events in the present
     *
     * @param userID ID of user associated with events
     * @return list model of events
     */
    public ListModel getCurrentEvents(String userID) {
        return EventAdapter.createEventListModel(useCaseManager.getCurrentEvents(userID));
    }

    /**
     * Get list model of events in the future
     *
     * @param userID ID of user associated with events
     * @return list model of events
     */
    public ListModel getFutureEvents(String userID) {
        return EventAdapter.createEventListModel(useCaseManager.getFutureEvents(userID));
    }

    /**
     * Get list model of time strings and events by an hour time frame
     *
     * @param date date of the time frame
     * @param hour hour of the time frame
     * @param userID ID of user associated with the events
     * @return list model of times and events
     */
    public ListModel getHourlyEvents(String date, int hour, String userID) {
        LocalDate dateObject = GlobalAdapter.stringToDate(date);
        Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> eventTimes = useCaseManager.getEventsByHour(dateObject, hour, userID);
        return EventAdapter.createEventDateListModel(eventTimes.getSecond(), eventTimes.getFirst());
    }

    /**
     * Get list model of time strings and events by a day time frame
     *
     * @param date day of time frame
     * @param userID ID of user associated with the events
     * @return list model of times and events
     */
    public ListModel getDailyEvents(String date, String userID) {
        LocalDate dateObject = GlobalAdapter.stringToDate(date);
        Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> eventTimes = useCaseManager.getEventsByDay(dateObject, userID);
        return EventAdapter.createEventDateListModel(eventTimes.getSecond(), eventTimes.getFirst());
    }

    /**
     * Get list model of time strings and events by a weekly time frame
     *
     * @param date first day of the week time frame
     * @param userID ID of user associated with the events
     * @return list model of times and events
     */
    public ListModel getWeeklyEvents(String date, String userID) {
        LocalDate dateObject = GlobalAdapter.stringToDate(date);
        Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> eventTimes = useCaseManager.getEventsByWeek(dateObject, userID);
        return EventAdapter.createEventDateListModel(eventTimes.getSecond(), eventTimes.getFirst());
    }

    /**
     * Get list model of time strings and events by a monthly time frame
     *
     * @param year year of the time frame
     * @param month month of the time frame
     * @param userID ID of user associated with the events
     * @return list model of times and events
     */
    public ListModel getMonthlyEvents(int year, int month, String userID) {
        Month monthObject = GlobalAdapter.intToMonth(month);
        Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> eventTimes = useCaseManager.getEventsByMonth(year, monthObject, userID);
        return EventAdapter.createEventDateListModel(eventTimes.getSecond(), eventTimes.getFirst());
    }

    /**
     * Get list model of events associated with an alert
     *
     * @param alertID ID of alert events are associated with
     * @param ownerID ID of the owner of the events
     * @return list model of events
     */
    public ListModel getEventsFromAlert(String alertID, String ownerID) {
        return EventAdapter.createEventListModel(useCaseManager.getEventsByAlertID(alertID, ownerID));
    }

    /**
     * Get list model of events associated with a series
     *
     * @param seriesID ID of series associated with event
     * @param ownerID ID of owner of the events
     * @return list model of events
     */
    public ListModel getEventsBySeriesIDAndOwnerID(String seriesID, String ownerID) {
        return EventAdapter.createEventListModel(useCaseManager.getEventsBySeriesIDAndOwnerID(seriesID, ownerID));
    }

    /**
     * Get list model of events associated with a tag
     *
     * @param tagID ID of associated tag
     * @param ownerID ID of owner of the events
     * @return ListModel of events
     */
    public ListModel getEventsByTagIDAndOwnerID(String tagID, String ownerID){
        return EventAdapter.createEventListModel(useCaseManager.getEventsByTagIDAndOwnerID(tagID, ownerID));
    }

    // INVITATIONS

    /**
     * Get list model of incoming invitations
     *
     * @param userID ID of user receiving invitations
     * @return list model of invitations
     */
    public ListModel getIncomingInvitations(String userID) {
        return InvitationAdapter.createInvitationListModel(useCaseManager.getIncomingInvitations(userID));
    }

    /**
     * Get list model of outgoing invitations
     *
     * @param userID ID of user sending these invitations
     * @return list model of invitations
     */
    public ListModel getOutgoingInvitations(String userID) {
        return InvitationAdapter.createInvitationListModel(useCaseManager.getOutgoingInvitations(userID));
    }

    /**
     * Get a singular model for invitation
     *
     * @param invitationID ID of invitation
     * @param userID ID of user associated with invitation
     * @return singular view model for invitation
     */
    public SingularInvitationModel getSingularInvitation(String invitationID, String userID) {
        return InvitationAdapter.createInvitationSingularModel(useCaseManager.getInvitation(invitationID, userID));
    }

    /**
     * Accept or decline an invitation
     *
     * @param invitationID ID of invitation
     * @param respondingMessage message response to invitation
     * @param accept whether to accept or decline the invitation
     * @param userID ID of the recipient of the invitation
     * @return
     */
    public boolean acceptInvitation(String invitationID, String respondingMessage, Boolean accept, String userID) {
        return useCaseManager.acceptInvitation(invitationID, respondingMessage, accept, userID);
    }

    /**
     * Create an invitation.
     *
     * @param eventID ID of event of the invitation
     * @param inviterID ID of the inviter
     * @param inviteeID ID of invitee
     * @param initialMessage initial message to invitee
     * @return invitation ID if successful, or null otherwise
     */
    public String createInvitation(String eventID, String inviterID, String inviteeID, String initialMessage) {
        return useCaseManager.createInvitation(eventID, inviterID, inviteeID, initialMessage);
    }

    /**
     * Delete an invitation
     *
     * @param invitationID ID of invitation to delete
     * @param userID ID of inviter
     * @return whether invitation deletion was successful
     */
    public boolean deleteInvitation(String invitationID, String userID) {
        return useCaseManager.deleteInvitation(invitationID, userID);
    }

    // NOTES
    public ListModel getEventsByMemoIDAndOwnerID(String memoID, String ownerID){
        return EventAdapter.createEventListModel(useCaseManager.getEventsByMemoIDAndOwnerID(memoID, ownerID));
    }

    public ListModel getMemosByOwnerID(String ownerID){
        return NoteAdapter.createMemoListModel(useCaseManager.getMemosByOwnerID(ownerID));
    }

    public boolean editMemoName(String memoID, String name, String ownerID){
        return useCaseManager.editMemoName(memoID, name, ownerID);
    }

    public boolean editMemoNote(String memoID, String note, String ownerID){
        return useCaseManager.editMemoNote(memoID, note, ownerID);
    }
    public boolean addMemoToEvent(String memoID, String eventID, String ownerID){
        return useCaseManager.addMemoToEvent(memoID, eventID, ownerID);
    }
    public ListModel getTagsByOwnerID(String ownerID){
        return NoteAdapter.createTagListModel(useCaseManager.getTagsByOwnerID(ownerID));
    }
    public String createMemo(String name, String note, String userID){
        return useCaseManager.createMemo(name, note, userID);
    }
    public String createTag(String name, String userID){
        return useCaseManager.createTag(name, userID);
    }
    public boolean removeMemoFromEvent(String eventID, String ownerID){
        return useCaseManager.removeMemoFromEvent(eventID, ownerID);
    }
    public boolean removeTagFromEvent(String tagID, String eventID, String ownerID) {
        return useCaseManager.removeTagFromEvent(tagID, eventID, ownerID);
    }
    public boolean addTagToEvent(String tagID, String eventID, String ownerID){
        return useCaseManager.addTagToEvent(tagID, eventID, ownerID);
    }
    public boolean deleteMemo(String memoID, String ownerID){
        return useCaseManager.deleteMemo(memoID, ownerID);
    }
    public boolean deleteTag(String tagID, String ownerID){
        return useCaseManager.deleteTag(tagID, ownerID);
    }

    // SERIES
    public boolean createSeriesByCombiningEvents(String seriesName, ArrayList<String> eventIDs, String userID) {
        return useCaseManager.createSeriesByCombiningEvents(seriesName, eventIDs, userID);
    }

    public boolean createSeriesFromEventFormula(String seriesName, String start, String end, String frequency, int numEvents, String userID) {
        return useCaseManager.createSeriesFromEventFormula(seriesName, GlobalAdapter.stringToDateTime(start), GlobalAdapter.stringToDateTime(end), frequency, numEvents, userID);
    }

    public ListModel getSeriesByUserID(String userID) {
        return SeriesAdapter.createSeriesListModel(useCaseManager.getSeriesByUserID(userID));
    }
    public ListModel getSeriesBySeriesName(String seriesName, String userID) {
        return SeriesAdapter.createSeriesListModel(useCaseManager.getSeriesBySeriesName(seriesName, userID));
    }

    public boolean editSeriesName(String seriesID, String seriesName, String userID){
        return useCaseManager.editSeriesName(seriesID, seriesName, userID);
    }

    // TIME

    /**
     * Get a string representation of the current application time
     *
     * @return current time string
     */
    public String getTime() {
        return GlobalAdapter.dateToString(useCaseManager.getTime());
    }

    /**
     * Set application time according to date string input.
     *
     * @param dateTime date string to set time to
     */
    public void setTime(String dateTime) {
        useCaseManager.setTime(GlobalAdapter.stringToDateTime(dateTime));
    }

    /**
     * Reset the application time
     */
    public void resetTime() {
        useCaseManager.resetTime();
    }

    // USER

    /**
     * Create a new user
     *
     * @param username user's username
     * @param password user's password
     * @return whether user was created successfully
     */
    public boolean createUser(String username, String password) {
        return useCaseManager.createUser(username, password);
    }

    /**
     * Login a user using username and password
     *
     * @param username user's username
     * @param password user's password
     * @return user ID if successful, or
     */
    public String loginUser(String username, String password) {
        return useCaseManager.loginUser(username, password);
    }

    /**
     * Get a ListModel for all application users
     *
     * @return ListModel of user
     */
    public ListModel getUsers() {
        return UserAdapter.createUserListModel(useCaseManager.getUsers());
    }
}

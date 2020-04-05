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

public class Controller {
    private UseCaseManager useCaseManager;

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

    public LocalDateTime alertTimeFromInteger(int year,int month, int day, int hour, int minute) {
        return GlobalAdapter.inputToDate(year, month, day, hour, minute);
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
    public ListModel getCalendarsByUserID(String ownerID){
        return CalendarAdapter.createCalendarListModel(useCaseManager.getCalendarsByOwnerID(ownerID));
    }

    public String createCalendar(String userID, String calendarName){
        return useCaseManager.createCalendar(userID, calendarName);
    }

    public boolean editCalendarName(String calendarID, String newName, String ownerID) {
        return useCaseManager.editCalendarName(calendarID, newName, ownerID);
    }

    public boolean addEventToCalendar(String eventID, String calendarID, String ownerID) {
        return useCaseManager.addEventToCalendar(eventID, calendarID, ownerID);
    }

    public boolean removeEventFromCalendar(String eventID, String ownerID) {
        return useCaseManager.removeEventFromCalendar(eventID, ownerID);
    }

    public boolean deleteCalendar(String calendarID, String ownerID) {
        return useCaseManager.deleteCalendar(calendarID, ownerID);
    }

    // EVENTS
    public String createEvent(String name, String start, String end, String location, String userID) {
        return useCaseManager.createEvent(name, GlobalAdapter.stringToDateTime(start), GlobalAdapter.stringToDateTime(end),
                location, userID);
    }
    public String createHolidayEvent(String name, int year, int month, String weekDay, String location, String userID) {
        return useCaseManager.createEventByFirstWeekDay(name, year, GlobalAdapter.intToMonth(month),
                GlobalAdapter.stringToWeekDay(weekDay), location, userID);
    }
    public SingularModel getSingularEvent(String eventID, String userID) {
        Sextuple<CalendarEvent, Memo, ArrayList<Tag>, Alert, Calendars, Series> meta = useCaseManager.getSingularEvent(eventID, userID);
        if (meta == null) {
            return null;
        }
        return EventAdapter.createEventSingularModel(meta.getFirst(), meta.getSecond(), meta.getThird(), meta.getFourth(), meta.getFifth(), meta.getSixth());
    }
    public String getEventDirections(String eventID) {
        return useCaseManager.getEventDirections(eventID);
    }
    public String getEventWeather(String eventID) {
        return useCaseManager.getEventWeather(eventID);
    }
    public String getEventTwitterShare(String eventID) {
        return useCaseManager.getEventTwitterShare(eventID);
    }
    public String getEventEmailShare(String eventID) {
        return useCaseManager.getEventEmailShare(eventID);
    }
    public boolean editEventName(String eventID, String name, String ownerID) {
        return useCaseManager.editEventName(eventID, name, ownerID);
    }
    public boolean editEventLocation(String eventID, String location, String ownerID) {
        return useCaseManager.editEventLocation(eventID, location, ownerID);
    }
    public boolean editEventTime(String eventID, String start, String end, String ownerID) {
        LocalDateTime startTime = GlobalAdapter.stringToDateTime(start);
        LocalDateTime endTime = GlobalAdapter.stringToDateTime(end);
        return useCaseManager.editEventTime(eventID, startTime, endTime, ownerID);
    }
    public boolean deleteEvent(String eventID, String ownerID) {
        return useCaseManager.deleteEvent(eventID, ownerID);
    }

    public ListModel getEventsByName(String name, String userID) {
        return EventAdapter.createEventListModel(useCaseManager.getEventsByNameAndUserID(name, userID));
    }
    public ListModel getPastEvents(String userID) {
        return EventAdapter.createEventListModel(useCaseManager.getPastEvents(userID));
    }
    public ListModel getCurrentEvents(String userID) {
        return EventAdapter.createEventListModel(useCaseManager.getCurrentEvents(userID));
    }
    public ListModel getFutureEvents(String userID) {
        return EventAdapter.createEventListModel(useCaseManager.getFutureEvents(userID));
    }

    public ListModel getHourlyEvents(String date, int hour, String userID) {
        LocalDate dateObject = GlobalAdapter.stringToDate(date);
        Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> eventTimes = useCaseManager.getEventsByHour(dateObject, hour, userID);
        return EventAdapter.createEventDateListModel(eventTimes.getSecond(), eventTimes.getFirst());
    }
    public ListModel getDailyEvents(String date, String userID) {
        LocalDate dateObject = GlobalAdapter.stringToDate(date);
        Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> eventTimes = useCaseManager.getEventsByDay(dateObject, userID);
        return EventAdapter.createEventDateListModel(eventTimes.getSecond(), eventTimes.getFirst());
    }
    public ListModel getWeeklyEvents(String date, String userID) {
        LocalDate dateObject = GlobalAdapter.stringToDate(date);
        Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> eventTimes = useCaseManager.getEventsByWeek(dateObject, userID);
        return EventAdapter.createEventDateListModel(eventTimes.getSecond(), eventTimes.getFirst());
    }
    public ListModel getMonthlyEvents(int year, int month, String userID) {
        Month monthObject = GlobalAdapter.intToMonth(month);
        Tuple<ArrayList<LocalDateTime>, ArrayList<ArrayList<CalendarEvent>>> eventTimes = useCaseManager.getEventsByMonth(year, monthObject, userID);
        return EventAdapter.createEventDateListModel(eventTimes.getSecond(), eventTimes.getFirst());
    }

    public ListModel getEventsFromAlert(String alertID, String ownerID) {
        return EventAdapter.createEventListModel(useCaseManager.getEventsByAlertID(alertID, ownerID));
    }

    public ListModel getEventsByTagIDAndOwnerID(String tagID, String ownerID){
        return EventAdapter.createEventListModel(useCaseManager.getEventsByTagIDAndOwnerID(tagID, ownerID));
    }

    // INVITATIONS
    public ListModel getIncomingInvitations(String userID) {
        return InvitationAdapter.createInvitationListModel(useCaseManager.getIncomingInvitations(userID));
    }
    public ListModel getOutgoingInvitations(String userID) {
        return InvitationAdapter.createInvitationListModel(useCaseManager.getOutgoingInvitations(userID));
    }
    public SingularInvitationModel getSingularInvitation(String invitationID, String userID) {
        return InvitationAdapter.createInvitationSingularModel(useCaseManager.getInvitation(invitationID, userID));
    }
    public boolean acceptInvitation(String invitationID, String respondingMessage, Boolean accept, String userID) {
        return useCaseManager.acceptInvitation(invitationID, respondingMessage, accept, userID);
    }
    public String createInvitation(String eventID, String inviterID, String inviteeID, String initialMessage) {
        return useCaseManager.createInvitation(eventID, inviterID, inviteeID, initialMessage);
    }
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
    public boolean addTagToEvent(String tagID, String eventID, String ownerID){
        return useCaseManager.addTagToEvent(tagID, eventID, ownerID);
    }

    // SERIES
    public boolean createSeriesByCombiningEvents(String seriesName, String[] eventIDs, String userID) {
        return useCaseManager.createSeriesByCombiningEvents(seriesName, eventIDs, userID);
    }

    public boolean createSeriesFromEventFormula(String seriesName, LocalDateTime start, LocalDateTime end, String frequency, int numEvents, String userID) {
        return useCaseManager.createSeriesFromEventFormula(seriesName, start, end, frequency, numEvents, userID);
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
    public String getTime() {
        return GlobalAdapter.dateToString(useCaseManager.getTime());
    }
    public void setTime(String dateTime) {
        useCaseManager.setTime(GlobalAdapter.stringToDateTime(dateTime));
    }
    public void resetTime() {
        useCaseManager.resetTime();
    }

    // USER
    public boolean createUser(String username, String password) {
        return useCaseManager.createUser(username, password);
    }
    public String loginUser(String username, String password) {
        return useCaseManager.loginUser(username, password);
    }
    public ListModel getUsers() {
        return UserAdapter.createUserListModel(useCaseManager.getUsers());
    }
}

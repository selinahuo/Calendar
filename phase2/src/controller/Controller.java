package controller;

import controller.viewmodels.ListModel;
import controller.viewmodels.SingularInvitationModel;
import entities.*;
import usecases.UseCaseManager;

import java.time.LocalDateTime;
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
    public String createIndividualAlert(String eventID, String alertName, LocalDateTime time, String userID ){
        return AlertAdapter.createAlertString(useCaseManager.createIndividualAlert(eventID, alertName, time, userID));
    }

    public String createFrequencyAlert(String eventID, String alertName, String userID,
                                       LocalDateTime startTime, String frequency){
        return AlertAdapter.createAlertString(useCaseManager.createFrequencyAlert(eventID, alertName,
                userID, startTime, frequency));
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

    public boolean editAlertTimeAsIndividual(String alertID, LocalDateTime time, String userID){
        return useCaseManager.editAlertTimeAsIndividual(alertID, time, userID);
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
    public String createEvent(String name, String start, String end, String location, String userID, String calendarID) {
        return useCaseManager.createEvent(name, GlobalAdapter.stringToDate(start), GlobalAdapter.stringToDate(end),
                location, userID, calendarID);
    }
    public String createHolidayEvent(String name, int year, int month, String weekDay, String location, String userID, String calendarID) {
        return useCaseManager.createEventByFirstWeekDay(name, year, GlobalAdapter.intToMonth(month),
                GlobalAdapter.stringToWeekDay(weekDay), location, userID, calendarID);
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
    public ArrayList<CalendarEvent> getEventsArrayListByMemoIDAndOwnerID(String memoID, String ownerID){
        return useCaseManager.getEventsByMemoIDAndOwnerID(memoID, ownerID);
    }

    public Memo getMemoByMemoIDAndOwnerID(String memoID, String ownerID){
        return useCaseManager.getMemoByMemoIDAndOwnerID(memoID, ownerID);
    }
    public ArrayList<Memo> getMemosArrayListByOwnerID(String ownerID){
        return useCaseManager.getMemosByOwnerID(ownerID);
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
    public void setTime(int year, int month, int day, int hour, int minute) {
        useCaseManager.setTime(GlobalAdapter.inputToDate(year, month, day, hour, minute));
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

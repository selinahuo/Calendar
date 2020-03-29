package controller;

import controller.viewmodels.ListModel;
import entities.Series;
import jdk.nashorn.internal.objects.Global;
import usecases.UseCaseManager;
import usecases.time.TimeManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {
    private UseCaseManager useCaseManager;

    public Controller(UseCaseManager useCaseManager) {
        this.useCaseManager = useCaseManager;
    }

    // ALERTS

    // CALENDARS

    // EVENTS
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

    // NOTES

    // SERIES
    public boolean createSeriesByCombiningEvents(String seriesName, String[] eventIDs, String userID) {
        return useCaseManager.createSeriesByCombiningEvents(seriesName, eventIDs, userID);
    }

    public boolean createSeriesFromEventFormula(String seriesName, LocalDateTime start, LocalDateTime end, String frequency, int numEvents, String userID) {
        return useCaseManager.createSeriesFromEventFormula(seriesName, start, end, frequency, numEvents, userID);
    }

    public ArrayList<Series> getSeriesByUserID(String userID) {
        return useCaseManager.getSeriesByUserID(userID);
    }
    public ArrayList<Series> getSeriesBySeriesName(String seriesName, String userID) {
        return useCaseManager.getSeriesBySeriesName(seriesName, userID);
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

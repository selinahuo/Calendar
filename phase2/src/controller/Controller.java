package controller;

import controller.viewmodels.ListModel;
import jdk.nashorn.internal.objects.Global;
import usecases.UseCaseManager;
import usecases.time.TimeManager;

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

    // INVITATIONS

    // NOTES

    // SERIES

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

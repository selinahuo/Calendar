package controller;

import usecases.UseCaseManager;
import entities.CalendarEvent;

public class SimpleController {
    private UseCaseManager useCaseManager;

    public SimpleController(UseCaseManager useCaseManager) {
        this.useCaseManager = useCaseManager;
    }

    public String createEvent(String name) {
        this.useCaseManager.createEvent(new CalendarEvent("1", name, null, null, "here"));
        return name + " event created";
    }
}

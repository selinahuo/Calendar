package controller;

import usecases.IUseCaseManager;
import entities.CalendarEvent;

public class SimpleController {
    private IUseCaseManager useCaseManager;

    public SimpleController(IUseCaseManager useCaseManager) {
        this.useCaseManager = useCaseManager;
    }

    public String createEvent(String name) {
        this.useCaseManager.createEvent(new CalendarEvent("1", name, null, null, "here"));
        return name + " event created";
    }
}

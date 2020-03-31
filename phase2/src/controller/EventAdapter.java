package controller;

import controller.viewmodels.ListModel;
import entities.CalendarEvent;

import java.util.ArrayList;

public class EventAdapter {
    public static String createEventString(CalendarEvent event) {
        return String.format("ID: %s | Event: %s", event.getEventID(), event.getName());
    }

    public static ListModel createEventListModel(ArrayList<CalendarEvent> events) {
        ArrayList<String> eventList = new ArrayList<>();
        for (CalendarEvent event: events) {
            eventList.add(createEventString(event));
        }
        return new ListModel(eventList);
    }
}

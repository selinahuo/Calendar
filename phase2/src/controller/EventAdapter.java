package controller;

import controller.viewmodels.ListModel;
import controller.viewmodels.SingularModel;
import entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

class EventAdapter {
    static String createEventString(CalendarEvent event) {
        return String.format("ID: %s | Event: %s @ %s | From %s to %s", event.getEventID(), event.getName(), event.getLocation(),
                GlobalAdapter.dateToString(event.getStart()), GlobalAdapter.dateToString(event.getEnd()));
    }

    static ListModel createEventListModel(ArrayList<CalendarEvent> events) {
        ArrayList<String> eventList = new ArrayList<>();
        for (CalendarEvent event: events) {
            eventList.add(createEventString(event));
        }
        return new ListModel(eventList);
    }

    static ListModel createEventDateListModel(ArrayList<ArrayList<CalendarEvent>> events, ArrayList<LocalDateTime> times) {
        ArrayList<String> eventDateList = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            eventDateList.add(GlobalAdapter.dateToString(times.get(i)));
            for (CalendarEvent event: events.get(i)) {
                eventDateList.add(createEventString(event));
            }
            eventDateList.add("");
        }
        return new ListModel(eventDateList);
    }

    static SingularModel createEventSingularModel(CalendarEvent event, Memo memo, ArrayList<Tag> tags, Alert alert, Calendars calendar, Series series) {
        String entityString = "Event " + createEventString(event) + "\n";
        if (calendar != null) {
            entityString += "Calendar " + CalendarAdapter.createCalendarString(calendar) + "\n";
        }
        if (series != null) {
            entityString += "Series " + SeriesAdapter.createSeriesString(series) + "\n";
        }
        if (memo != null) {
            entityString += "Memo " + NoteAdapter.createMemoString(memo) + "\n";
        }
        if (tags.size() > 0) {
            entityString += "Tags:\n";
            for (Tag tag: tags) {
                entityString += "    " + NoteAdapter.createTagString(tag) + "\n";
            }
        }
        if (alert != null) {
            entityString += "Alert " + AlertAdapter.createAlertString(alert) + "\n";
        }
        return new SingularModel(entityString, event.getUserID(), event.getEventID());
    }
}

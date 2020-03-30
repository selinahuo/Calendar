package controller;

import controller.viewmodels.ListModel;
import entities.Calendars;

import java.util.ArrayList;

public class CalendarAdapter {

    public static String createCalendarString(Calendars calendar) {
        return String.format("ID: %s | Calendar: %s | Number of events: %s",
                calendar.getCalendarID(), calendar.getCalendarName(), calendar.getCount());
    }

    public static ListModel createCalendarListModel(ArrayList<Calendars> calendars) {
        ArrayList<String> calendarList = new ArrayList<>();
        for (Calendars calendar: calendars) {
            calendarList.add(createCalendarString(calendar));
        }
        return new ListModel(calendarList);
    }

}
package controller;

import controller.viewmodels.ListModel;
import entities.Calendars;

import java.util.ArrayList;

class CalendarAdapter {
    /**
     * A static method that allows translating calendar information to a String representation.
     *
     * @param calendar the calendar instance that would be processed
     * @return A String representation of the information of an calendar instance
     */
    static String createCalendarString(Calendars calendar) {
        return String.format("ID: %s | Calendar: %s | Number of events: %s",
                calendar.getCalendarID(), calendar.getCalendarName(), calendar.getCount());
    }

    /**
     * A static method that creates an ListModel for multiple calendars.
     *
     * @param calendars A List of alerts for the ListModel
     * @return ListModel model that has the information of the calendars in the input List
     */
    static ListModel createCalendarListModel(ArrayList<Calendars> calendars) {
        ArrayList<String> calendarList = new ArrayList<>();
        for (Calendars calendar: calendars) {
            calendarList.add(createCalendarString(calendar));
        }
        return new ListModel(calendarList);
    }

}

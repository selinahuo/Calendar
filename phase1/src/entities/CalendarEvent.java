package entities;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * A class that represents a CalendarEvent
 */
public class CalendarEvent {
    private String eventID;
    private String name;
    private GregorianCalendar start;
    private GregorianCalendar end;
    private String location;

    private String userID;
    private ArrayList<String> tagIDs;
    private ArrayList<String> memoIDs;
    private String seriesID;
    private String alertID;
}


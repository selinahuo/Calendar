package entities;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class that represents a CalendarEvent
 */
public class CalendarEvent {
    private String eventID;
    private String name;
    private Date start;
    private Date end;

    private String location;

    private String userID;
    private ArrayList<String> tagIDs;
    private ArrayList<String> memoIDs;
    private String seriesID;
    private String alertID;

    public CalendarEvent(String eventID, String name, Date start, Date end, String location,
                         String userID, ArrayList<String> tagIDs, ArrayList<String> memoIDs,
                         String seriesID, String alertID) {
        this.eventID = eventID;
        this.name = name;
        this.start = start;
        this.end = end;
        this.location = location;
        this.userID = userID;
        this.tagIDs = tagIDs;
        this.memoIDs = memoIDs;
        this.seriesID = seriesID;
        this.alertID = alertID;
    }

    public String getEventID() {
        return eventID;
    }

    public String getName() {
        return name;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getLocation() {
        return location;
    }

    public String getUserID() {
        return userID;
    }

    public ArrayList<String> getTagIDs() {
        return tagIDs;
    }

    public ArrayList<String> getMemoIDs() {
        return memoIDs;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public String getAlertID() {
        return alertID;
    }
}


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

    public CalendarEvent(String eventID, String name, GregorianCalendar start, GregorianCalendar end, String location,
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

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(GregorianCalendar start) {
        this.start = start;
    }

    public void setEnd(GregorianCalendar end) {
        this.end = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setTagIDs(ArrayList<String> tagIDs) {
        this.tagIDs = tagIDs;
    }

    public void setMemoIDs(ArrayList<String> memoIDs) {
        this.memoIDs = memoIDs;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public void setAlertID(String alertID) {
        this.alertID = alertID;
    }

    public String getEventID() {
        return eventID;
    }

    public String getName() {
        return name;
    }

    public GregorianCalendar getStart() {
        return start;
    }

    public GregorianCalendar getEnd() {
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


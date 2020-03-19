package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A class that represents a CalendarEvent
 */
public class CalendarEvent implements Serializable {
    private String eventID = UUID.randomUUID().toString();
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String location;

    private String userID;
    private String calendarID;
    private ArrayList<String> collaborators;
    private ArrayList<String> tagIDs;
    private ArrayList<String> memoIDs;
    private String seriesID;
    private String alertID;

    public CalendarEvent(String name, LocalDateTime start, LocalDateTime end, String location, String userID, String calendarID) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.location = location;

        this.userID = userID;
        this.calendarID = calendarID;

        this.collaborators = new ArrayList<>();
        this.tagIDs = new ArrayList<>();
        this.memoIDs = new ArrayList<>();
    }

    public String getEventID() {
        return eventID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserID() {
        return userID;
    }

    public String getCalendarID() {
        return calendarID;
    }

    public void setCalendarID(String calendarID) {
        this.calendarID = calendarID;
    }

    public ArrayList<String> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(ArrayList<String> collaborators) {
        this.collaborators = collaborators;
    }

    public ArrayList<String> getTagIDs() {
        return tagIDs;
    }

    public void setTagIDs(ArrayList<String> tagIDs) {
        this.tagIDs = tagIDs;
    }

    public ArrayList<String> getMemoIDs() {
        return memoIDs;
    }

    public void setMemoIDs(ArrayList<String> memoIDs) {
        this.memoIDs = memoIDs;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public String getAlertID() {
        return alertID;
    }

    public void setAlertID(String alertID) {
        this.alertID = alertID;
    }
}

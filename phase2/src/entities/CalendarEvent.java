package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A class that represents a calendar Event
 */
public class CalendarEvent implements Serializable {
    private final String eventID = UUID.randomUUID().toString();
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String location;

    private String userID;
    private String calendarID = "";
    private ArrayList<String> collaborators;
    private ArrayList<String> tagIDs;
    private String memoID = "";
    private String seriesID = "";
    private String alertID = "";

    /**
     * Construct a calendar event with the following properties
     * @param name name the event
     * @param start start datetime of the event
     * @param end end datetime of the event
     * @param location location of the event
     * @param userID ID of the event's owner
     */
    public CalendarEvent(String name, LocalDateTime start, LocalDateTime end, String location,
                         String userID) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.location = location;

        this.userID = userID;
        this.calendarID = calendarID;

        this.collaborators = new ArrayList<>();
        this.tagIDs = new ArrayList<>();
    }

    /**
     * Get event's unique ID
     * @return unique event ID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Get event's name
     * @return event's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set event's name
     * @param name new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get event's start datetime
     * @return event's start datetime
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Set event's start datetime
     * @param start start datetime to set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Get event's end datetime
     * @return event's end datetime
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Set event's end datetime
     * @param end end datetime to set
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Get event's location
     * @return event's location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set event's location
     * @param location location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get event owner's unique ID
     * @return event owner's unique ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Get event's calendar's unique  ID or "" if not associated with an calendar
     * @return event's calendarID or ""
     */
    public String getCalendarID() {
        return calendarID;
    }

    /**
     * Associate event with a calendar or "" for no calendar
     * @param calendarID calendarID to set
     */
    public void setCalendarID(String calendarID) {
        this.calendarID = calendarID;
    }

    /**
     * Get list of user's invited to view this event
     * @return list of invited user's unique user IDs
     */
    public ArrayList<String> getCollaborators() {
        return collaborators;
    }

    /**
     * Add a collaborator to view this event
     * @param collaboratorID unique ID of collaborator to add
     */
    public void addCollaborator(String collaboratorID) {
        collaborators.add(collaboratorID);
    }

    /**
     * Get list of tag's associated with event
     * @return list of unique tag IDs
     */
    public ArrayList<String> getTagIDs() {
        return tagIDs;
    }

    /**
     * Set list of tag's associated with event
     * @param tagIDs tag IDs to set
     */
    public void setTagIDs(ArrayList<String> tagIDs) {
        this.tagIDs = tagIDs;
    }

    /**
     * Get ID of memo associated with event or "" if no memo
     * @return unique memo ID or ""
     */
    public String getMemoID() {
        return memoID;
    }

    /**
     * Set a memo ID to associate with event or "" to remove
     * @param memoID memo ID to set
     */
    public void setMemoID(String memoID) {
        this.memoID = memoID;
    }

    /**
     * Get ID of series associated with event or "" if no series
     * @return unique series ID or ""
     */
    public String getSeriesID() {
        return seriesID;
    }

    /**
     * Set a series ID to associate with event or "" to remove
     * @param seriesID series ID to set
     */
    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    /**
     * Get ID of alert associated with event or "" if no alert
     * @return unique alert ID or ""
     */
    public String getAlertID() {
        return alertID;
    }

    /**
     * Set alert ID to associate with event or "" to remove alert
     * @param alertID alert ID to set
     */
    public void setAlertID(String alertID) {
        this.alertID = alertID;
    }
}

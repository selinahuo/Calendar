package entities;

import java.io.Serializable;
import java.util.UUID;

public class Calendars implements Serializable {
    private final String calendarID = UUID.randomUUID().toString();
    private String ownerID;
    private String calendarName;
    private int count;

    /**
     * Constructor of an individual calendar instance.
     *
     * @param userID the userID of the user that is associated with this alert
     * @param calendarName the the name of this calendar
     */
    public Calendars(String userID, String calendarName) {
        this.ownerID = userID;
        this.calendarName = calendarName;
    }

    /**
     * A public method that returns the calendarID of this Calendar.
     *
     * @return a String representation of the calendarID of this Calendar
     */
    public String getCalendarID() {
        return calendarID;
    }

    /**
     * A public method that returns the ownerID of this Calendar.
     *
     * @return a String representation of the ownerID of this Calendar
     */
    public String getOwnerID() {
        return ownerID;
    }

    /**
     * A public method that changes the owner of this Calendar
     *
     * @param ownerID the new owner(user) of this event
     */
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * A public method that returns the name of this Calendar.
     *
     * @return a String representation of the name of this Calendar
     */
    public String getCalendarName() {
        return calendarName;
    }

    /**
     * A public method that changes the name of this Calendar
     *
     * @param calendarName the new name of this Calendar
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public int getCount() {
        return count;
    }

    public void addCount(){
        this.count++;
    }

    public void removeCount(){
        this.count--;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Calendar: %s | Count: %d | User: %s",
                getCalendarID(), getCalendarName(), getCount(), getOwnerID());
    }
}
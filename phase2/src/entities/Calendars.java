package entities;

import java.io.Serializable;
import java.util.UUID;

public class Calendars implements Serializable {
    private String calendarID = UUID.randomUUID().toString();
    private String ownerID;
    private String calendarName;
    private int count;

    public Calendars(String userID, String calendarName) {
        this.ownerID = userID;
        this.calendarName = calendarName;
    }

    public String getCalendarID() {
        return calendarID;
    }

    public void setCalendarID(String calendarID) {
        this.calendarID = calendarID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getCalendarName() {
        return calendarName;
    }

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
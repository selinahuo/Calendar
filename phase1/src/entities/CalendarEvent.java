package entities;

import java.util.Date;

public class CalendarEvent {
    private String eventID;
    private String name;
    private Date start;
    private Date end;
    private String location;

    public CalendarEvent(String eventID, String name, Date start, Date end, String location) {
        this.eventID = eventID;
        this.name = name;
        this.start = start;
        this.end = end;
        this.location = location;
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
}


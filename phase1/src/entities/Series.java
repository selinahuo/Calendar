package entities;

import java.util.ArrayList;

/**
 * A class that represents a Series of CalendarEvents
 */
public class Series {
    private String seriesID;
    private String name;
    private int eventCount;
    private String userID;

    public Series(String seriesID, String name, int eventCount, String userID) {
        this.seriesID = seriesID;
        this.name = name;
        this.eventCount = eventCount;
        this.userID = userID;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public String getName() {
        return name;
    }

    public int getEventCount() {
        return eventCount;
    }

    public String getUserID() {
        return userID;
    }
}

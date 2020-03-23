package entities;

import java.io.Serializable;

public class Series implements Serializable {
    private String seriesID;
    private String seriesName;
    private int eventCount;
    private String userID;

    public Series(String seriesID, String seriesName, int eventCount, String userID) {
        this.seriesID = seriesID;
        this.seriesName = seriesName;
        this.eventCount = eventCount;
        this.userID = userID;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public int getEventCount() {
        return eventCount;
    }

    public String getUserID() {
        return userID;
    }
}

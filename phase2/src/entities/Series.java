package entities;

import java.io.Serializable;
import java.util.UUID;

public class Series implements Serializable {
    private final String seriesID = UUID.randomUUID().toString();
    private String seriesName;
    private int eventCount;
    private String userID;

    public Series(String seriesName, int eventCount, String userID) {
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

    public void setEventCount(int eventCount) { this.eventCount = eventCount; }

    public void editEventCountRemove() { eventCount--; }

    public String getUserID() {
        return userID;
    }

    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }

    @Override
    public String toString() {
        return String.format("ID: %s | Series: %s | Count: %d | User: %s",
                getSeriesID(), getSeriesName(), getEventCount(), getUserID());
    }
}

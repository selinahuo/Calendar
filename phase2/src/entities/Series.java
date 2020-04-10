package entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class representing a Series
 */
public class Series implements Serializable {
    private final String seriesID = UUID.randomUUID().toString();
    private String seriesName;
    private int eventCount;
    private String userID;

    /**
     * Constructor of an individual series
     * @param seriesName
     * @param eventCount
     * @param userID
     */
    public Series(String seriesName, int eventCount, String userID) {
        this.seriesName = seriesName;
        this.eventCount = eventCount;
        this.userID = userID;
    }

    /**
     * A public method that returns the seriesID of this series.
     *
     * @return
     */
    public String getSeriesID() {
        return seriesID;
    }

    /**
     * A public method that returns the name of this series.
     * @return
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * A public method that returns the event count of this series.
     *
     * @return
     */
    public int getEventCount() {
        return eventCount;
    }

    /**
     * A public method sets the event count.
     *
     * @param eventCount
     */
    public void setEventCount(int eventCount) { this.eventCount = eventCount; }

    /**
     * A public method subtracts the event count.
     *
     */
    public void editEventCountRemove() { eventCount--; }

    /**
     * A public method that gets userID.
     * @return
     */
    public String getUserID() {
        return userID;
    }

    /**
     * A public method sets the series name.
     * @param seriesName
     */
    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }

    @Override
    public String toString() {
        return String.format("ID: %s | Series: %s | Count: %d | User: %s",
                getSeriesID(), getSeriesName(), getEventCount(), getUserID());
    }
}

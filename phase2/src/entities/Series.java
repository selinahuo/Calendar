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
     * @param seriesName the name of this series
     * @param eventCount the number of event in this series
     * @param userID the user id of this series
     */
    public Series(String seriesName, int eventCount, String userID) {
        this.seriesName = seriesName;
        this.eventCount = eventCount;
        this.userID = userID;
    }

    /**
     * A public method that returns the seriesID of this series.
     *
     * @return a String representation of the seriesID
     */
    public String getSeriesID() {
        return seriesID;
    }

    /**
     * A public method that returns the name of this series.
     *
     * @return a String representation of the series nmae
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * A public method that returns the event count of this series.
     *
     * @return a Integer representation of number of events in the series
     */
    public int getEventCount() {
        return eventCount;
    }

    /**
     * A public method sets the event count.
     *
     * @param eventCount the new number of event in the series
     */
    public void setEventCount(int eventCount) { this.eventCount = eventCount; }

    /**
     * A public method subtracts the event count.
     *
     */
    public void editEventCountRemove() { eventCount--; }

    /**
     * A public method that gets userID.
     *
     * @return a String representation of the user ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * A public method sets the series name.
     *
     * @param seriesName the new series name
     */
    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }

    @Override
    public String toString() {
        return String.format("ID: %s | Series: %s | Count: %d | User: %s",
                getSeriesID(), getSeriesName(), getEventCount(), getUserID());
    }
}

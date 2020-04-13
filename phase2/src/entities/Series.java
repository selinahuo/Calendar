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
     * @param seriesName the name of the series
     * @param eventCount number of events in the series
     * @param userID the user ID of the series
     */
    public Series(String seriesName, int eventCount, String userID) {
        this.seriesName = seriesName;
        this.eventCount = eventCount;
        this.userID = userID;
    }

    /**
     * A public method that returns the seriesID of this series.
     *
     * @return the ID of the series
     */
    public String getSeriesID() {
        return seriesID;
    }

    /**
     * A public method that returns the name of this series.
     *
     * @return the name of the series
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * A public method that returns the event count of this series.
     *
     * @return number of events in the series
     */
    public int getEventCount() {
        return eventCount;
    }

    /**
     * A public method sets the event count.
     *
     * @param eventCount the number of events in the series
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
     * @return the user ID of the series
     */
    public String getUserID() {
        return userID;
    }

    /**
     * A public method sets the series name.
     *
     * @param seriesName the new name of the series
     */
    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }
}

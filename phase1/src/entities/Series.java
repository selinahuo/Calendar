package entities;

/**
 * A class that represents a Series of CalendarEvents
 */
public class Series {
    private String seriesID;
    private String name;
    private String[] events;

    /**
     * Construct a new Series.
     * @param seriesID the series' ID
     * @param name the series' name
     * @param events the series' events
     */
    public Series(String seriesID, String name, String[] events) {
        this.seriesID = seriesID;
        this.name = name;
        this.events = events;
    }

    /**
     * Get this series' ID.
     * @return series' ID
     */
    public String getSeriesID() {
        return seriesID;
    }

    /**
     * Get the series' name.
     * @return series' name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the series' events.
     * @return series' events
     */
    public String[] getEvents() {
        return events;
    }
}

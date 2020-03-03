package usecases.series;

import entities.Series;

public interface ISeriesManager {
    /**
     * Create and save a Series.
     * @param name name of the series
     * @param events that belong to the series
     * @return true if series creation was successful, false otherwise
     */
    boolean createSeries(String name, String events);

    /**
     * Edit a series with matching ID
     * @param seriesID ID of the series to edit
     * @param name new name of the series
     * @param events new events of the series
     * @return true if series edit was successful, false otherwise
     */
    boolean editSeries(String seriesID, String name, String events);

    /**
     * Get all Series that have an ID in a list of IDs
     * @param ids returned Series must have an ID in this list
     * @return list of matching Series
     */
    Series[] getSeriesByIDs(String[] ids);

    /**
     * Get a Series that has an ID in a list of IDs and a matching name
     * @param name Series must match this name
     * @param ids Series' ID must be in this list
     * @return a matching Series
     */
    Series getSeriesByNameAndIDs(String name, String[] ids);

    /**
     * Get a Series that has an ID in a list of IDs and contains an eventID
     * @param eventID Series must contain this event ID
     * @param ids Series' ID must be in this list
     * @return a matching Series
     */
    Series getSeriesByEventIDAndIDs(String eventID, String[] ids);
}

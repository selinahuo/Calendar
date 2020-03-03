package usecases.series;

import entities.Series;

/**
 * Provides a public interface for series repository operations
 */
public interface ISeriesRepository {
    /**
     * Save a Series.
     * @param series the Series to save
     * @return true if Series creation was successful, false otherwise
     */
    boolean saveSeries(Series series);

    /**
     * Edit a series.
     * @param id the ID of the series to edit
     * @param name the new name of the series
     * @param events the new events of the series
     * @return true if series edit was successful,
     */
    boolean editSeries(String id, String name, String[] events);

    /**
     * Fetch a Series by its ID.
     * @param id the ID to filter by
     * @return the corresponding Series or null if it does not exist
     */
    Series fetchSeriesByID(String id);

    /**
     * Fetch a Series that has an ID in the list of IDS with a matching name
     * @param name name to match the Series by
     * @param ids returned Series must have an ID in this list
     * @return a Series fitting the name and ID criteria
     */
    Series fetchSeriesByNameAndID(String name, String[] ids);

    /**
     * Fetch a Series that has an ID in the list of IDs and contains the eventID
     * @param eventID eventID of a calendar event that is part of the matching series
     * @param ids returned Series must have an ID in this list
     * @return a Series fitting the eventID and ID criteria
     */
    Series[] fetchSeriesByEventIDAndID(String eventID, String[] ids);
}

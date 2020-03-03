package dataacess;

import entities.Series;
import usecases.series.ISeriesRepository;

public class CSVSeriesRepository implements ISeriesRepository {
    /**
     * Save a Series.
     *
     * @param series the Series to save
     * @return true if Series creation was successful, false otherwise
     */
    @Override
    public boolean saveSeries(Series series) {
        return false;
    }

    /**
     * Edit a series.
     *
     * @param id     the ID of the series to edit
     * @param name   the new name of the series
     * @param events the new events of the series
     * @return true if series edit was successful,
     */
    @Override
    public boolean editSeries(String id, String name, String[] events) {
        return false;
    }

    /**
     * Fetch a Series by its ID.
     *
     * @param id the ID to filter by
     * @return the corresponding Series or null if it does not exist
     */
    @Override
    public Series fetchSeriesByID(String id) {
        return null;
    }

    /**
     * Fetch a Series that has an ID in the list of IDS with a matching name
     *
     * @param name name to match the Series by
     * @param ids  returned Series must have an ID in this list
     * @return a Series fitting the name and ID criteria
     */
    @Override
    public Series fetchSeriesByNameAndID(String name, String[] ids) {
        return null;
    }

    /**
     * Fetch a Series that has an ID in the list of IDs and contains the eventID
     *
     * @param eventID eventID of a calendar event that is part of the matching series
     * @param ids     returned Series must have an ID in this list
     * @return a Series fitting the eventID and ID criteria
     */
    @Override
    public Series[] fetchSeriesByEventIDAndID(String eventID, String[] ids) {
        return new Series[0];
    }
}

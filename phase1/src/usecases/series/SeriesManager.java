package usecases.series;

import entities.Series;

class SeriesManager implements ISeriesManager {
    private ISeriesRepository repository;

    SeriesManager(ISeriesRepository repository) {
        this.repository = repository;
    }

    /**
     * Create and save a Series.
     *
     * @param name   name of the series
     * @param events that belong to the series
     * @return true if series creation was successful, false otherwise
     */
    @Override
    public boolean createSeries(String name, String events) {
        return false;
    }

    /**
     * Edit a series with matching ID
     *
     * @param seriesID ID of the series to edit
     * @param name     new name of the series
     * @param events   new events of the series
     * @return true if series edit was successful, false otherwise
     */
    @Override
    public boolean editSeries(String seriesID, String name, String events) {
        return false;
    }

    /**
     * Get all Series that have an ID in a list of IDs
     *
     * @param ids returned Series must have an ID in this list
     * @return list of matching Series
     */
    @Override
    public Series[] getSeriesByIDs(String[] ids) {
        return new Series[0];
    }

    /**
     * Get a Series that has an ID in a list of IDs and a matching name
     *
     * @param name Series must match this name
     * @param ids  Series' ID must be in this list
     * @return a matching Series
     */
    @Override
    public Series getSeriesByNameAndIDs(String name, String[] ids) {
        return null;
    }

    /**
     * Get a Series that has an ID in a list of IDs and contains an eventID
     *
     * @param eventID Series must contain this event ID
     * @param ids     Series' ID must be in this list
     * @return a matching Series
     */
    @Override
    public Series getSeriesByEventIDAndIDs(String eventID, String[] ids) {
        return null;
    }
}

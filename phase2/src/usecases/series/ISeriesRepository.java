package usecases.series;

import entities.Series;
import entities.Tag;

import java.util.ArrayList;

/**
 * Provides a public interface for series repository operations
 */
public interface ISeriesRepository {

    // save
    /**
     * Save a Series.
     *
     * @param series the series that wanting to save
     */
    void saveSeries(Series series);

    // fetch
    /**
     * Returns a list of Series by userID.
     *
     * @param userID the user that will be getting the series
     * @return a series that matches the input seriesID and userID
     */
    ArrayList<Series> fetchSeriesByUserID(String userID);

    /**
     * Returns a list of Series by Series's name and userID.
     *
     * @param seriesName the name of the series that will be getting
     * @param userID the user of the series that will be getting
     * @return list of series the user has that matches the series name
     */
    ArrayList<Series> fetchSeriesBySeriesNameAndUserID(String seriesName, String userID);

    /**
     * Returns a single Series by SeriesID and userID.
     *
     * @param seriesID the ID of the series
     * @param userID the user that will be getting the series
     * @return a series that matches the input seriesID and userID
     */
    Series fetchSeriesBySeriesIDAndUserID(String seriesID, String userID);

    // edit
    /**
     * Returns whether the modification of Series's name is successful.
     *
     * @param seriesID the id of the series
     * @param seriesName the new name of the series that will be modified
     * @param ownerID the id of the owner
     * @return True if successful
     */
    boolean editSeriesName(String seriesID, String seriesName, String ownerID);

    /**
     * Returns whether adding the Series's event count is successful.
     *
     * @param seriesID the id of the series
     * @param eventCount the new number of events inside this series
     * @param ownerID the id of the owner
     * @return True if series event count has been modified successful
     */
    boolean editSeriesEventCount(String seriesID, int eventCount, String ownerID);

    /**
     * Returns whether subtracting the Series's event count is successful.
     *
     * @param seriesID the id of the series
     * @param ownerID the id of the owner
     * @return True if number of series events has been modified successful
     */
    boolean editSeriesEventCountRemove(String seriesID, String ownerID);

    // delete
    /**
     * Returns whether Series deletion is successful.
     *
     * @param seriesID the id of series that will be deleted
     * @param ownerID the owner id of the seires
     * @return
     */
    boolean deleteSeries(String seriesID, String ownerID);

}

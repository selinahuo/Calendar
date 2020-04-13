package usecases.series;

import entities.Series;
import entities.Tag;

import java.util.ArrayList;

/**
 * Provides a public interface for series repository operations
 */
public interface ISeriesRepository {

    // Save

    /**
     * Save a Series.
     *
     * @param series the series that needs to save
     */
    void saveSeries(Series series);

    // Fetch

    /**
     * Returns a list of Series by userID.
     *
     * @param userID the ID of the user
     * @return list of series that the user has
     */
    ArrayList<Series> fetchSeriesByUserID(String userID);

    /**
     * Returns a list of Series by Series's name and userID.
     *
     * @param seriesName the name of the series
     * @param userID the ID of user of the series
     * @return List of series that in the series name
     */
    ArrayList<Series> fetchSeriesBySeriesNameAndUserID(String seriesName, String userID);

    /**
     * Returns a single Series by SeriesID and userID.
     *
     * @param seriesID the ID of the series
     * @param userID the ID of the user of the series
     * @return series that matches the series ID and the user ID
     */
    Series fetchSeriesBySeriesIDAndUserID(String seriesID, String userID);

    // Edit

    /**
     * Returns whether the modification of Series's name is successful.
     *
     * @param seriesID the ID of the seires
     * @param seriesName the new name of the series
     * @param ownerID the ID of the owner
     * @return If the edition is successful
     */
    boolean editSeriesName(String seriesID, String seriesName, String ownerID);

    /**
     * Returns whether adding the Series's event count is successful.
     *
     * @param seriesID  the ID of the seires
     * @param eventCount the new number of events in the series
     * @param ownerID the ID of the owner
     * @return If the edition is successful
     */
    boolean editSeriesEventCount(String seriesID, int eventCount, String ownerID);

    /**
     * Returns whether subtracting the Series's event count is successful.
     *
     * @param seriesID the ID of the seires
     * @param ownerID the ID of the owner
     * @return  If the removal is successful
     */
    boolean editSeriesEventCountRemove(String seriesID, String ownerID);

    // Delete

    /**
     * Returns whether Series deletion is successful.
     *
     * @param seriesID the ID of the seires
     * @param ownerID the ID of the owner
     * @return  If the deletion is successful
     */
    boolean deleteSeries(String seriesID, String ownerID);

}

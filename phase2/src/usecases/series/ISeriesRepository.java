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
     * @param series
     */
    void saveSeries(Series series);

    // Fetch

    /**
     * Returns a list of Series by userID.
     *
     * @param userID
     * @return
     */
    ArrayList<Series> fetchSeriesByUserID(String userID);

    /**
     * Returns a list of Series by Series's name and userID.
     *
     * @param seriesName
     * @param userID
     * @return
     */
    ArrayList<Series> fetchSeriesBySeriesNameAndUserID(String seriesName, String userID);

    /**
     * Returns a single Series by SeriesID and userID.
     *
     * @param seriesID
     * @param userID
     * @return
     */
    Series fetchSeriesBySeriesIDAndUserID(String seriesID, String userID);

    // Edit

    /**
     * Returns whether the modification of Series's name is successful.
     *
     * @param seriesID
     * @param seriesName
     * @param OwnerID
     * @return
     */
    boolean editSeriesName(String seriesID, String seriesName, String OwnerID);

    /**
     * Returns whether adding the Series's event count is successful.
     *
     * @param seriesID
     * @param eventCount
     * @param ownerID
     * @return
     */
    boolean editSeriesEventCount(String seriesID, int eventCount, String ownerID);

    /**
     * Returns whether subtracting the Series's event count is successful.
     *
     * @param seriesID
     * @param ownerID
     * @return
     */
    boolean editSeriesEventCountRemove(String seriesID, String ownerID);

    // Delete

    /**
     * Returns whether Series deletion is successful.
     *
     * @param seriesID
     * @param ownerID
     * @return
     */
    boolean deleteSeries(String seriesID, String ownerID);

}

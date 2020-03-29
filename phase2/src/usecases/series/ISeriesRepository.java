package usecases.series;

import entities.Series;
import entities.Tag;

import java.util.ArrayList;

public interface ISeriesRepository {

    // Save
    void saveSeries(Series series);

    // Fetch
    ArrayList<Series> fetchSeriesByUserID(String userID);
    ArrayList<Series> fetchSeriesBySeriesNameAndUserID(String seriesName, String userID);
    Series fetchSeriesBySeriesIDAndUserID(String seriesID, String userID);

    // Edit
    boolean editSeriesName(String seriesID, String seriesName, String OwnerID);
    boolean editSeriesEventCount(String seriesID, int eventCount, String ownerID);
    boolean editSeriesEventCountRemove(String seriesID, String ownerID);

    // Delete
    boolean deleteSeries(String seriesID, String ownerID);

}

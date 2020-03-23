package usecases.series;

import entities.Series;
import entities.Tag;

import java.util.ArrayList;

public interface ISeriesRepository {

    // Save
    boolean saveSeries(Series series);

    // Fetch - singular
    ArrayList<Series> fetchSeriesByUserID(String userID);
    ArrayList<Series> fetchSeriesBySeriesName(String seriesName);
    ArrayList<Series> fetchSeriesBySeriesIDAndUserID(String seriesID, String userID);

    // Edit
    boolean editSeriesName(String seriesID, String seriesNewName, String OwnerID);

    // Delete
    boolean deleteSeries(String seriesID, String ownerID);

}

package usecases.series;

import entities.Series;
import entities.Tag;

import java.util.ArrayList;

public interface ISeriesRepository {

    // Save
    boolean saveSeries(Series series);

    // Fetch - singular
    Series fetchSeriesByUserID(String userID);
    Series fetchSeriesBySeriesName(String seriesName);

    // Edit
    boolean editSeriesName(String seriesID, String seriesNewName, String OwnerID);

    // Delete
    boolean deleteSeries(String seriesID, String ownerID);

}

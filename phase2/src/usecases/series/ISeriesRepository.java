package usecases.series;

import entities.Series;

import java.util.ArrayList;

public interface ISeriesRepository {
    /**
     * Save a Series.
     * @param series the Series to save
     * @return true if Series creation was successful, false otherwise
     */
    boolean saveSeries(Series series);

    Series fetchSeriesByIDAndUserID(String seriesID, String userID);

    /**
     * Fetch a Series that has an ID in the list of IDS with a matching name
     * @param seriesName name to match the Series by
     * @param userID returned Series must have an ID in this list
     * @return a Series fitting the name and ID criteria
     */
    Series fetchSeriesByNameAndUserID(String seriesName, String userID);

    ArrayList<Series> fetchSeriesByUserID(String userID);

    ArrayList<Series> fetchSeriesBySeriesName(String seriesName);

}

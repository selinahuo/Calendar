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

    Series fetchSeriesByUserID(String userID);

    Series fetchSeriesBySeriesName(String seriesName);

}

package dataaccess;

import entities.Series;
import usecases.series.ISeriesRepository;

import java.util.ArrayList;

public class SerializableSeriesRepository extends SerializableRepository<Series> implements ISeriesRepository {
    public SerializableSeriesRepository() {
        super("series.ser");
    }

    /**
     * Save a Series.
     *
     * @param series the Series to save
     * @return true if Series creation was successful, false otherwise
     */
    @Override
    public boolean saveSeries(Series series) {
        ArrayList<Series> seriesArr = deserialize();
        seriesArr.add(series);
        serialize(seriesArr);
        return true;
    }

    /**
     * Fetch a Series that has an ID in the list of IDS with a matching name
     *
     * @param name   name to match the Series by
     * @param userID returned Series must have an ID in this list
     * @return a Series fitting the name and ID criteria
     */
    @Override
    public Series fetchSeriesByNameAndUserID(String name, String userID) {
        ArrayList<Series> seriesArr = deserialize();
        for (Series series: seriesArr) {
            if (series.getName().equals(name) && series.getUserID().equals(userID)) {
                return series;
            }
        }
        return null;
    }
}

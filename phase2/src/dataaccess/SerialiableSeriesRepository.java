package dataaccess;

import entities.Series;
import usecases.series.ISeriesRepository;

import java.util.ArrayList;

public class SerialiableSeriesRepository extends SerializableRepository<Series> implements ISeriesRepository {
    public SerialiableSeriesRepository(String serFile) {
        super("series.ser");
    }


    public boolean saveSeries(Series series) {
        ArrayList<Series> seriesArr = deserialize();
        seriesArr.add(series);
        serialize(seriesArr);
        return true;
    }

    public Series fetchSeriesByIDAndUserID(String seriesID, String userID) {
        ArrayList<Series> seriesArr = deserialize();
        for (Series series: seriesArr) {
            if (series.getSeriesID().equals(seriesID) && series.getUserID().equals(userID)) {
                return series;
            }
        }
        return null;
    }


    public Series fetchSeriesByNameAndUserID(String seriesName, String userID) {
        ArrayList<Series> seriesArr = deserialize();
        for (Series series: seriesArr) {
            if (series.getSeriesName().equals(seriesName) && series.getUserID().equals(userID)) {
                return series;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Series> fetchSeriesByUserID(String userID) {
        return null;
    }

    @Override
    public ArrayList<Series> fetchSeriesBySeriesName(String seriesName) {
        return null;
    }

}


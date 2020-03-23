package dataaccess;

import entities.Series;
import usecases.series.ISeriesRepository;

import java.util.ArrayList;

public class SerialiableSeriesRepository extends SerializableRepository<Series> implements ISeriesRepository {
    public SerialiableSeriesRepository(String serFile) {
        super("series.ser");
    }

    @Override
    public boolean saveSeries(Series series) {
        ArrayList<Series> seriesArr = deserialize();
        seriesArr.add(series);
        serialize(seriesArr);
        return true;
    }

    @Override
    public Series fetchSeriesByUserID(String userID) {
        ArrayList<Series> seriesArr = deserialize();
        for (Series series: seriesArr){
            if (series.getUserID().equals(userID)){
                return series;
            }
        }
        return null;
    }

    @Override
    public Series fetchSeriesBySeriesName(String seriesName) {
        ArrayList<Series> seriesArr = deserialize();
        for (Series series: seriesArr){
            if (series.getSeriesName().equals(seriesName)){
                return series;
            }
        }
        return null;
    }

    @Override
    public boolean editSeriesName(String seriesID, String seriesNewName, String OwnerID) {
        ArrayList<Series> seriesArr = deserialize();
        for (Series series: seriesArr){
            if (series.getSeriesID().equals(seriesID)){
                series.setSeriesName(seriesNewName);
            }
        }
        return true;
    }

    @Override
    public boolean deleteSeries(String seriesID, String ownerID) {
        return false;
    }


}


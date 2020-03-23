package dataaccess;

import entities.Alert;
import entities.CalendarEvent;
import entities.Series;
import usecases.series.ISeriesRepository;

import java.util.ArrayList;
import java.util.List;

public class SerializableSeriesRepository extends SerializableRepository<Series> implements ISeriesRepository {
    public SerializableSeriesRepository(String serFile) {
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
    public ArrayList<Series> fetchSeriesByUserID(String userID) {
        return fetchPlural((Series series) ->
                series.getUserID() != null && series.getUserID().equals(userID));
    }

    @Override
    public ArrayList<Series> fetchSeriesBySeriesName(String seriesName) {
        return fetchPlural((Series series) ->
                series.getSeriesName() != null && series.getSeriesName().equals(seriesName));
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


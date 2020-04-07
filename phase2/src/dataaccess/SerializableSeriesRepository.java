package dataaccess;

import entities.Series;
import usecases.series.ISeriesRepository;

import java.util.ArrayList;
import java.util.List;

class SerializableSeriesRepository extends SerializableRepository<Series> implements ISeriesRepository {
    SerializableSeriesRepository() {
        super("./series.ser");
    }

    @Override
    public void saveSeries(Series series) {
        saveItem(series);
    }

    @Override
    public ArrayList<Series> fetchSeriesByUserID(String userID) {
        return fetchPlural((Series s) -> s.getUserID().equals(userID));
    }

    @Override
    public ArrayList<Series> fetchSeriesBySeriesNameAndUserID(String seriesName, String userID) {
        return fetchPlural((Series s) -> s.getUserID().equals(userID) && s.getSeriesName().equals(seriesName));
    }

    @Override
    public Series fetchSeriesBySeriesIDAndUserID(String seriesID, String userID) {
        return fetchSingular((Series s) -> s.getUserID().equals(userID) && s.getSeriesID().equals(seriesID));
    }

    @Override
    public boolean editSeriesName(String seriesID, String seriesName, String ownerID) {
        return editSingular(
                (Series s) -> s.getSeriesID().equals(seriesID) && s.getUserID().equals(ownerID),
                (Series s) -> s.setSeriesName(seriesName)
        );
    }

    @Override
    public boolean editSeriesEventCount(String seriesID, int eventCount, String ownerID) {
        return editSingular(
                (Series s) -> s.getSeriesID().equals(seriesID) && s.getUserID().equals(ownerID),
                (Series s) -> s.setEventCount(eventCount)
        );
    }

    @Override
    public boolean editSeriesEventCountRemove(String seriesID, String ownerID) {
        return editSingular(
                (Series s) -> s.getSeriesID().equals(seriesID) && s.getUserID().equals(ownerID),
                Series::editEventCountRemove
        );
    }

    @Override
    public boolean deleteSeries(String seriesID, String ownerID) {
        return deleteSingular((Series s) -> s.getSeriesID().equals(seriesID) && s.getUserID().equals(ownerID));
    }

}


package controller;

import controller.viewmodels.ListModel;
import entities.Series;

import java.util.ArrayList;

public class SeriesAdapter {
    public static String createSeriesString(Series series){
        return String.format("SeriesID: %s| SeriesName: %s| EventCount: %d, | UerID: %s",
                series.getSeriesID(),series.getSeriesName(), series.getEventCount(), series.getUserID());
    }

    public static ListModel createSeriesListModel(ArrayList<Series> series){
        ArrayList<String> seriesList = new ArrayList<>();
        for (Series serie : series){
            seriesList.add(createSeriesString(serie));
        }
        return new ListModel(seriesList);
    }
}

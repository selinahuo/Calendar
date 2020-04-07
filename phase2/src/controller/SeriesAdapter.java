package controller;

import controller.viewmodels.ListModel;
import entities.Series;

import java.util.ArrayList;

class SeriesAdapter {
    static String createSeriesString(Series series){
        return String.format("ID: %s | Name: %s | Event Count: %d, | User: %s",
                series.getSeriesID(),series.getSeriesName(), series.getEventCount(), series.getUserID());
    }

    static ListModel createSeriesListModel(ArrayList<Series> series){
        ArrayList<String> seriesList = new ArrayList<>();
        for (Series individualSeries : series){
            seriesList.add(createSeriesString(individualSeries));
        }
        return new ListModel(seriesList);
    }
}

package controller;

import controller.viewmodels.ListModel;
import entities.Series;

import java.util.ArrayList;

class SeriesAdapter {

    /**
     * A private static method that allows translating series status to a string representation.
     *
     * @param series the series instance that would be processed
     * @return a String representation fo the status of a series instance
     */
    static String createSeriesString(Series series){
        return String.format("ID: %s | Name: %s | Event Count: %d, | User: %s",
                series.getSeriesID(),series.getSeriesName(), series.getEventCount(), series.getUserID());
    }

    /**
     * A private method that allows translating series information to a Series representation.
     *
     * @param series the series instance that would be precessed
     * @return A list of model that has the information of the series in the input list
     */
    static ListModel createSeriesListModel(ArrayList<Series> series){
        ArrayList<String> seriesList = new ArrayList<>();
        for (Series individualSeries : series){
            seriesList.add(createSeriesString(individualSeries));
        }
        return new ListModel(seriesList);
    }
}

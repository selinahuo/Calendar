package usecases.series;

import entities.CalendarEvent;
import entities.Series;
import usecases.events.EventManager;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
import java.time.LocalDateTime;

public class SeriesManager implements Observer {
    private ISeriesRepository repository;
    private EventManager eventManager;

    SeriesManager(ISeriesRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    // save
    boolean createSeriesByCombiningEvents(String seriesName, String[] eventIDs, String userID){
        Series newSeries = new Series(UUID.randomUUID().toString(), seriesName, eventIDs.length, userID);
        this.repository.saveSeries(newSeries);
        for (String id : eventIDs) {
            this.eventManager.editSeriesID(id, newSeries.getSeriesID(), userID);
        }
        return true;
    }

    boolean createSeriesFromEventFormula(String seriesName, LocalDateTime start, LocalDateTime end, String frequency, int numEvents, String userID){
        Series newSeries = new Series(UUID.randomUUID().toString(), seriesName, numEvents, userID);
        ArrayList<LocalDateTime[]> times = getTimes(start, end, frequency, numEvents);
        for (LocalDateTime[] time : times) {
            this.eventManager.createEvent("", time[0], time[1], "", userID, null);
        }
        this.repository.saveSeries(newSeries);
        return true;
    }

    private ArrayList<LocalDateTime[]> getTimes(LocalDateTime start, LocalDateTime end, String frequency, int numEvents) {
        int daysToAdd;
        if (frequency.equals("d")) {
            daysToAdd = 1;
        } else {
            daysToAdd = 7;
        }
        ArrayList<LocalDateTime[]> times = new ArrayList<>();
        LocalDateTime startTime = start.plusHours(0);
        LocalDateTime endTime = end.plusHours(0);
        int i = 0;
        while (i < numEvents) {
            LocalDateTime startClone = startTime.plusHours(0);
            LocalDateTime endClone = endTime.plusHours(0);
            LocalDateTime[] timeArray = {startClone, endClone};
            times.add(timeArray);
            startTime.plusDays(daysToAdd);
            endTime.plusDays(daysToAdd);
            i++;
        }
        return times;
    }

    // singular
    public Series getSeriesByUserID(String userID){

        return this.repository.fetchSeriesByUserID(userID);
    }

    public Series getSeriesBySeriesName(String seriesName){
        return this.repository.fetchSeriesBySeriesName(seriesName);
    }

    // plural
    public ArrayList<CalendarEvent> getSeriesByIDAndUserID(String seriesID, String userID){
        return this.eventManager.getEventsBySeriesIDAndOwnerID(seriesID, userID);
    }

    // edit
    boolean editSeriesName(String seriesID, String seriesNewName, String OwnerID){
        return repository.editSeriesName(seriesID, seriesNewName, OwnerID);
    }
    // delete
    public boolean deleteSeries(String seriesID, String ownerID) {
        return repository.deleteSeries(seriesID, ownerID);
    }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("series has changed");
    }
}

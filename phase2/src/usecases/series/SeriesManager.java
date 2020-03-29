package usecases.series;

import entities.CalendarEvent;
import entities.Series;
import usecases.events.EventManager;
import usecases.events.IEventDeletionObserver;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class SeriesManager implements IEventDeletionObserver {
    private ISeriesRepository repository;
    private EventManager eventManager;

    public SeriesManager(ISeriesRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    // save
    public boolean createSeriesByCombiningEvents(String seriesName, String[] eventIDs, String userID){
        Series newSeries = new Series(seriesName, 0, userID);
        repository.saveSeries(newSeries);
        int count = 0;
        for (String id : eventIDs) {
            boolean success = eventManager.editSeriesID(id, newSeries.getSeriesID(), userID);
            if (success) {
                count++;
            }
        }
        repository.editSeriesEventCount(newSeries.getSeriesID(), count, userID);
        return true;
    }

    public boolean createSeriesFromEventFormula(String seriesName, LocalDateTime start, LocalDateTime end, String frequency, int numEvents, String userID){
        Series newSeries = new Series(seriesName, numEvents, userID);
        ArrayList<LocalDateTime[]> times = getTimes(start, end, frequency, numEvents);
        for (LocalDateTime[] time : times) {
            eventManager.createEvent(seriesName, time[0], time[1], "", userID, "");
        }
        repository.saveSeries(newSeries);
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
        LocalDateTime startTime = LocalDateTime.from(start);
        LocalDateTime endTime = LocalDateTime.from(end);
        int i = 0;
        while (i < numEvents) {
            LocalDateTime startClone = LocalDateTime.from(startTime);
            LocalDateTime endClone = LocalDateTime.from(endTime);
            LocalDateTime[] timeArray = {startClone, endClone};
            times.add(timeArray);
            startTime = startTime.plusDays(daysToAdd);
            endTime = endTime.plusDays(daysToAdd);
            i++;
        }
        return times;
    }

    // plural
    public ArrayList<Series> getSeriesByUserID(String userID){
        return repository.fetchSeriesByUserID(userID);
    }

    public ArrayList<Series> getSeriesBySeriesName(String seriesName, String userID){
        return repository.fetchSeriesBySeriesNameAndUserID(seriesName, userID);
    }

    // edit
    public boolean editSeriesName(String seriesID, String seriesName, String userID){
        return repository.editSeriesName(seriesID, seriesName, userID);
    }

    @Override
    public void handleEventDeletion(CalendarEvent event) {
        Series series = repository.fetchSeriesBySeriesIDAndUserID(event.getSeriesID(), event.getUserID());
        if (series != null) {
            if (series.getEventCount() <= 1) {
                repository.deleteSeries(event.getSeriesID(), event.getUserID());
            } else {
                repository.editSeriesEventCountRemove(event.getSeriesID(), event.getUserID());
            }
        }
    }
}

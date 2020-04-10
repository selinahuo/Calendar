package usecases.series;

import entities.CalendarEvent;
import entities.Series;
import usecases.events.EventManager;
import usecases.events.IEventDeletionObserver;

import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * A class that is responsible for the creation and modification of series instances in the Calendar program.
 */
public class SeriesManager implements IEventDeletionObserver {
    private ISeriesRepository repository;
    private EventManager eventManager;

    /**
     * Constructor for SeriesManager.
     *
     * @param repository
     * @param eventManager
     */
    public SeriesManager(ISeriesRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    //save
    /**
     * Create a Series by combining events.
     *
     * @param seriesName
     * @param eventIDs
     * @param userID
     * @return
     */
    public boolean createSeriesByCombiningEvents(String seriesName, ArrayList<String> eventIDs, String userID){
        Series newSeries = new Series(seriesName, 0, userID);
        repository.saveSeries(newSeries);
        int count = 0;
        for (String id : eventIDs) {
            boolean success = eventManager.editSeriesID(id, newSeries.getSeriesID(), userID);
            if (success) {
                count++;
            }
        }
        if (count > 0) {
            repository.editSeriesEventCount(newSeries.getSeriesID(), count, userID);
            return true;
        }
        repository.deleteSeries(newSeries.getSeriesID(), userID);
        return false;
    }

    /**
     * Create a Series from Event formula.
     *
     * @param seriesName
     * @param start
     * @param end
     * @param frequency
     * @param numEvents
     * @param userID
     * @return
     */
    public boolean createSeriesFromEventFormula(String seriesName, LocalDateTime start, LocalDateTime end, String frequency, int numEvents, String userID){
        Series newSeries = new Series(seriesName, numEvents, userID);
        ArrayList<LocalDateTime[]> times = getTimes(start, end, frequency, numEvents);
        for (LocalDateTime[] time : times) {
            String eventID = eventManager.createEvent(seriesName, time[0], time[1], "", userID);
            eventManager.editSeriesID(eventID, newSeries.getSeriesID(), userID);
        }
        repository.saveSeries(newSeries);
        return true;
    }

    /**
     * get times
     *
     * @param start
     * @param end
     * @param frequency
     * @param numEvents
     * @return
     */
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

    // singular
    /**
     * Get a single Series by SeriesID and userID.
     *
     * @param seriesID
     * @param userID
     * @return
     */
    public Series getSeriesBySeriesIDAndUserID(String seriesID, String userID){
        return repository.fetchSeriesBySeriesIDAndUserID(seriesID, userID);
    }

    // plural
    /**
     * Get multiple Series by userID.
     *
     * @param userID
     * @return
     */
    public ArrayList<Series> getSeriesByUserID(String userID){
        return repository.fetchSeriesByUserID(userID);
    }

    /**
     * Get Series by Series name.
     *
     * @param seriesName
     * @param userID
     * @return
     */
    public ArrayList<Series> getSeriesBySeriesName(String seriesName, String userID){
        return repository.fetchSeriesBySeriesNameAndUserID(seriesName, userID);
    }

    // edit
    /**
     * Edit Series's name.
     *
     * @param seriesID
     * @param seriesName
     * @param userID
     * @return
     */
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

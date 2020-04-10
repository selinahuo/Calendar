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
     * @param repository the repository associated with the series
     * @param eventManager the eventManager of the calendar events that the series are associated to
     */
    public SeriesManager(ISeriesRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    //save
    /**
     * Create a Series by combining events.
     *
     * @param seriesName the series name of the series that will be creating
     * @param eventIDs list of event IDs of this series
     * @param userID the user of this series
     * @return True if successful
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
     * @param seriesName the series name of the series that will be creating
     * @param start the start time of this series
     * @param end the end time of this series
     * @param frequency the frequency of this series
     * @param numEvents number of events inside this series
     * @param userID the user that creates this series
     * @return True if successful
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
     * get times by the number of events and frequency
     *
     * @param start the start time
     * @param end the end time
     * @param frequency the frequency of events
     * @param numEvents the number of events happened between the start and the end time
     * @return list of times for each of the events
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
     * @param seriesID the ID of the series
     * @param userID the user that will be getting the series
     * @return a series that matches the input seriesID and userID
     */
    public Series getSeriesBySeriesIDAndUserID(String seriesID, String userID){
        return repository.fetchSeriesBySeriesIDAndUserID(seriesID, userID);
    }

    // plural
    /**
     * Get multiple Series by userID.
     *
     * @param userID the user that will be getting the series
     * @return list of series that matches the user name
     */
    public ArrayList<Series> getSeriesByUserID(String userID){
        return repository.fetchSeriesByUserID(userID);
    }

    /**
     * Get Series by Series name.
     *
     * @param seriesName the name of the series that will be getting
     * @param userID the user of the series that will be getting
     * @return list of series the user has that matches the series name
     */
    public ArrayList<Series> getSeriesBySeriesName(String seriesName, String userID){
        return repository.fetchSeriesBySeriesNameAndUserID(seriesName, userID);
    }

    // edit
    /**
     * Edit Series's name.
     *
     * @param seriesID the id of the series
     * @param seriesName the new name of the series that will be modified
     * @param userID the id of the user
     * @return True if successful
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

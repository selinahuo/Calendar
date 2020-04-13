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
     * @param repository the repository associated with events
     * @param eventManager the eventManager of the calendar events that the tags are associated to
     */
    public SeriesManager(ISeriesRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    //save
    /**
     * Create a Series by combining events.
     *
     * @param seriesName the name of the series that is going to create
     * @param eventIDs list of event IDs for the events that going to be in the series
     * @param userID the user that this series will notify
     * @return If the series was successfully created
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
     * Create a series from the event formula
     * @param seriesName the name of the series that is going to be create
     * @param start the start time of the events in series
     * @param end the end time of the events in series
     * @param frequency the frequency of the events
     * @param numEvents the number of events in the series
     * @param userID the user ID of the series
     * @return If the series was successfully created
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

    /**
     * Get a single Series by SeriesID and userID.
     *
     * @param seriesID the ID of the seires
     * @param userID the ID of the user
     * @return the series that the use has which corresponds to the seriesID
     */
    public Series getSeriesBySeriesIDAndUserID(String seriesID, String userID){
        return repository.fetchSeriesBySeriesIDAndUserID(seriesID, userID);
    }

    /**
     * Get multiple Series by user
     *
     * @param userID ID of user
     * @return list of matching series
     */
    public ArrayList<Series> getSeriesByUserID(String userID){
        return repository.fetchSeriesByUserID(userID);
    }

    /**
     * Get Series by Series name.
     *
     * @param seriesName the name of the series
     * @param userID the ID of the user
     * @return List of series that in the series name
     */
    public ArrayList<Series> getSeriesBySeriesName(String seriesName, String userID){
        return repository.fetchSeriesBySeriesNameAndUserID(seriesName, userID);
    }

    /**
     * Edit Series's name.
     *
     * @param seriesID the ID of the seires
     * @param seriesName the new name of the series
     * @param userID the ID of the user
     * @return If the edition is successful
     */
    public boolean editSeriesName(String seriesID, String seriesName, String userID){
        return repository.editSeriesName(seriesID, seriesName, userID);
    }

    /**
     * Handle event deletion by decrementing count of series associated with events. Deletes series with 0 events.
     *
     * @param event event which was deleted and reacted to
     */
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

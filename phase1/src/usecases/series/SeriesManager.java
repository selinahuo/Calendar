package usecases.series;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import entities.CalendarEvent;
import entities.Series;
import usecases.events.IEventManager;

class SeriesManager implements ISeriesManager {
    private ISeriesRepository repository;
    private IEventManager eventManager;

    SeriesManager(ISeriesRepository repository, IEventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    @Override
    public boolean createSeriesByCombiningEvents(String seriesName, String[] eventIDs, String userID) {
        Series newSeries = new Series(UUID.randomUUID().toString(), seriesName, eventIDs.length, userID);
        this.repository.saveSeries(newSeries);
        for (String id : eventIDs) {
            this.eventManager.editEventSeriesID(id, newSeries.getSeriesID());
        }
        return true;
    }

    @Override
    public boolean createSeriesFromEventFormula(String seriesName, GregorianCalendar start, GregorianCalendar end, String frequency, int numEvents, String userID) {
        Series newSeries = new Series(UUID.randomUUID().toString(), seriesName, numEvents, userID);
        ArrayList<GregorianCalendar[]> times = getTimes(start, end, frequency, numEvents);
        for (GregorianCalendar[] time : times) {
            CalendarEvent eventToCreate = new CalendarEvent(UUID.randomUUID().toString(), seriesName, time[0], time[1], "", userID, null, null, newSeries.getSeriesID(), null);
            this.eventManager.createEvent(eventToCreate);
        }
        this.repository.saveSeries(newSeries);
        return true;
    }

    private ArrayList<GregorianCalendar[]> getTimes(GregorianCalendar start, GregorianCalendar end, String frequency, int numEvents) {
        int daysToAdd;
        if (frequency == "d") {
            daysToAdd = 1;
        } else {
            daysToAdd = 7;
        }
        ArrayList<GregorianCalendar[]> times = new ArrayList<>();
        GregorianCalendar startTime = (GregorianCalendar) start.clone();
        GregorianCalendar endTime =  (GregorianCalendar) end.clone();
        int i = 0;
        while (i < numEvents) {
            GregorianCalendar startClone = (GregorianCalendar) startTime.clone();
            GregorianCalendar endClone = (GregorianCalendar) endTime.clone();
            GregorianCalendar[] timeArray = {startClone, endClone};
            times.add(timeArray);
            startTime.add(Calendar.DAY_OF_MONTH, daysToAdd);
            endTime.add(Calendar.DAY_OF_MONTH, daysToAdd);
            i++;
        }
        return times;
    }

    @Override
    public CalendarEvent[] getEventsBySeriesNameAndUserID(String seriesName, String userID) {
        Series series = this.repository.fetchSeriesByNameAndUserID(seriesName, userID);
        if (series == null) {
            return new CalendarEvent[0];
        }
        return this.eventManager.getEventsBySeriesIDAndUserID(series.getSeriesID(), userID);
    }

    @Override
    public Series getSeriesByIDAndUserID(String seriesID, String userID) {
        return this.repository.fetchSeriesByIDAndUserID(seriesID, userID);
    }
}

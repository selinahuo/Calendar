package usecases.series;

import entities.CalendarEvent;
import entities.Series;

import java.util.GregorianCalendar;

public interface ISeriesManager {
    boolean createSeriesByCombiningEvents(String seriesName, String[] eventIDs, String userID);
    boolean createSeriesFromEventFormula(String seriesName, GregorianCalendar start, GregorianCalendar end, String frequency, int numEvents, String userID);
    CalendarEvent[] getEventsBySeriesNameAndUserID(String seriesName, String userID);
    Series getSeriesByIDAndUserID(String seriesID, String userID);
}

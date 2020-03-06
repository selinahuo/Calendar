package dataaccess;

import entities.CalendarEvent;
import usecases.events.IEventRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

public class CSVEventRepository implements IEventRepository {

    private static final String pathToCsv = "CalenderEvent.csv";
    private static final String cvsSplitBy = ",";
    private static final String gregorianSplitBy = "-";
    private static final String arrListSplitBy = ";";


    /**
     * Save a CalendarEvent.
     * @param event the event to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean saveEvent(CalendarEvent event) {
        System.out.println(event.getName());
        return true;
    }

    /**
     * Fetch an CalendarEvent by its ID.
     *
     * @param id the ID to filter by.
     * @return the corresponding CalendarEvent or null if it does not exist
     */
    @Override
    public CalendarEvent fetchEventByID(String id){

        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(pathToCsv))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] eventInfo = line.split(cvsSplitBy);
                String eventID = eventInfo[0];
                String name = eventInfo[1];
                String[] startTime = eventInfo[2].split(gregorianSplitBy);
                GregorianCalendar start = new GregorianCalendar(Integer.parseInt(startTime[0]),
                        Integer.parseInt(startTime[1]), Integer.parseInt(startTime[2]), Integer.parseInt(startTime[3]),
                        Integer.parseInt(startTime[4]), Integer.parseInt(startTime[5]));
                String[] endTime = eventInfo[3].split(gregorianSplitBy);
                GregorianCalendar end = new GregorianCalendar(Integer.parseInt(endTime[0]),
                        Integer.parseInt(endTime[1]), Integer.parseInt(endTime[2]), Integer.parseInt(endTime[3]),
                        Integer.parseInt(endTime[4]), Integer.parseInt(endTime[5]));
                String location = eventInfo[4];
                String userID = eventInfo[5];
                ArrayList<String> tagIDs = (ArrayList<String>) Arrays.asList(eventInfo[6].split(arrListSplitBy));
                ArrayList<String> memoIDs = (ArrayList<String>) Arrays.asList(eventInfo[7].split(arrListSplitBy));
                String seriesID = eventInfo[8];
                String alertID = eventInfo[9];

                if(eventID.equals(id)){
                    CalendarEvent event = new CalendarEvent(eventID, name, start, end, location, userID, tagIDs, memoIDs,
                            seriesID, alertID);
                    br.close();
                    return event;
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Fetch all CalendarEvents that have an ID in a list of IDs and a matching name
     *
     * @param name events must have this name
     * @param userID  returned CalendarEvents must have an ID in this list
     * @return list of matching CalendarEvents
     */
    @Override
    public CalendarEvent[] fetchEventsByNameAndUserID(String name, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByDateAndUserID(GregorianCalendar before, GregorianCalendar after, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByDateBeforeAndUserID(GregorianCalendar before, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByDateAfterAndUserID(GregorianCalendar after, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventBySeriesIDAndUserID(String seriesID, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public boolean editEventSeriesID(String eventID, String newSeriesID) {
        return false;
    }

    @Override
    public CalendarEvent[] fetchEventsByTagIDAndUserID(String tagID, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByMemoIDAndUserID(String memoID, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent fetchEventByAlertIDAndUserID(String alertID, String userID) {
        return null;
    }
}

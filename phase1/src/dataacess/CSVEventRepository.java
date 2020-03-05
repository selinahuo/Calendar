package dataacess;

import entities.CalendarEvent;
import usecases.events.IEventRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class CSVEventRepository implements IEventRepository {

    static private String pathToCsv = "Event.csv";
    static private String cvsSplitBy = ",";

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

        HashMap<String,String> eventInfo = new HashMap<String,String>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(pathToCsv))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] event = line.split(cvsSplitBy);

                if(event[0] == id){
                    return
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
    public CalendarEvent[] fetchEventsByDateAndUserID(Date before, Date after, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByDateBeforeAndUserID(Date before, String userID) {
        return new CalendarEvent[0];
    }

    @Override
    public CalendarEvent[] fetchEventsByDateAfterAndUserID(Date after, String userID) {
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

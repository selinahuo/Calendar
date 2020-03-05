package dataacess;

import entities.Series;
import usecases.series.ISeriesRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVSeriesRepository implements ISeriesRepository {

    private static  String pathToCsv = "Series.csv";
    private final static String cvsSplitBy = ";";

    /**
     * Save a Series.
     *
     * @param series the Series to save
     * @return true if Series creation was successful, false otherwise
     */
    @Override
    public boolean saveSeries(Series series) {
        System.out.println("user creating");
        try{
            FileWriter fw = new FileWriter("Series.csv");
            String seriesID = series.getSeriesID();
            String seriesName = series.getName();
            String seriesEventCount = String.valueOf(series.getEventCount());
            String serisUserID = series.getUserID();
            String seriesInfo = seriesID + seriesName + seriesEventCount + serisUserID;
            fw.write(seriesInfo);
            fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Fetch a Series by its ID.
     *
     * @param seriesID the ID to filter by
     * @return the corresponding Series or null if it does not exist
     */
    @Override
    public Series fetchSeriesByID(String seriesID) {

        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(pathToCsv))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] event = line.split(cvsSplitBy);
                if (event[0].equals(seriesID)){
                    int eventCount = Integer.parseInt(event[2]);
                    br.close();
                    return new Series(event[0], event[1], eventCount, event[3]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fetch a Series that has an ID in the list of IDS with a matching name
     *
     * @param name name to match the Series by
     * @param userID  returned Series must have an ID in this list
     * @return a Series fitting the name and ID criteria
     */

    @Override
    public Series fetchSeriesByNameAndUserID(String name, String userID) {


        /**
         * Fetch a Series that has an ID in the list of IDs and contains the eventID
         *
         * @param eventID eventID of a calendar event that is part of the matching series
         * @param ids     returned Series must have an ID in this list
         * @return a Series fitting the eventID and ID criteria
         */

        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(pathToCsv))) {


            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] event = line.split(cvsSplitBy);
                if ((event[1].equals(name))& (event[3].equals(userID))){
                    int eventCount = Integer.parseInt(event[2]);
                    br.close();
                    return new Series(event[0], event[1], eventCount, event[3]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

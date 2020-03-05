package usecases.alerts;
import entities.Alert;

import java.util.GregorianCalendar;

/**
 * Provides a public interface for alert repository operations
 */
public interface IAlertRepository {
    /**
     * Saves an alert.
     * @param alert the alert to save
     * @return true if event creation was successful, false otherwise
     */
    boolean saveAlert(Alert alert);

    /**
     * Edit a Alert.
     * @param id the ID of the alert to edit
     * @param name the new name of the alert
     * @param start the new start time of the alert
     * @param end the new end time of the alert
     * @return true if series edit was successful,
     */
    boolean editAlert(String id, String name, GregorianCalendar start, GregorianCalendar end);


    /**
     * Fetch an Alert by their ID.
     * @param id the ID to filter by.
     * @return the corresponding Alert or null if it does not exist
     */
    Alert fetchAlertByID(String id);

    /**
     * Fetch a Alert by its Name.
     * @param name the name to filter by.
     * @return the corresponding alert or null if it does not exist
     */
    entities.Alert fetchAlertByName(String name);




}

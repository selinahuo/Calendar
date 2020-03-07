package usecases.alerts;
import entities.Alert;
import entities.FrequencyAlert;
import entities.IndividualAlert;

import java.util.ArrayList;
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
     *
     * @param ID
     * @param name
     * @param UserID
     * @return
     */
    boolean editIndividualAlert(String alertID, String name, GregorianCalendar newStart);
    boolean editFrequencyAlerts(String alertID, String name, ArrayList<GregorianCalendar> frequency);

    /**
     * switch the acknowledged to false
     * @param ID
     * @param UserID
     * @return true if acknowledge alert was successful, false otherwise
     */
    boolean  acknowledgeAlert(String ID, String UserID);


    /**
     * Fetch an Alert by their ID.
     * @param id the ID to filter by.
     * @return the corresponding Alert or null if it does not exist
     */
    Alert fetchAlertByID(String id);

    /**
     * Fetch a Alert by its Name.
     * @param name the name to filter by.
     * @param userID involves in this alert
     * @return the corresponding alert or null if it does not exist
     */
    Alert fetchAlertByNameAndUserID(String name, String userID);

    /**
     *
     * @param alertID
     * @param userID involves in this alert
     * @return
     */
    Alert fetchAlertByIDAndUserID(String alertID, String userID);

    IndividualAlert fetchIndividualAlertByID(String alertID);
    FrequencyAlert fetchFrequencyAlertByID(String alertID);



}

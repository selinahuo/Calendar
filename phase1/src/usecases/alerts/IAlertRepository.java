package usecases.alerts;
import entities.Alert;

import java.util.List;

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
     * @param alertID
     * @param name
     * @param newStart
     * @return
     */
//    boolean editIndividualAlert(String alertID, String name, GregorianCalendar newStart);

    /**
     *
     * @param alertID
     * @param name
     * @param frequency
     * @return
     */
//    boolean editFrequencyAlerts(String alertID, String name, ArrayList<GregorianCalendar> frequency);



    /**
     * Fetch an Alert by their ID.
     * @param id the ID to filter by.
     * @return the corresponding Alert or null if it does not exist
     */
//    Alert fetchAlertByID(String id);

    /**
     * Fetch a Alert by its Name.
     * @param name the name to filter by.
     * @param userID involves in this alert
     * @return the corresponding alert or null if it does not exist
     */
//    Alert fetchAlertByNameAndUserID(String name, String userID);

    /**
     *
     * @param alertID
     * @param userID involves in this alert
     * @return
     */
    Alert fetchAlertByIDAndUserID(String alertID, String userID);

//    IndividualAlert fetchIndividualAlertByID(String alertID);
//    FrequencyAlert fetchFrequencyAlertByID(String alertID);

    Alert[] fetchAlertByUserID(String userID);

    boolean editAlertAcknowledge(String alertID, List<Boolean> acknowledge);
}

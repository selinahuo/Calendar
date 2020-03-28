package usecases.alerts;
import entities.Alert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a public interface for alert repository operations
 */
public interface IAlertRepository {
    //write - Alerts

    /**
     * Saves an alert to the alert repository.
     *
     * @param alert the alert instance that is being saved
     */
    void saveAlert(Alert alert);

    //Fetch - singular- Alert

    /**
     * Returns an alert instance of the given alertID and userID.
     *
     * @param alertID the alertID of the desired alert
     * @param userID the userID of the user that is associated with the desired alert
     * @return the desired alert instance
     */
    Alert fetchAlertByIDAndUserID(String alertID, String userID);

    //Fetch - plural- Alerts

    /**
     * Returns a list of alerts that are associated with the user of the given userID.
     *
     * @param userID the userID of the user
     * @return a list of alerts of the user
     */
    ArrayList<Alert> fetchAlertByUserID(String userID);

    //Edit - Alert

    /**
     * Returns whether the modification of alert's name is successful.
     *
     * @param alertID the alertID of the alert instance that is being modified
     * @param name the new name of the alert
     * @param userID the userID of the user that is associated with this alert instance
     * @return true if modification is successful, else return false
     */
    boolean editAlertName(String alertID, String name, String userID);

    /**
     * Returns whether the modification of totalAcknowledged with the given boolean is successful.
     *
     * @param alertID the alertID of the alert instance that is under modification
     * @param totalAcknowledged a boolean representation of whether all notification time has been acknowledged.
     * @param userID the userID of the user that is associated with this alert
     * @return  true if modification is successful, else return false
     */
    boolean editTotalAcknowledged(String alertID, Boolean totalAcknowledged, String userID);

    /**
     * Return whether the modification of acknowledges is successful.
     *
     * @param alertID the alertID of the alert instance that is under modification
     * @param acknowledge the list of boolean representations of whether the notification times are acknowledged
     * @param userID the userID of the user that is associated with this user
     * @return true if modification is successful, else return false
     */
    boolean editAcknowledge(String alertID, ArrayList<Boolean> acknowledge, String userID);

    /**
     * Return whether the modification of notification times is successful.
     *
     * @param alertID the alertID of the alert instance that is under modification
     * @param times the list of notification times that this alert would notify the user
     * @param userID the userID of the user that would be notified
     * @return true if modification is successful, else return false
     */
    boolean editTimes(String alertID, ArrayList<LocalDateTime> times, String userID);

    //Delete - Alert

    /**
     * Return whether alert deletion is successful.
     *
     * @param alertID the alertID of the alert instance that is being deleted
     * @param userID the userID of the user that is associated with this alert
     * @return true if deletion is successful, else return false
     */
    boolean deleteAlert(String alertID, String userID);
}



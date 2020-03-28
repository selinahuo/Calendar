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
    void saveAlert(Alert alert);

    //Fetch - singular- Alert
    Alert fetchAlertByIDAndUserID(String alertID, String userID);
    //Fetch - plural- Alerts
    ArrayList<Alert> fetchAlertByUserID(String userID);

    //Edit - Alert
    boolean editAlertName(String alertID, String name, String userID);
    boolean editTotalAcknowledged(String alertID, Boolean totalAcknowledged, String userID);
    boolean editAcknowledge(String alertID, ArrayList<Boolean> acknowledge, String userID);
    boolean editTimes(String alertID, ArrayList<LocalDateTime> times, String userID);

    //Delete - Alert
    boolean deleteAlert(String alertID, String userID);
}



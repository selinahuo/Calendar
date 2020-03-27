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
    boolean saveAlert(Alert alert);

    //Fetch - singular- Alert
    Alert fetchAlertByIDAndUserID(String alertID, String userID);
    Alert fetchAlertByID(String alertID);
    //Fetch - plural- Alerts
    ArrayList<Alert> fetchAlertByUserID(String userID);

    //Edit - Alert
    boolean editAlertName(String alertID, String name, String newName, String userID);
    boolean editAlertID(String alertID, String newID, String userID);

    //Delete - Alert
    boolean deleteAlertByID(String alertID);
    boolean deleteAlertByIDAndUserID(String alertID, String userID);

    //AcknowledgeAlert
    boolean editAlertAcknowledge(String alertID, List<Boolean> acknowledge);
    boolean editAlertTimeAsIndividual(String alertID, LocalDateTime individualTime);
}



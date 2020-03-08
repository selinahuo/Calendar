package dataaccess;

import entities.Alert;
import usecases.alerts.IAlertRepository;

import java.util.ArrayList;
import java.util.List;

public class SerializableAlertRepository extends SerializableRepository<Alert> implements IAlertRepository {
    public SerializableAlertRepository() {
        super("alerts.ser");
    }

    /**
     * Saves an alert.
     *
     * @param alert the alert to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean saveAlert(Alert alert) {
        ArrayList<Alert> alerts = deserialize();
        alerts.add(alert);
        serialize(alerts);
        return true;
    }

    /**
     * @param alertID
     * @param userID  involves in this alert
     * @return
     */
    @Override
    public Alert fetchAlertByIDAndUserID(String alertID, String userID) {
        ArrayList<Alert> alerts = deserialize();
        for (Alert alert: alerts) {
            if (alert.getAlertID().equals(alertID) && alert.getUserID().equals(userID)) {
                return alert;
            }
        }
        return null;
    }

    @Override
    public Alert[] fetchAlertByUserID(String userID) {
        ArrayList<Alert> alerts = deserialize();
        List<Alert> alertList = new ArrayList<>();
        for (Alert alert: alerts) {
            if (alert.getUserID().equals(userID)) {
                alertList.add(alert);
            }
        }
        Alert[] alertArray  = new Alert[alertList.size()];
        alertList.toArray(alertArray);
        return alertArray;
    }

    @Override
    public boolean editAlertAcknowledge(String alertID, List<Boolean> acknowledge) {
        ArrayList<Alert> alerts = deserialize();
        for (Alert alert: alerts) {
            if (alert.getAlertID().equals(alertID)) {
                alert.setAcknowledge(acknowledge);
                serialize(alerts);
                return true;
            }
        }
        return false;
    }
}
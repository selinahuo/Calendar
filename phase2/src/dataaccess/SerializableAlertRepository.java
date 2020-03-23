package dataaccess;

import entities.Alert;
import usecases.alerts.IAlertRepository;

import java.util.ArrayList;
import java.util.List;

public class SerializableAlertRepository extends SerializableRepository<Alert> implements IAlertRepository {
    public SerializableAlertRepository() {
        super("alerts.ser");
    }

    @Override
    public boolean saveAlert(Alert alert) {
        ArrayList<Alert> alerts = deserialize();
        alerts.add(alert);
        serialize(alerts);
        return true;
    }

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
    public boolean editAlertName(String alertID, String name, String newName, String userID){
        ArrayList<Alert> alertsArr = deserialize();
        for (Alert alerts: alertsArr) {
            if (alerts.getAlertID().equals(alertID)) {
                alerts.setAlertName(newName);
            }
        }
        return true;
    }

    @Override
    public boolean editAlertID(String alertID, String newID, String userID){
        ArrayList<Alert> alertsArr = deserialize();
        for (Alert alerts: alertsArr) {
            if (alerts.getAlertID().equals(alertID)) {
                alerts.setAlertID(newID);
            }
        }
        return true;
    }

    @Override
    public boolean deleteAlertByEventID(String eventID){
        return false;
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
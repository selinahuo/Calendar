package dataaccess;

import entities.Alert;
import usecases.alerts.IAlertRepository;

import java.time.LocalDateTime;
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
        return fetchSingular((Alert alert) -> alert.getAlertID() != null
                && alert.getAlertID().equals(alertID)
                && alert.getUserID().equals(userID));
    }

    @Override
    public Alert fetchAlertByID(String alertID) {
        return fetchSingular((Alert alert) -> alert.getUserID() != null
                && alert.getAlertID().equals(alertID));
    }

    @Override
    public ArrayList<Alert> fetchAlertByUserID(String userID) {
        return fetchPlural((Alert alert) -> alert.getUserID() != null &&
                alert.getUserID().equals(userID));
    }

    @Override
    public boolean editAlertName(String alertID, String name, String newName, String userID){
        ArrayList<Alert> alertsArr = deserialize();
        for (Alert alerts: alertsArr) {
            if (alerts.getAlertID().equals(alertID)) {
                alerts.setAlertName(newName);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editAlertID(String alertID, String newID, String userID){
        ArrayList<Alert> alertsArr = deserialize();
        for (Alert alerts: alertsArr) {
            if (alerts.getAlertID().equals(alertID)) {
                alerts.setAlertID(newID);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAlertByEventID(String eventID){
        return false;
    }

    @Override
    public boolean deleteAlertByIDAndUserID(String alertID, String userID){
        return false; }

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

    @Override
    public boolean editAlertTimeAsIndividual(String alertID, LocalDateTime individualTime) {
        ArrayList<Alert> alertsArr = deserialize();
        for (Alert alerts: alertsArr) {
            if (alerts.getAlertID().equals(alertID)) {
                alerts.setIndividualTime(individualTime);
                return true;
            }
        }
        return false;
    }

}
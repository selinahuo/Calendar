package dataaccess;
import entities.Alert;
import usecases.alerts.IAlertRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;


class SerializableAlertRepository extends SerializableRepository<Alert> implements IAlertRepository {
    SerializableAlertRepository() {
        super("./alerts.ser");
    }

    @Override
    public void saveAlert(Alert alert) {
        saveItem(alert);
    }

    @Override
    public Alert fetchAlertByIDAndUserID(String alertID, String userID) {
        return fetchSingular((Alert a) -> a.getAlertID().equals(alertID) && a.getUserID().equals(userID));
    }

    @Override
    public ArrayList<Alert> fetchAlertByUserID(String userID) {
        return fetchPlural((Alert a) -> a.getUserID().equals(userID));
    }

    @Override
    public boolean editAlertName(String alertID, String name, String userID) {
        return editSingular(
                (Alert a) -> a.getAlertID().equals(alertID) && a.getUserID().equals(userID),
                (Alert a) -> a.setAlertName(name)
        );
    }

    @Override
    public boolean editTotalAcknowledged(String alertID, Boolean totalAcknowledged, String userID) {
        return editSingular(
                (Alert a) -> a.getAlertID().equals(alertID) && a.getUserID().equals(userID),
                (Alert a) -> a.setTotalAcknowledged(totalAcknowledged)
        );
    }

    @Override
    public boolean editAcknowledge(String alertID, ArrayList<Boolean> acknowledge, String userID) {
        return editSingular(
                (Alert a) -> a.getAlertID().equals(alertID) && a.getUserID().equals(userID),
                (Alert a) -> a.setAcknowledge(acknowledge)
        );
    }

    @Override
    public boolean editTimes(String alertID, ArrayList<LocalDateTime> times, String userID) {
        return editSingular(
                (Alert a) -> a.getAlertID().equals(alertID) && a.getUserID().equals(userID),
                (Alert a) -> a.setTimes(times)
        );
    }

    @Override
    public boolean deleteAlert(String alertID, String userID) {
        return deleteSingular(
                (Alert a) -> a.getAlertID().equals(alertID) && a.getUserID().equals(userID)
        );
    }
}
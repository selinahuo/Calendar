package usecases.alerts;

import entities.Alert;
import entities.Memo;

import java.util.Date;

public class AlertManager {
    private IAlertRepository repository;

    AlertManager(IAlertRepository repository){
        this.repository = repository;
    }

    /**
     * Create and save a Alert.
     * @param String alertID, String alertName, Date start, Date end
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean createAlert(String alertID, String alertName, Date start, Date end){
        Alert alert = Alert(String alertID, String alertName, Date start, Date end);
        this.repository.saveAlert(alert);
        return true;
        // how about false;
    }

    /**
     * Get a Alert by its name.
     * @param name the name to filter by.
     * @return the corresponding Alert or null if it does not exist
     */
    @Override
    public Memo getMemoByName(String name) {
        return null;
    }


}

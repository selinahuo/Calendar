package usecases.alerts;

import entities.Alert;
import entities.Memo;

public interface IAlertManager {
    /**
     * Create and save an alert.
     * @param alert the Alert to ave
     * @return true is alert creation was successful, false otherwise
     */
    boolean createAlert (Alert alert);

    /**
     * Get a alert by its name.
     * @param name the name to filter by.
     * @return the corresponding alert or null if it does not exist
     */
    Memo getAlertByName(String name);
}

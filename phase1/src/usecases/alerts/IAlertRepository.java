package usecases.alerts;
import entities.Alert;
import entities.Memo;

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
     * Fetch a Alert by its Name.
     * @param name the name to filter by.
     * @return the corresponding alert or null if it does not exist
     */
    entities.Alert fetchAlertByName(String name);

}

package usecases.alerts;

import entities.Alert;
import entities.Memo;
import entities.Series;
import entities.User;

import java.util.GregorianCalendar;

public interface IAlertManager {

    /**
     * Create and save a Alerts.
     * @param name name of the alert
     * @param start start time of the alert
     * @param  end end time of the alert
     * @return true if series creation was successful, false otherwise
     */
    boolean createIndividualAlert(String name, GregorianCalendar start, GregorianCalendar end);

    /**
     * Create and save a Alerts.
     * @param name name of the alert
     * @param start start time of the alert
     * @param  end end time of the alert
     * @param  frequency the frequency that this alert is repeating
     * @return true if series creation was successful, false otherwise
     */
    boolean createFrequencylAlert(String name, GregorianCalendar start, GregorianCalendar end,GregorianCalendar frequency );


    /**
     * Edit a alert with matching ID
     * @param alertID ID of the alert to edit
     * @param name new name of the alert
     * @param newStart new start time of the series
     * @param newEnd new end time of the series
     * @return true if alerts edit was successful, false otherwise
     */
    boolean editAlerts(String alertID, String name, GregorianCalendar newStart, GregorianCalendar newEnd);

    /**
     * Edit a alert with matching ID
     * @param alertID ID of the alert to edit
     * @param name new name of the alert
     * @param newStart new start time of the alert
     * @param newEnd new end time of the alert
     * @param newFrequency new frequency of the alert
     * @return true if alerts edit was successful, false otherwise
     */
    boolean editFrequencyAlerts(String alertID, String name, GregorianCalendar newStart, GregorianCalendar newEnd,
                                GregorianCalendar[] newFrequency);

    /**
     * Get all Alerts that have an ID in a list of IDs
     * @param id id of the desired Alert
     * @return Alert that match the id
     */
    Alert getAlertByIDs(String id);

    /**
     * Get a Alert that has an ID  and a matching name
     * @param name Alert must match this name
     * @param id id of the alert.
     * @return a matching Alert
     */
    Alert getAlertByNameAndIDs(String name, String id);

}

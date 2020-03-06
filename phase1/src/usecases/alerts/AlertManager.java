package usecases.alerts;

import entities.Alert;
import usecases.events.IEventManager;

import java.util.GregorianCalendar;

class AlertManager implements IAlertManager {
    private IAlertRepository repository;
    private IAlertManager alertManager;

    public AlertManager(IAlertRepository repository, IAlertManager alertManager){
        this.repository = repository;
        this.alertManager = alertManager;
    }

    /**
     * Create and save an alert.
     * @param name,start,end time
     * @return true is alert creation was successful, false otherwise
     */
    @Override
    public boolean createIndividualAlert (String name, GregorianCalendar start, GregorianCalendar end){
        return false;
    }

    /**
     * Create and save a Alerts.
     * @param name name of the alert
     * @param start start time of the alert
     * @param  end end time of the alert
     * @param  frequency the frequency that this alert is repeating
     * @return true if series creation was successful, false otherwise
     */
    @Override
    public boolean createFrequencylAlert(String name, GregorianCalendar start, GregorianCalendar end,GregorianCalendar frequency ){
        return false;
    }

    /**
     * Edit a alert with matching ID
     * @param alertID ID of the alert to edit
     * @param name new name of the alert
     * @param newStart new start time of the series
     * @param newEnd new end time of the series
     * @return true if alerts edit was successful, false otherwise
     */
    @Override
    public boolean editAlerts(String alertID, String name, GregorianCalendar newStart, GregorianCalendar newEnd){
        return false;
    }

    /**
     * Edit a alert with matching ID
     * @param alertID ID of the alert to edit
     * @param name new name of the alert
     * @param newStart new start time of the alert
     * @param newEnd new end time of the alert
     * @param newFrequency new frequency of the alert
     * @return true if alerts edit was successful, false otherwise
     */
    @Override
    public boolean editFrequencyAlerts(String alertID, String name, GregorianCalendar newStart, GregorianCalendar newEnd,
                                GregorianCalendar[] newFrequency){
        return false;
    }

    /**
     * Get all Alerts that have an ID in a list of IDs
     * @param id id of the desired Alert
     * @return Alert that match the id
     */
    //TODO implement this method
    @Override
    public Alert getAlertByIDs(String id){
        return null;
    }

    /**
     * Get a Alert that has an ID  and a matching name
     * @param name Alert must match this name
     * @param id id of the alert.
     * @return a matching Alert
     */
    public Alert getAlertByNameAndIDs(String name, String id) {
        return null;
    }

}

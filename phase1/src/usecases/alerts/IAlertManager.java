package usecases.alerts;

import entities.Alert;

import entities.CalendarEvent;

import java.util.GregorianCalendar;

public interface IAlertManager {

    /**
     *
     * @param alertID the id of this alert
     * @param alertName the name of this alert
     * @param start start time
     * @return ttrue if event creation was successful, false otherwise
     */
    boolean createIndividualAlert(String alertID, String alertName, GregorianCalendar start);

    /**
     *
     * @param alertID the id of this alert
     * @param alertName the name of this alert
     * @param frequency the rate of which this is repeating
     * @return true if event creation was successful, false otherwise
     */
    public boolean createFrequencylAlert(String alertID, String alertName, GregorianCalendar[] frequency);

    public boolean createFrequencylAlertByEvent(String alertID, String alertName,  GregorianCalendar end, String eventId);

//    /**
//     * Edit a alert with matching ID
//     * @param alertID ID of the alert to edit
//     * @param name new name of the alert
//     * @param newStart new start time of the series
//     * @param newEnd new end time of the series
//     * @return true if alerts edit was successful, false otherwise
//     */
//    boolean editAlerts(String alertID, String name, GregorianCalendar newStart, GregorianCalendar newEnd);
//
//    /**
//     * Edit a alert with matching ID
//     * @param alertID ID of the alert to edit
//     * @param name new name of the alert
//     * @param newStart new start time of the alert
//     * @param newEnd new end time of the alert
//     * @param newFrequency new frequency of the alert
//     * @return true if alerts edit was successful, false otherwise
//     */
//    boolean editFrequencyAlerts(String alertID, String name, GregorianCalendar newStart, GregorianCalendar newEnd,
//                                GregorianCalendar[] newFrequency);

    /**
     * Get all Alerts that have an ID in a list of IDs
     * @param id id of the desired Alert
     * @return Alert that match the id
     */
    Alert getAlertByIDs(String id);

    /**
     * Get a Alert that has an ID  and a matching name
     * @param name Alert must match this name
     * @param id id of this alert
     * @return a matching Alert
     */
    Alert getAlertByNameAndId(String name, String id);

    CalendarEvent getEventByAlertNameAndUserID(String alertName, String userID);


}

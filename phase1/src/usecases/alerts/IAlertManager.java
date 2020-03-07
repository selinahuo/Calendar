package usecases.alerts;

import entities.Alert;

import entities.CalendarEvent;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IAlertManager {

    /**
     *
     * @param eventID
     * @param name
     * @param startTime
     * @param userID
     * @return
     */
    public Boolean createIndividualAlertOnEvent(String eventID, String name, GregorianCalendar startTime, String userID);

    /**
     *
     * @param eventID
     * @param name
     * @param frequency
     * @param userID
     * @return
     */
    public boolean createFrequencyAlert(String eventID, String name, ArrayList<GregorianCalendar> frequency, String userID);

    public boolean createFrequencyAlertOnEvent(String eventID, String name, GregorianCalendar first, String userID);

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

    public void acknowledgeIndividualAlert(String alertID, String userID);
    public void acknowledgeFrequencyAlert(String alertID, String userID);
    public ArrayList<Alert> getOverdueAlertsAfterDate(GregorianCalendar date, String userID);

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

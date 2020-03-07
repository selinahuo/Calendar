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

    public boolean  editFrequencyAlerts(String alertID, String name,ArrayList<GregorianCalendar> frequency);


    public boolean  editIndividualAlert(String alertID, String name, GregorianCalendar newStart);

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

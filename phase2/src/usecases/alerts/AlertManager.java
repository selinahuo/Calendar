package usecases.alerts;

import entities.Alert;
import entities.CalendarEvent;
import usecases.events.IEventDeletionObserver;
import java.time.LocalDateTime;
import usecases.events.EventManager;

import java.util.ArrayList;
import java.util.UUID;

public class AlertManager implements IEventDeletionObserver {
    private IAlertRepository alertRepository;
    private EventManager eventManager;

    public AlertManager(IAlertRepository repository, EventManager eventManager){
        this.alertRepository = repository;
        this.eventManager = eventManager;
    }

    /**
     * Create an Individual Alert
     * @param eventID the eventID of the event associated with this alert
     * @param alertName the name of this alert
     * @param userID the user that this alert will notify
     * @param start the alert time of this Individual alert
     * @return alert
     */
    public String createIndividualAlert(String eventID, String alertName, LocalDateTime start, String userID) {
        // create an alert
        Alert alert = new Alert(alertName, userID, start);
        if (eventManager.editAlertID(eventID, alert.getAlertID(), userID)) {
            alertRepository.saveAlert(alert);
            return alert.getAlertID();
        }
        return null;
    }


    /**
     * Create a Frequency Alert
     * @param eventID the eventID of the event associated with this alert
     * @param alertName the name of this alert
     * @param userID the user that this alert will notify
     * @param startTime the start time of this alert
     * @param frequency the frequency this alert is repeating.
     * @return alert
     */
    private Alert createFrequencyAlert(String eventID, String alertName, String userID, LocalDateTime startTime, String frequency) {
        // Get event
        CalendarEvent event = this.eventManager.getEventByIDAndOwnerID(eventID, userID);
        if (event == null) {
            return null;
        }
        // organize alertTimes
        LocalDateTime end = event.getEnd();
        ArrayList<LocalDateTime> alertTimes = new ArrayList<>();
        LocalDateTime start = LocalDateTime.from(startTime);

        while (start.isBefore(end)) {
            LocalDateTime currTime = LocalDateTime.from(start);
            alertTimes.add(currTime);
            if (frequency.equals("d")){
                start = start.plusDays(1);
            }
            else if (frequency.equals("w")) {
                start = start.plusDays(7);
            }
            else {
                start = start.plusHours(1);
            }
        }
        //create the alert
        Alert alert = new Alert(alertName, userID, alertTimes);
        return alert;
    }

    /**
     * attach this alert to the associated event
     * @param eventID the ID of the event that is associated with this alert
     * @param alertName the name of this alert
     * @param userID the user that will be modified
     * @param startTime the start time of this alert
     * @param frequency the frequency of which the alert is repeating
     * @return True is successful
     */
    public boolean createFrequencyAlertOnEvent(String eventID, String alertName, String userID, LocalDateTime startTime, String frequency) {
        // create frequency alert
        Alert alert = createFrequencyAlert(eventID, alertName, userID, startTime, frequency);
        if (alert == null) {
            return false;
        }
        //update the event's alertID
        eventManager.editAlertID(eventID, alert.getAlertID(), userID);
        alertRepository.saveAlert(alert);
        return true;
    }

    /**
     * Acknowledge the alert time has passed
     * @param alertID the ID of this alert
     * @param userID the user associated with this alert
     */
    public boolean acknowledgeAlert(String alertID, String userID) {
        Alert alert = this.alertRepository.fetchAlertByIDAndUserID(alertID, userID);
        if (alert != null) {
            alert.acknowledge();
            alertRepository.editAcknowledge(alertID, alert.getAcknowledge(), userID);
            alertRepository.editTotalAcknowledged(alertID, alert.getTotalAcknowledged(), userID);
            return true;
        }
        return false;
    }

    /**
     * acknowledge alert if passed date
     * @param date time
     * @param userID the user using this calendar
     * @return a list of alert that is overdue.
     */
    public ArrayList<Alert> getOverdueAlertsAfterDate(LocalDateTime date, String userID){
        ArrayList<Alert> alerts = this.alertRepository.fetchAlertByUserID(userID);
        ArrayList<Alert> alertsArr = new ArrayList<Alert>();
        for (Alert alert: alerts) {
            LocalDateTime nextRing = alert.getNextRing();
            if (nextRing != null && nextRing.isBefore(date)) {
                alertsArr.add(alert);
            }
        }
        return  alertsArr;
    }

    public boolean deleteAlertByIDAndUserID(String alertID, String userID) {
        if(alertRepository.deleteAlert(alertID, userID)) {
            // update the event's alertID
            this.eventManager.getEventByAlertIDAndOwnerID(alertID, userID).setAlertID("");
            return true;
        }
        return false;
    }

    // get - singular alert
    public Alert getAlertByIDAndUserID(String alertID, String userID) {
        return alertRepository.fetchAlertByIDAndUserID(alertID, userID);
    }

    // get - plural alerts

    public ArrayList<Alert> getAlertByUserID(String userID) {
        return alertRepository.fetchAlertByUserID(userID);
    }

    // edit - Alert
    public boolean editAlertName(String alertID, String name, String userID){
        return alertRepository.editAlertName(alertID, name, userID);
    }

    public boolean editAlertTimeAsIndividual(String alertID, LocalDateTime time, String userID) {
        if (alertRepository.editTotalAcknowledged(alertID, false, userID)) {
            ArrayList<Boolean> acknowledge = new ArrayList<>();
            acknowledge.add(false);
            alertRepository.editAcknowledge(alertID, acknowledge, userID);
            ArrayList<LocalDateTime> times = new ArrayList<>();
            times.add(time);
            alertRepository.editTimes(alertID, times, userID);
            return true;
        }
        return false;
    }

    // delete alert
    @Override
    public void handleEventDeletion(CalendarEvent event) {
        alertRepository.deleteAlert(event.getAlertID(), event.getUserID());
    }
}

package usecases.alerts;

import entities.Alert;
import entities.CalendarEvent;
import usecases.events.IEventDeletionObserver;
import java.time.LocalDateTime;
import usecases.events.EventManager;

import java.util.ArrayList;
import java.util.UUID;

class AlertManager implements IEventDeletionObserver {
    private IAlertRepository alertRepository;
    private EventManager eventManager;

    public AlertManager(IAlertRepository repository, EventManager eventManager){
        this.alertRepository = repository;
        this.eventManager = eventManager;
    }

    /**
     * generate an alertID
     */
    private String generateAlertID(){
        return UUID.randomUUID().toString();
    }

    /**
     * Create an Individual Alert
     * @param eventID the eventID of the event associated with this alert
     * @param alertName the name of this alert
     * @param userID the user that this alert will notify
     * @param start the alert time of this Individual alert
     * @return alert
     */
    private Alert createIndividualAlert(String eventID, String alertName, String userID, LocalDateTime start) {
        // generate alertID
        String alertID = generateAlertID();
        // create an alert
        Alert alert = new Alert(alertID, alertName, userID, start);
        return alert;
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
    private Alert createFrequencyAlert(String eventID, String alertName, String userID, LocalDateTime startTime, String frequency){
        // generate alertID
        String alertID = generateAlertID();
        // organize alertTimes
        LocalDateTime end = this.eventManager.getEventByIDAndUserID(eventID, userID).getEnd();
        ArrayList<LocalDateTime> alertTimes = new ArrayList<>();
        LocalDateTime start = (LocalDateTime) startTime.plusHours(0);

        while (start.isBefore(end)) {
            LocalDateTime currTime = start.plusHours(0);
            alertTimes.add(currTime);
            if (frequency.equals("d")){
                start.plusDays(1);
            }
            else if (frequency.equals("w")) {
                start.plusDays(7);
            }
            else {
                start.plusHours(1);
            }
        }
        //create the alert
        Alert alert = new Alert(alertID, alertName, userID,alertTimes);
        return alert;
    }

    /**
     * attach this alert to the associated event
     * @param eventID the ID of the event that is associated with this alert
     * @param alertName the name of this alert
     * @param userID the user that will be modified
     * @param start the alert time of the notification
     * @return True is successful
     */
    public boolean createIndividualAlertOnEvent(String eventID, String alertName, LocalDateTime start, String userID) {
        //create individual alert
        Alert alert = createIndividualAlert(eventID, alertName, userID, start);
        //update the event's alertID
        this.eventManager.getEventByIDAndUserID(eventID, userID).setAlertID(alert.getAlertID());
        return this.alertRepository.saveAlert(alert);
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
        //update the event's alertID
        this.eventManager.getEventByIDAndUserID(eventID, userID).setAlertID(alert.getAlertID());
        return this.alertRepository.saveAlert(alert);
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
            return this.alertRepository.editAlertAcknowledge(alertID, alert.getAcknowledge());
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

    // delete alert
    @Override
    public void handleEventDeletion(String eventID) {
        this.alertRepository.deleteAlertByEventID(eventID);
    }

    public boolean deleteAlertByIDAndUserID(String alertID, String userID) {
        if(this.alertRepository.deleteAlertByIDAndUserID(alertID, userID)) {
            // update the event's alertID
            this.eventManager.getEventByAlertIDAndOwnerID(alertID, userID).setAlertID(null);
            return true;
        }
        return false;
    }

    // get - singular alert
    public Alert getAlertByIDAndUserID(String alertID, String userID) {
        return this.alertRepository.fetchAlertByIDAndUserID(alertID, userID);
    }

    // get - plural alerts

    public ArrayList<Alert> getAlertByUserID(String userID) {
        return alertRepository.fetchAlertByUserID(userID);
    }

    // edit - Alert

    public boolean editAlertName(String alertID, String name, String newName, String userID){
     return this.alertRepository.editAlertName(alertID, name, newName, userID);
    }

    public boolean editAlertTimeAsIndividual(String alertID, LocalDateTime individualTime) {
        return this.alertRepository.editAlertTimeAsIndividual(alertID, individualTime);
    }

    public boolean editAlertTimeAsFrequency(String eventID, String userID, LocalDateTime start, String frequency) {
        // the updated alert would have the original alert's name
        String originalAlertID = this.eventManager.getEventByIDAndUserID(eventID, userID).getAlertID();
        String originalAlertName = this.alertRepository.fetchAlertByID(originalAlertID).getAlertName();
        // the updated alert would have the original alert's userID
        String originalUserID = this.alertRepository.fetchAlertByID(originalAlertID).getUserID();
        // create a new frequency alert and attach to the associated event.
        return createIndividualAlertOnEvent(eventID, originalAlertName, start, originalUserID);
    }


//    @Override
//    public boolean editAlert(String name, String alertID, GregorianCalendar alertTime){
//        Alert alert = this.alertRepository.fetchAlertByNameAndUserID(name, alertID);
//        for(GregorianCalendar time: alert.getTimes()){
//            if ()
//        }
//    }

//    /**
//     * For Phase II
//     * @param eventID
//     * @param name
//     * @param first
//     * @param userID
//     * @return
//     */
//    @Override
//    public boolean createFrequencyAlertOnEvent(String eventID, String name, GregorianCalendar first, String userID) {
//        //generate alertID
//        String alertID = generateAlertID();
//        //edit event alertID
//        this.eventManager.getEventByID(eventID).setAlertID(alertID);
//        // find the frequency of this event and create alert
//            // this alert is attached to a single event
//        if (this.eventManager.getEventByID(eventID).getSeriesID() == null) {
//            ArrayList<GregorianCalendar> frequency = new ArrayList<GregorianCalendar>();
//            frequency.add(this.eventManager.getEventByID(eventID).getStart());
//            Alert alert = new FrequencyAlert(alertID,name,frequency,userID );
//            return this.alertRepository.saveAlert(alert);
//        }
//            // this alert is attached to a series of event
//        else {
//            String seriesID = this.eventManager.getEventByID(eventID).getSeriesID();
//            CalendarEvent[] allEvents= this.eventManager.getEventsBySeriesIDAndUserID(seriesID, userID);
//            ArrayList<GregorianCalendar> frequency = new ArrayList<GregorianCalendar>();
//            for (CalendarEvent allEvent : allEvents) {
//                frequency.add(allEvent.getStart());
//            }
//            Alert alert = new FrequencyAlert(alertID,name,frequency,userID );
//            return this.alertRepository.saveAlert(alert);
//        }
//    }

//    @Override
//    public boolean editFrequencyAlerts(String alertID, String name,ArrayList<GregorianCalendar> frequency){
//        FrequencyAlert alert = getFrequencyAlertByID(alertID);
//        alert.setAlertName(name);
//        alert.setTimes(frequency);
//        return this.alertRepository.editFrequencyAlerts(alertID, name, frequency);
//    }

//    /**
//     * Get all Alerts that have an ID in a list of IDs
//     * @param id id of the desired Alert
//     * @return Alert that match the id
//     */
//    @Override
//    public Alert getAlertByIDs(String id){
//        return this.alertRepository.fetchAlertByID(id);
//    }

//    /**
//     * Get a Alert that has an ID  and a matching name
//     * @param name Alert must match this name
//     * @param id id of this alert
//     * @return a matching Alert
//     */
//    @Override
//    public Alert getAlertByNameAndId(String name, String id) {
//        return this.alertRepository.fetchAlertByNameAndUserID(name, id);
//    }

//    @Override
//    public CalendarEvent getEventByAlertNameAndUserID(String alertName, String userID) {
//        Alert alert = this.alertRepository.fetchAlertByNameAndUserID(alertName, userID);
//        if (alert == null) {
//            return null;
//        }
//       return this.eventManager.getEventByAlertIDAndUserID(alert.getAlertID(), userID);
//    }

//    public IndividualAlert getIndividualAlertByID(String alertID){
//        return this.alertRepository.fetchIndividualAlertByID(alertID);
//    }
//
//    public FrequencyAlert getFrequencyAlertByID(String alertID){
//        return this.alertRepository.fetchFrequencyAlertByID(alertID);
//    }


}

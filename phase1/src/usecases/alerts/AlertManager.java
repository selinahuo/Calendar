package usecases.alerts;

import entities.Alert;
import entities.CalendarEvent;
import entities.FrequencyAlert;
import entities.IndividualAlert;
import usecases.events.IEventManager;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.UUID;

class AlertManager implements IAlertManager {
    private IAlertRepository alertRepository;
    private IAlertManager alertManager;
    private IEventManager eventManager;

    public AlertManager(IAlertRepository repository, IAlertManager alertManager, IEventManager eventManager){
        this.alertRepository = repository;
        this.alertManager = alertManager;
        this.eventManager = eventManager;
    }

    /**
     * generate an alertID
     */
    private String generateAlertID(){
        return UUID.randomUUID().toString();
    }

    /**
     *
     * @param eventID
     * @param name
     * @param startTime
     * @param userID
     * @return
     */
    @Override
    public Boolean createIndividualAlertOnEvent(String eventID, String name, GregorianCalendar startTime, String userID){
        //create individual alert
        String alertID = generateAlertID();
        Alert alert = new IndividualAlert(alertID, name, startTime, userID);
        //update the event's alertID
        this.eventManager.getEventByID(eventID).setAlertID(alertID);
        return this.alertRepository.saveAlert(alert);
    }

    /**
     *
     * @param eventID
     * @param name
     * @param frequency
     * @param userID
     * @return
     */
    @Override
    public boolean createFrequencylAlert(String eventID, String name, GregorianCalendar[] frequency, String userID){
        //create the alert
        String alertID = generateAlertID();
        Alert alert = new FrequencyAlert(alertID, name, frequency, userID);
        //update the event's alertID
        this.eventManager.getEventByID(eventID).setAlertID(alertID);
        return this.alertRepository.saveAlert(alert);
    }

    /**
     *
     * @param eventID
     * @param name
     * @param first
     * @param userID
     * @return
     */
    @Override
    public boolean createFrequencyAlertOnEvent(String eventID, String name, GregorianCalendar first, String userID) {
        //generate alertID
        String alertID = generateAlertID();
        //edit event alertID
        this.eventManager.getEventByID(eventID).setAlertID(alertID);
        // find the frequency of this event and create alert
            // this alert is attached to a single event
        if (this.eventManager.getEventByID(eventID).getSeriesID() == null) {
            GregorianCalendar[] frequency = {this.eventManager.getEventByID(eventID).getStart()};
            Alert alert = new FrequencyAlert(alertID,name,frequency,userID );
            return this.alertRepository.saveAlert(alert);
        }
            // this alert is attached to a series of event
        else {
            String seriesID = this.eventManager.getEventByID(eventID).getSeriesID();
            CalendarEvent[] allEvents= this.eventManager.getEventsBySeriesIDAndUserID(seriesID, userID);
            GregorianCalendar[] frequency = new GregorianCalendar[allEvents.length];
            for(int i = 0; i < allEvents.length; i++) {
                frequency[i] = allEvents[i].getStart();
            }
            Alert alert = new FrequencyAlert(alertID,name,frequency,userID );
            return this.alertRepository.saveAlert(alert);
        }
    }

    public Alert acknowledgeAlert(alertID, String userID) {

    }
    // get the alert and acknowledge it

//    /**
//     * Edit a alert with matching ID
//     * @param alertID ID of the alert to edit
//     * @param name new name of the alert
//     * @param newStart new start time of the series
//     * @param newEnd new end time of the series
//     * @return true if alerts edit was successful, false otherwise
//     */
//    @Override
//    public boolean editAlerts(String alertID, String name, GregorianCalendar newStart, GregorianCalendar newEnd){
//        return false;
//    }

//    /**
//     * Edit a alert with matching ID
//     * @param alertID ID of the alert to edit
//     * @param name new name of the alert
//     * @param newStart new start time of the alert
//     * @param newEnd new end time of the alert
//     * @param newFrequency new frequency of the alert
//     * @return true if alerts edit was successful, false otherwise
//     */
//    @Override
//    public boolean editFrequencyAlerts(String alertID, String name, GregorianCalendar newStart, GregorianCalendar newEnd,
//                                GregorianCalendar[] newFrequency){
//        return false;
//    }

    /**
     * Get all Alerts that have an ID in a list of IDs
     * @param id id of the desired Alert
     * @return Alert that match the id
     */
    @Override
    public Alert getAlertByIDs(String id){
        return this.alertRepository.fetchAlertByID(id);
    }

    /**
     * Get a Alert that has an ID  and a matching name
     * @param name Alert must match this name
     * @param id id of this alert
     * @return a matching Alert
     */
    @Override
    public Alert getAlertByNameAndId(String name, String id) {
        return this.alertRepository.fetchAlertByNameAndUserID(name, id);
    }

    @Override
    public CalendarEvent getEventByAlertNameAndUserID(String alertName, String userID) {
        Alert alert = this.alertRepository.fetchAlertByNameAndUserID(alertName, userID);
        if (alert == null) {
            return null;
        }
       return this.eventManager.getEventByAlertIDAndUserID(alert.getAlertID(), userID);
    }



}

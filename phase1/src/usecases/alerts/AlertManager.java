package usecases.alerts;

import entities.Alert;
import entities.CalendarEvent;
import entities.FrequencyAlert;
import entities.IndividualAlert;
import usecases.events.IEventManager;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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
     * @param alertID the ID of this alert
     * @param alertName the name of this alert
     * @param start start time of the alert
     * @param  end end time of the alert
     * @return true if alert creation was successful, false otherwise.
     */
    @Override
    public boolean createIndividualAlert (String alertID, String alertName, GregorianCalendar start, GregorianCalendar end){
        Alert alert = new IndividualAlert( alertID, alertName, start, end);
        this.alertRepository.saveAlert(alert);
        return true;
    }

    /**
     *
     * @param alertID the id of this alert
     * @param alertName the name of this alert
     * @param start start time
     * @param end end time
     * @param frequency the rate of which this is repeating
     * @return true if alert creation was successful, false otherwise.
     */
    @Override
    public boolean createFrequencylAlert(String alertID, String alertName, GregorianCalendar start, GregorianCalendar end, GregorianCalendar[] frequency){
        Alert alert = new FrequencyAlert(alertID, alertName, start, end, frequency);
        this.alertRepository.saveAlert(alert);
        return true;
    }

    @Override
    public boolean createFrequencylAlertByEvent(String alertID, String alertName,  GregorianCalendar end, String eventId) {
        // this alert is attached to a single event
        if (this.eventManager.getEventByID(eventId).getSeriesID() == null) {
            GregorianCalendar alertTime = this.eventManager.getEventByID(eventId).getStart();
            GregorianCalendar[] frequency = {alertTime};
            Alert alert = new FrequencyAlert(alertID,alertName,alertTime,end, frequency );
            this.alertRepository.saveAlert(alert);
        }
        // i.e. this alert will notify user at the start of a series of events, i.e. every math lecture.
        else {
            String seriesID = this.eventManager.getEventByID(eventId).getSeriesID();
            String userID = this.eventManager.getEventByID(eventId).getUserID();
            CalendarEvent[] allEvents= this.eventManager.getEventsBySeriesIDAndUserID(seriesID, userID);
            GregorianCalendar[] frequency = new GregorianCalendar[allEvents.length];
            for(int i = 0; i < allEvents.length; i++) {
                frequency[i] = allEvents[i].getStart();
            }
            Alert alert = new FrequencyAlert(alertID, alertName, frequency[0], end, frequency );
            this.alertRepository.saveAlert(alert);
        }
        return true;
    }

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

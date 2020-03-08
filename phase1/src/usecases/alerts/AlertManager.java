package usecases.alerts;

import entities.Alert;

import usecases.events.IEventManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

class AlertManager implements IAlertManager {
    private IAlertRepository alertRepository;
    private IEventManager eventManager;

    public AlertManager(IAlertRepository repository, IEventManager eventManager){
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
     *
     * @param eventID
     * @param alertName
     * @param userID
     * @param start
     * @return
     */
    private Alert createIndividualAlert(String eventID, String alertName, String userID, GregorianCalendar start) {
        // generate alertID
        String alertID = generateAlertID();
        // create an alert
        Alert alert = new Alert(alertID, alertName, userID, start);
        return alert;
    }


    /**
     *
     * @param eventID
     * @param alertName
     * @param userID
     * @param startTime
     * @param frequency
     * @return
     */
    private Alert createFrequencyAlert(String eventID, String alertName, String userID, GregorianCalendar startTime, String frequency){
        // generate alertID
        String alertID = generateAlertID();
        // organize alertTimes
        GregorianCalendar end = this.eventManager.getEventByID(eventID).getEnd();
        ArrayList<GregorianCalendar> alertTimes = new ArrayList<>();
        GregorianCalendar start = (GregorianCalendar) startTime.clone();

        while (start.before(end)) {
            GregorianCalendar currTime = (GregorianCalendar) start.clone();
            alertTimes.add(currTime);
            if (frequency.equals("d")){
                start.add(Calendar.DAY_OF_MONTH, 1 );
            }
            else if (frequency.equals("w")) {
                start.add(Calendar.DAY_OF_MONTH, 7 );
            }
            else {
                start.add(Calendar.HOUR_OF_DAY, 1);
            }
        }
        //create the alert
        Alert alert = new Alert(alertID, alertName, userID,alertTimes);
        return alert;
    }

    /**
     *
     * @param eventID
     * @param alertName
     * @param userID
     * @param start
     * @return
     */
    @Override
    public boolean createIndividualAlertOnEvent(String eventID, String alertName, GregorianCalendar start, String userID) {
        //create individual alert
        Alert alert = createIndividualAlert(eventID, alertName, userID, start);
        //update the event's alertID
        this.eventManager.getEventByID(eventID).setAlertID(alert.getAlertID());
        return this.alertRepository.saveAlert(alert);
    }

    /**
     *
     * @param eventID
     * @param alertName
     * @param userID
     * @param startTime
     * @param frequency
     * @return
     */
    @Override
    public boolean createFrequencyAlertOnEvent(String eventID, String alertName, String userID, GregorianCalendar startTime, String frequency) {
        // create frequency alert
        Alert alert = createFrequencyAlert(eventID, alertName, userID, startTime, frequency);
        //update the event's alertID
        this.eventManager.getEventByID(eventID).setAlertID(alert.getAlertID());
        return this.alertRepository.saveAlert(alert);
    }

    /**
     *
     * @param alertID
     * @param userID
     */
    @Override
    public boolean acknowledgeAlert(String alertID, String userID) {
        Alert alert = this.alertRepository.fetchAlertByIDAndUserID(alertID, userID);
        if (alert != null) {
            alert.acknowledge();
            return this.alertRepository.editAlertAcknowledge(alertID, alert.getAcknowledge());
        }
        return true;
    }

    /**
     *
     * @param date
     * @param userID
     * @return
     */
    @Override
    public ArrayList<Alert> getOverdueAlertsAfterDate(GregorianCalendar date, String userID){
        Alert[] alerts = this.alertRepository.fetchAlertByUserID(userID);
        ArrayList<Alert> alertsArr = new ArrayList<Alert>();
        for (Alert alert: alerts) {
            GregorianCalendar nextRing = alert.getNextRing();
            if (nextRing != null && nextRing.before(date)) {
                alertsArr.add(alert);
            }
        }
        return  alertsArr;
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

    /**
     * Get all Alerts that have an ID in a list of IDs
     * @param id id of the desired Alert
     * @return Alert that match the id
     */
//    @Override
//    public Alert getAlertByIDs(String id){
//        return this.alertRepository.fetchAlertByID(id);
//    }

    /**
     * Get a Alert that has an ID  and a matching name
     * @param name Alert must match this name
     * @param id id of this alert
     * @return a matching Alert
     */
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

    @Override
    public Alert getAlertByIDAndUserID(String alertID, String userID) {
        return this.alertRepository.fetchAlertByIDAndUserID(alertID, userID);
    }

}

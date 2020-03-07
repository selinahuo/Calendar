package usecases.alerts;

import entities.Alert;
import entities.CalendarEvent;
import entities.FrequencyAlert;
import entities.IndividualAlert;
import usecases.events.IEventManager;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
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
    public boolean createFrequencyAlert(String eventID, String name, ArrayList<GregorianCalendar> frequency, String userID){
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
            ArrayList<GregorianCalendar> frequency = new ArrayList<GregorianCalendar>();
            frequency.add(this.eventManager.getEventByID(eventID).getStart());
            Alert alert = new FrequencyAlert(alertID,name,frequency,userID );
            return this.alertRepository.saveAlert(alert);
        }
            // this alert is attached to a series of event
        else {
            String seriesID = this.eventManager.getEventByID(eventID).getSeriesID();
            CalendarEvent[] allEvents= this.eventManager.getEventsBySeriesIDAndUserID(seriesID, userID);
            ArrayList<GregorianCalendar> frequency = new ArrayList<GregorianCalendar>();
            for (CalendarEvent allEvent : allEvents) {
                frequency.add(allEvent.getStart());
            }
            Alert alert = new FrequencyAlert(alertID,name,frequency,userID );
            return this.alertRepository.saveAlert(alert);
        }
    }

    @Override
    public void acknowledgeIndividualAlert(String alertID, String userID) {
        Alert alert = this.alertRepository.fetchAlertByIDAndUserID(alertID, userID);
        alert.setAcknowledged();
        //turn off the alert
        this.alertRepository.acknowledgeAlert(alertID, userID);
    }

    @Override
    public void acknowledgeFrequencyAlert(String alertID, String userID) {
        Alert alert = this.alertRepository.fetchAlertByIDAndUserID(alertID, userID);
        int i = 0;
        while (alert.getAcknowledge().get(i)) {
            i++;
        }
        alert.getAcknowledge().set(i, true);

        // acknowledge all times in the frequency Alert
        if (alert.getAcknowledge().get(alert.getAcknowledge().size() - 1)) {
            alert.setAcknowledged();
        }
        this.alertRepository.acknowledgeAlert(alertID,userID);
    }


    // TODO implement this method

    /**
     *
     * @param date
     * @param userID
     * @return
     */
    public ArrayList<Alert> getOverdueAlertsAfterDate(GregorianCalendar date, String userID){}


        /**
     *
     * @param alertID
     * @param name
     * @param newStart
     * @return
     */
    @Override
    public boolean editIndividualAlert(String alertID, String name, GregorianCalendar newStart){
        IndividualAlert alert = getIndividualAlertByID(alertID);
        alert.setAlertName(name);
        alert.setStartTime(newStart);
        return true;
    }

    @Override
    public boolean editFrequencyAlerts(String alertID, String name,ArrayList<GregorianCalendar> frequency){
        FrequencyAlert alert = getFrequencyAlertByID(alertID);
        alert.setAlertName(name);
        alert.setTimes(frequency);
        return true;
    }

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

    public IndividualAlert getIndividualAlertByID(String alertID){
        return this.alertRepository.fetchIndividualAlertByID(alertID);
    }

    public FrequencyAlert getFrequencyAlertByID(String alertID){
        return this.alertRepository.fetchFrequencyAlertByID(alertID);
    }

}

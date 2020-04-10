package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class representing  a Calendar Alert
 */
public class Alert implements Serializable {
    private final String alertID = UUID.randomUUID().toString();
    private String alertName;
    private Boolean totalAcknowledged;
    private String userID;
    private ArrayList<LocalDateTime> times = new ArrayList<>();
    private ArrayList<Boolean> acknowledge = new ArrayList<Boolean>();

    /**
     * Constructor of an individual alert instance.
     *
     * @param alertName the name of this alert
     * @param userID the userID of the user that is associated with this alert
     * @param startTime the start time of the notification time
     */
    public Alert(String alertName, String userID, LocalDateTime startTime) {
        this.alertName = alertName;
        this.totalAcknowledged = false;
        this.userID = userID;
        this.times.add(startTime);
        this.acknowledge.add(false);
    }

    /**
     * Constructor of a frequency alert instance.
     *
     * @param alertName the name of this alert
     * @param userID the userID of the user that is associated with this alert
     * @param alertTimes the times that the alert would notify the user
     */
    public Alert(String alertName, String userID,  ArrayList<LocalDateTime> alertTimes) {
        this.alertName = alertName;
        this.totalAcknowledged = false;
        this.userID = userID;
        this.times = alertTimes;
        for(LocalDateTime time : times) {
            acknowledge.add(false);
        }
    }

    /**
     * A public method that returns the alertID of this alert.
     *
     * @return a String representation of the alertID of this alert
     */
    public String getAlertID() {
        return alertID;
    }

    /**
     * A public method that returns the name of this alert.
     *
     * @return a String representation of the alert name of this alert
     */
    public String getAlertName() {
        return alertName;
    }

    /**
     * A public method that changes the alert name with a new alert name.
     *
     * @param newName the new alert name
     */
    public void setAlertName(String newName) {
        this.alertName = newName;
    }

    /**
     * A public method that returns whether the alert, or a frequency alert has been acknowledged.
     *
     * @return true if all alert times are acknowledged, else return false
     */
    public Boolean getTotalAcknowledged() {return this.totalAcknowledged;}

    /**
     * A public method that acknowledge all times in a frequency alert.
     *
     * @param totalAcknowledged a boolean representation of whether the alert is acknowledged
     */
    public void setTotalAcknowledged(Boolean totalAcknowledged) { this.totalAcknowledged = totalAcknowledged; }

    /**
     * A public method that returns the userID of the user that is associated with this alert.
     *
     * @return A String representation of the userID of the user that is associated with this alert
     */
    public String getUserID(){ return this.userID;}

    /**
     * A public method that returns that times which the frequency alert is notifying the user.
     *
     * @return a list of times when the user is getting notification from the frequency alert
     */
    public ArrayList<LocalDateTime> getTimes(){
        return this.times;
    }

    /**
     * A public method that changes the time which the frequency alert is notifying the user.
     *
     * @param times a list of new times that the frequency alert
     */
    public void setTimes(ArrayList<LocalDateTime> times) {
        this.times = times;
    }

    /**
     * A public method that return the acknowledge list that correspond with the alert times of a frequency alert.
     *
     * @return the acknowledge list that correspond with the alert times of a frequency alert
     */
    public ArrayList<Boolean> getAcknowledge(){
        return this.acknowledge;
    }

    /**
     * A public method that changes the acknowledge list that correspond with the alert times of a frequency alert.
     *
     * @param acknowledge the new acknowledge list that contains boolean representation of which notification times
     *                    are acknowledged.
     */
    public void setAcknowledge(ArrayList<Boolean> acknowledge) {
        this.acknowledge = acknowledge;
    }

    /**
     * A public method returns the next notification time that hasn't been acknowledged.
     *
     * @return the next notification time
     */
    public LocalDateTime getNextRing(){
        if (this.totalAcknowledged) {
            return null;
        }
        int i = 0;
        for (Boolean acknowledged: acknowledge) {
            if (!acknowledged) {
                return times.get(i);
            }
            i++;
        }
        this.totalAcknowledged = true;
        return null;
    }

    /**
     * a public method that acknowledge the first notification time that hasn't been acknowledged before.
     */
    public void acknowledge(){
        if (!this.totalAcknowledged) {
            int i = 0;
            while (this.getAcknowledge().get(i)) {
                i++;
            }
            this.getAcknowledge().set(i, true);
            if (i >= acknowledge.size() - 1) {
                totalAcknowledged = true;
            }
        }
    }
}

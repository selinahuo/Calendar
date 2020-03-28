package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class representing  a Calendar Alert
 */
public class Alert implements Serializable {
    private String alertID = UUID.randomUUID().toString();
    private String alertName;
    private Boolean totalAcknowledged;
    private String userID;
    private ArrayList<LocalDateTime> times = new ArrayList<>();
    private ArrayList<Boolean> acknowledge = new ArrayList<Boolean>();

    public Alert(String alertName, String userID, LocalDateTime startTime) {
        this.alertName = alertName;
        this.totalAcknowledged = false;
        this.userID = userID;
        this.times.add(startTime);
        this.acknowledge.add(false);
    }

    public Alert(String alertName, String userID,  ArrayList<LocalDateTime> alertTimes) {
        this.alertName = alertName;
        this.totalAcknowledged = false;
        this.userID = userID;
        this.times = alertTimes;
        for(LocalDateTime time : times) {
            acknowledge.add(false);
        }
    }

    public String getAlertID() {
        return alertID;
    }

    public String getAlertName() {
        return alertName;
    }

    public void setAlertName(String newName) {
        this.alertName = newName;
    }

    public Boolean getTotalAcknowledged() {return this.totalAcknowledged;}

    public void setTotalAcknowledged(Boolean totalAcknowledged) { this.totalAcknowledged = totalAcknowledged; }

    public String getUserID(){ return this.userID;}

    public ArrayList<LocalDateTime> getTimes(){
        return this.times;
    }

    public void setTimes(ArrayList<LocalDateTime> times) {
        this.times = times;
    }

    public ArrayList<Boolean> getAcknowledge(){
        return this.acknowledge;
    }

    public void setAcknowledge(ArrayList<Boolean> acknowledge) {
        this.acknowledge = acknowledge;
    }

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

    @Override
    public String toString() {
        return String.format("ID: %s | Alert: %s | Next Ring: %s | User: %s",
                getAlertID(), getAlertName(), getNextRing(), getUserID());
    }
}

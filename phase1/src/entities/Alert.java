package entities;
import java.util.*;

/**
 * Class representing a Calendar Alert
 */
public class Alert {
    private String alertID;
    private String alertName;
    private Boolean totalAcknowledged;
    private String userID;
    private ArrayList<GregorianCalendar> times = new ArrayList<GregorianCalendar>();
    private List<Boolean> acknowledge = new ArrayList<Boolean>();


    public Alert(String alertID, String alertName, String userID, GregorianCalendar startTime) {
        this.alertID = alertID;
        this.alertName = alertName;
        this.totalAcknowledged = false;
        this.userID = userID;
        this.times.add(startTime);
        this.acknowledge.add(false);
    }

    public Alert(String alertID, String alertName, String userID,  ArrayList<GregorianCalendar> alertTimes) {
        this.alertID = alertID;
        this.alertName = alertName;
        this.totalAcknowledged = false;
        this.userID = userID;
        this.times = alertTimes;
        for(GregorianCalendar time : times) {
            acknowledge.add(false);
        }
    }

    public String getAlertID() {
        return alertID;
    }
    public void setAlertID(String newAlertID) {this.alertID = newAlertID;}
    public String getAlertName() {
        return alertName;
    }
    public void setAlertName(String newName) {
        this.alertName = newName;
    }
    public Boolean getTotalAcknowledged() {return this.totalAcknowledged;}
    public void setAcknowledged() {
        this.totalAcknowledged = !this.totalAcknowledged;
    }
    public String getUserID(){ return this.userID;}

    public GregorianCalendar getNextRing(){
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

    public List<GregorianCalendar> getTimes(){
        return this.times;
    }

    public List<Boolean> getAcknowledge(){
        return this.acknowledge;
    }
    public void setAcknowledge(List<Boolean> acknowledge) {
        this.acknowledge = acknowledge;
    }

    public void acknowledge(){
        if (!this.totalAcknowledged) {
            int i = 0;
            while (this.getAcknowledge().get(i)) {
                i++;
            }
            this.getAcknowledge().set(i, true);
        }

    }

}

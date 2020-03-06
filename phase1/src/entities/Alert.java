package entities;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class representing a Calendar Alert
 */
public abstract class Alert {
    private String alertID;
    private String alertName;
    Boolean acknowledged;
    String userID;


    public Alert(String alertID, String alertName, String userID) {
        this.alertID = alertID;
        this.alertName = alertName;
        this.acknowledged = false;
        this.userID = userID;
    }

    public String getAlertID() {
        return alertID;
    }

    public String getAlertName() {
        return alertName;
    }


    public abstract GregorianCalendar getNextRing();


}

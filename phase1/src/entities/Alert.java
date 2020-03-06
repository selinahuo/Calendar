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


    public Alert(String alertID, String alertName) {
        this.alertID = alertID;
        this.alertName = alertName;
        this.acknowledged = false;
    }

    public String getAlertID() {
        return alertID;
    }

    public String getAlertName() {
        return alertName;
    }


    public abstract GregorianCalendar getNextRing();


}

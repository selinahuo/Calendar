package entities;
import java.util.Date;

/**
 * Class representing a Calendar Alert
 */
public abstract class Alert {
    private String alertID;
    private String alertName;
    private Date start;
    private Date end;


    public Alert(String alertID, String alertName, Date start, Date end) {
        this.alertID = alertID;
        this.alertName = alertName;
        this.start = start;
        this.end = end;
    }

    public String getAlertID() {
        return alertID;
    }

    public String getAlertName() {
        return alertName;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }


}

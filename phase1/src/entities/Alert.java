package entities;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class representing a Calendar Alert
 */
public abstract class Alert {
    private String alertID;
    private String alertName;
    private GregorianCalendar start;
    private GregorianCalendar end;


    public Alert(String alertID, String alertName, GregorianCalendar start, GregorianCalendar end) {
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

    public GregorianCalendar getStart() {
        return start;
    }

    public GregorianCalendar getEnd() {
        return end;
    }


}

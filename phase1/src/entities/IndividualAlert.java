package entities;

import java.util.GregorianCalendar;
import java.util.List;

public class IndividualAlert extends Alert {

    private GregorianCalendar startTime;
    public IndividualAlert(String alertID, String alertName, GregorianCalendar startTime, String userID){

        super(alertID, alertName, userID);
        this.startTime = startTime;
    }

    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    @Override
    public GregorianCalendar getNextRing() {
       if (!this.acknowledged) {
           return this.startTime;
       }
       return null;
    }

    @Override
    public List<GregorianCalendar> getTimes() {
        return null;
    }

    @Override
    public List<Boolean> getAcknowledge() {
        return null;
    }


}

package entities;

import java.util.GregorianCalendar;

public class IndividualAlert extends Alert {

    private GregorianCalendar startTime;
    public IndividualAlert(String alertID, String alertName, GregorianCalendar startTime, String userID){

        super(alertID, alertName, userID);
        this.startTime = startTime;
    }


    @Override
    public GregorianCalendar getNextRing() {
       if (!this.acknowledged) {
           return this.startTime;
       }
       return null;
    }
}

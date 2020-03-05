package entities;

import java.util.GregorianCalendar;

public class IndividualAlert extends Alert {

    public IndividualAlert(String alertID, String alertName, GregorianCalendar start, GregorianCalendar end){

        super(alertID, alertName, start, end);
    }
}

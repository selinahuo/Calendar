package entities;

import java.util.Date;
import java.util.GregorianCalendar;

public class FrequencyAlert extends Alert{
    private GregorianCalendar[] frequency;

    public FrequencyAlert(String alertID, String alertName, GregorianCalendar start, GregorianCalendar end, GregorianCalendar[] frequency){
        super(alertID, alertName, start, end);
        this.frequency = frequency;
    }

}

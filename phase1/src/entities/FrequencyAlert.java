package entities;

import java.util.Date;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class FrequencyAlert extends Alert{
    private HashMap<GregorianCalendar, Boolean> times = new HashMap<GregorianCalendar, Boolean>();

    public FrequencyAlert(String alertID, String alertName, GregorianCalendar[] frequency){
        super(alertID, alertName);
        for (GregorianCalendar time: frequency) {
            times.put(time, false);
        }
    }

    @Override
    public GregorianCalendar getNextRing() {
        for (GregorianCalendar b : times.keySet()) {
            if (times.get(b)) {
                return b;
            }
        }
        return null;
    }
}

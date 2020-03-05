package entities;

import java.util.Date;

public class FrequencyAlert extends Alert{
    private Date[] frequency;

    public FrequencyAlert(String alertID, String alertName, Date start, Date end, Date[] frequency){
        super(String alertID, String alertName, Date start, Date end);
        this.frequency = frequency;
    }

}

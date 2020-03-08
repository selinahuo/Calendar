//package entities;
//
//import java.lang.reflect.Array;
//import java.util.*;
//
//public class FrequencyAlert extends Alert{
//    private ArrayList<GregorianCalendar> times = new ArrayList<GregorianCalendar>();
//    private List<Boolean> acknowledge = new ArrayList<Boolean>();
//
//    public FrequencyAlert(String alertID, String alertName, ArrayList<GregorianCalendar> frequency, String userID){
//        super(alertID, alertName, userID);
//        this.times = frequency;
//        for(GregorianCalendar time : frequency) {
//            acknowledge.add(false);
//        }
//    }
//
//    @Override
//    public GregorianCalendar getNextRing() {
//        int i = 0;
//        while(acknowledge.get(i)) {
//            i++;
//        }
//        return times.get(i);
//    }
//
//    public List<GregorianCalendar> getTimes(){
//        return this.times;
//    }
//
//    public void setTimes(ArrayList<GregorianCalendar> frequency){
//        this.times = frequency;
//    }
//
//    public List<Boolean> getAcknowledge(){
//        return this.acknowledge;
//    }
//}

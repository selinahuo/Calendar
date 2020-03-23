package usecases.alerts;

import entities.Alert;
import java.time.LocalDateTime;
import entities.CalendarEvent;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IAlertManager {

    public boolean createIndividualAlertOnEvent(String eventID, String alertName, LocalDateTime start, String userID);
    public boolean createFrequencyAlertOnEvent(String eventID, String alertName, String userID, LocalDateTime startTime, String frequency);
    public boolean acknowledgeAlert(String alertID, String userID);
    public ArrayList<Alert> getOverdueAlertsAfterDate(LocalDateTime date, String userID);
    public Alert getAlertByIDAndUserID(String alertID, String userID);
    public Alert[] getAlertByUserID(String userID);
    public boolean editAlertName(String alertID, String name, String newName, String userID);
    public boolean editAlertID(String alertID, String newID, String userID);

}

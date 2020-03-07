package dataaccess;
import entities.Alert;
import entities.CalendarEvent;
import entities.Memo;
import usecases.alerts.IAlertRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class SerializableAlertRepository implements IAlertRepository {
    private ArrayList<Alert> Alerts;

    public SerializableAlertRepository() {
        this.Alerts = new ArrayList<Alert>();
    }

    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream("Alert.csv");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.Alerts);
            oos.close();
            fos.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void deserialize() {
        try {
            FileInputStream fis = new FileInputStream("Memo.csv");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.Alerts = (ArrayList<Alert>) ois.readObject();
            ois.close();
            fis.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    /**
     *
     * @param alert the alert to save
     * @return
     */
    @Override
    public boolean saveAlert(Alert alert){
        deserialize();
        this.Alerts.add(alert);
        serialize();
        return true;
    }

    /**
     *
     * @param id the ID to filter by.
     * @return
     */
    @Override
    public Alert fetchAlertByID(String id) {
        deserialize();
        for (Alert alert : Alerts) {
            if (alert.getAlertID() == id) {
                return alert;
            }
        }
        return null;
    }


    /**
     *
     * @param name the name to filter by.
     * @param userID involves in this alert
     * @return
     */
    @Override
    public Alert fetchAlertByNameAndUserID(String name, String userID){
        deserialize();
        for (Alert alert: Alerts) {
            if (alert.getAlertName().equals(name) && alert.getAlertID().equals(userID)) {
                return alert;
            }
        }
        return null;
    }

}

package controller;
import controller.viewmodels.ListModel;
import entities.Alert;

import java.util.ArrayList;

public class AlertAdapter {

    public static String createAlertString (Alert alert) {
        return String.format("ID: %s | Alert: %s | Alert Times: %s",
                alert.getAlertID(), alert.getAlertName(), alert.getTimes());
    }

    public static ListModel createAlertListModel(ArrayList<Alert> alerts) {
        ArrayList<String> alertList = new ArrayList<>();
        for (Alert alert: alerts ) {
            alertList.add(createAlertString(alert));
        }
        return new ListModel(alertList);
    }

}

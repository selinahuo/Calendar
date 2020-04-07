package controller;

import controller.viewmodels.ListModel;
import entities.Alert;

import java.time.LocalDateTime;
import java.util.ArrayList;

class AlertAdapter {
    private static String alertStatusString(Alert alert) {
        LocalDateTime nextRing = alert.getNextRing();
        if (nextRing != null) {
            return "Alert will ring next at: " + GlobalAdapter.dateToString(nextRing);
        } else if (alert.getTotalAcknowledged()) {
            return "Completely Acknowledged";
        }
        return "";
    }

    static String createAlertString (Alert alert) {
        return String.format("ID: %s | Alert: %s | %s",
                alert.getAlertID(), alert.getAlertName(), alertStatusString(alert));
    }

    static ListModel createAlertListModel(ArrayList<Alert> alerts) {
        ArrayList<String> alertList = new ArrayList<>();
        for (Alert alert: alerts ) {
            alertList.add(createAlertString(alert));
        }
        return new ListModel(alertList);
    }

}

package controller;

import controller.viewmodels.ListModel;
import entities.Alert;

import java.time.LocalDateTime;
import java.util.ArrayList;

class AlertAdapter {
    /**
     * A private static method that allows translating alert status to a String representation.
     *
     * @param alert the alert instance that would be processed
     * @return a String representation of the status of an alert instance
     */
    private static String alertStatusString(Alert alert) {
        LocalDateTime nextRing = alert.getNextRing();
        if (nextRing != null) {
            return "Alert will ring next at: " + GlobalAdapter.dateToString(nextRing);
        } else if (alert.getTotalAcknowledged()) {
            return "Completely Acknowledged";
        }
        return "";
    }

    /**
     * A static method that allows translating alert information to a String representation.
     *
     * @param alert the alert instance that would be processed
     * @return A String representation of the information of an alert instance
     */
    static String createAlertString (Alert alert) {
        if (alert == null) {
            return "Something went wrong, did not create alert.";
        } else {
            return String.format("ID: %s | Alert: %s | %s",
                    alert.getAlertID(), alert.getAlertName(), alertStatusString(alert));
        }
    }

    /**
     * A static method that creates an ListModel for multiple alerts.
     *
     * @param alerts A List of alerts for the ListModel
     * @return ListModel model that has the information of the alerts in the input List
     */
    static ListModel createAlertListModel(ArrayList<Alert> alerts) {
        ArrayList<String> alertList = new ArrayList<>();
        for (Alert alert: alerts ) {
            alertList.add(createAlertString(alert));
        }
        return new ListModel(alertList);
    }

}

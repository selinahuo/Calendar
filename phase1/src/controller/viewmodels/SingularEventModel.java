package controller.viewmodels;

public class SingularEventModel extends ViewModel {
    private String event;
    private String alert;
    private String series;
    private String memo;
    private String tag;

    public SingularEventModel(String event, String alert, String series, String memo, String tag) {
        this.event = event;
        this.alert = alert;
        this.series = series;
        this.memo = memo;
        this.tag = tag;
    }

    public String display() {
        if (event == null && !event.equals("")) {
            return "Event could not be found";
        }
        StringBuilder str = new StringBuilder(event);
        if (alert != null && !alert.equals("")) {
            str.append(System.lineSeparator() + alert);
        }
        if (series != null && !series.equals("")) {
            str.append(System.lineSeparator() + series);
        }
        if (memo != null && !memo.equals("")) {
            str.append(System.lineSeparator() + memo);
        }
        if (tag != null && !tag.equals("")) {
            str.append(System.lineSeparator() + tag);
        }
        return str.toString();
    }
}

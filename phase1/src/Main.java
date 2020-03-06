import dataacess.CSVSeriesRepository;
import entities.Series;

public class Main {
    public static void main(String[] args) {
//        CommandLineUI presenter = new CalendarAppBuilder().build();
//        presenter.run();
        CSVSeriesRepository test = new CSVSeriesRepository();
        Series newSeries = new Series("SERIES ID", "SERIES NAME", 0, "USER");
        test.saveSeries(newSeries);
    }
}

import presenter.CommandLinePresenter;

public class Main {
    public static void main(String[] args) {
        CommandLinePresenter presenter = new CalendarAppBuilder().build();
        presenter.run();
    }
}

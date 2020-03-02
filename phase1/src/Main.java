import ui.CommandLineUI;

public class Main {
    public static void main(String[] args) {
        CommandLineUI presenter = new CalendarAppBuilder().build();
        presenter.run();
    }
}

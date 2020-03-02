import controller.SimpleController;
import dataacess.CSVEventRepository;
import ui.CommandLineUI;
import usecases.UseCaseManagerBuilder;
import usecases.event.IEventRepository;
import usecases.IUseCaseManager;

public class CalendarAppBuilder {
    private IEventRepository eventRepository;
    private IUseCaseManager useCaseManager;
    private SimpleController controller;
    private CommandLineUI presenter;

    private void buildEventRepository() {
        this.eventRepository = new CSVEventRepository();
    }

    private void buildUseCaseManager() {
        this.useCaseManager = new UseCaseManagerBuilder(this.eventRepository).build();
    }

    private void buildSimpleController() {
        this.controller = new SimpleController(this.useCaseManager);
    }

    public CommandLineUI build() {
        this.buildEventRepository();
        this.buildUseCaseManager();
        this.buildSimpleController();
        this.presenter = new CommandLineUI(this.controller);
        return this.presenter;
    }
}

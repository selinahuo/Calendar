import controller.SimpleController;
import dataacess.CSVEventRepository;
import presenter.CommandLinePresenter;
import usecases.event.EventManager;
import usecases.event.IEventRepository;
import usecases.UseCaseManager;

public class CalendarAppBuilder {
    private IEventRepository eventRepository;
    private EventManager eventManager;
    private UseCaseManager useCaseManager;
    private SimpleController controller;
    private CommandLinePresenter presenter;

    private void buildEventRepository() {
        this.eventRepository = new CSVEventRepository();
    }

    private void buildEventManager() {
        this.eventManager = new EventManager(this.eventRepository);
    }

    private void buildUseCaseManager() {
        this.useCaseManager = new UseCaseManager(this.eventManager);
    }

    private void buildProceduralController() {
        this.controller = new SimpleController(this.useCaseManager);
    }

    public CommandLinePresenter build() {
        this.buildEventRepository();
        this.buildEventManager();
        this.buildUseCaseManager();
        this.buildProceduralController();
        this.presenter = new CommandLinePresenter(this.controller);
        return this.presenter;
    }
}

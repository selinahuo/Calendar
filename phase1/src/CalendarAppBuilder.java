import controller.SimpleController;
import dataacess.CSVEventRepository;
import dataacess.CSVUserRepository;
import ui.CommandLineUI;
import usecases.UseCaseManagerBuilder;
import usecases.events.IEventRepository;
import usecases.IUseCaseManager;
import usecases.users.IUserRepository;

public class CalendarAppBuilder {
    private IEventRepository eventRepository;
    private IUserRepository userRepository;
    private IUseCaseManager useCaseManager;
    private SimpleController controller;
    private CommandLineUI presenter;

    private void buildEventRepository() {
        this.eventRepository = new CSVEventRepository();
    }
    private void buildUserRepository() {
        this.userRepository = new CSVUserRepository();
    }

    private void buildUseCaseManager() {
        this.useCaseManager = new UseCaseManagerBuilder(this.eventRepository, this.userRepository).build();
    }

    private void buildSimpleController() {
        this.controller = new SimpleController(this.useCaseManager);
    }

    public CommandLineUI build() {
        this.buildEventRepository();
        this.buildUserRepository();

        this.buildUseCaseManager();

        this.buildSimpleController();

        this.presenter = new CommandLineUI(this.controller);
        return this.presenter;
    }
}

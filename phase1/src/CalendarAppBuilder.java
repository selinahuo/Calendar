import controller.CommandLineController;
import dataaccess.*;
import usecases.*;
import usecases.events.*;
import usecases.alerts.*;
import usecases.notes.*;
import usecases.series.*;
import usecases.users.*;
import view.ViewManager;


public class CalendarAppBuilder {
    private IEventRepository eventRepository;
    private IUserRepository userRepository;
    private IAlertRepository alertRepository;
    private ISeriesRepository seriesRepository;
    private IMemoRepository memoRepository;
    private ITagRepository tagRepository;
    private IEventManager eventManager;
    private IUserManager userManager;
    private IAlertManager alertManager;
    private ISeriesManager seriesManager;
    private INoteManager noteManager;
    private IUseCaseManager useCaseManager;
    private CommandLineController commandLineController;
    private ViewManager viewManager;

    // === Repositories === //
    private void buildEventRepository() {
        this.eventRepository = new SerializableEventRepository();
    }
    private void buildUserRepository() {
        this.userRepository = new SerializableUserRepository();
    }

    private void buildAlertRepository() { this.alertRepository = new SerializableAlertRepository(); }
    private void buildSeriesRepository() {
        this.seriesRepository = new SerializableSeriesRepository();
    }
    private void buildMemoRepository() { this.memoRepository = new SerializableMemoRepository(); }
    private void buildTagRepository() {
        this.tagRepository = new SerializableTagRepository();
    }

    // === Managers === //
    private void buildEventManager() {
        this.eventManager = EventManagerFactory.build(eventRepository);
    }
    private void buildUserManager() {
        this.userManager = UserManagerFactory.build(userRepository);
    }
    private void buildAlertManager() {
        this.alertManager = AlertManagerFactory.build(alertRepository, eventManager);
    }
    private void buildSeriesManager() {
        this.seriesManager = SeriesManagerFactory.build(seriesRepository, eventManager);
    }
    private void buildNoteManager() {
        this.noteManager = NoteManagerFactory.build(memoRepository, tagRepository, eventManager);
    }
    private void buildUseCaseManager() {
        this.useCaseManager = UseCaseManagerFactory.build(eventManager, userManager, alertManager, seriesManager, noteManager);
    }

    // === Controller === //
    private void buildCommandLineController() {
        this.commandLineController = new CommandLineController(useCaseManager);
    }

    // === View === //
    private void buildViewManager() {
        this.viewManager = new ViewManager(commandLineController);
    }

    public ViewManager build() {
        buildEventRepository();
        buildUserRepository();
        buildAlertRepository();
        buildSeriesRepository();
        buildMemoRepository();
        buildTagRepository();

        buildEventManager();
        buildUserManager();
        buildAlertManager();
        buildSeriesManager();
        buildNoteManager();
        buildUseCaseManager();

        buildCommandLineController();
        buildViewManager();
        return this.viewManager;
    }
}

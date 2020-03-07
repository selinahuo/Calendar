import usecases.alerts.IAlertRepository;
import usecases.events.IEventRepository;
import usecases.notes.IMemoRepository;
import usecases.notes.INoteManager;
import usecases.notes.ITagRepository;
import usecases.series.ISeriesRepository;
import usecases.users.IUserRepository;

public class CalendarAppBuilder {
    private IEventRepository eventRepository;
    private IUserRepository userRepository;
    private IAlertRepository alertRepository;
    private ISeriesRepository seriesRepository;
    private IMemoRepository memoRepository;
    private ITagRepository tagRepository;
    private IUseCaseManager useCaseManager;
    private SimpleController controller;
    private CommandLineUI presenter;

//    private void buildEventRepository() {
//        this.eventRepository = new CSVEventRepository();
//    }
//    private void buildUserRepository() {
//        this.userRepository = new CSVUserRepository();
//    }
//    private void buildSeriesRepository() {
//        this.seriesRepository = new CSVSeriesRepository();
//    }
//
//    private void buildUseCaseManager() {
//        this.useCaseManager = new UseCaseManagerBuilder(this.eventRepository,this.userRepository, this.seriesRepository).build();
//    }
//
//    private void buildSimpleController() {
//        this.controller = new SimpleController(this.useCaseManager);
//    }
//
//    public CommandLineUI build() {
//        this.buildEventRepository();
//        this.buildUserRepository();
//        this.buildSeriesRepository();
//
//        this.buildUseCaseManager();
//
//        this.buildSimpleController();
//
//        this.presenter = new CommandLineUI(this.controller);
//        return this.presenter;
//    }
}

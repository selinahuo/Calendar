//package usecases;
//
//import usecases.events.EventManagerFactory;
//import usecases.events.IEventManager;
//import usecases.events.IEventRepository;
//import usecases.series.ISeriesManager;
//import usecases.series.ISeriesRepository;
//import usecases.series.SeriesManagerFactory;
//import usecases.users.IUserManager;
//import usecases.users.IUserRepository;
//import usecases.users.UserManagerFactory;
//
//public class UseCaseManagerBuilder {
//    private IEventRepository eventRepository;
//    private IEventManager eventManager;
//    private IUserRepository userRepository;
//    private IUserManager userManager;
//    private ISeriesRepository seriesRepository;
//    private ISeriesManager seriesManager;
//
//    public UseCaseManagerBuilder(IEventRepository eventRepository, IUserRepository userRepository,
//                                 ISeriesRepository seriesRepository) {
//        this.eventRepository = eventRepository;
//        this.userRepository = userRepository;
//        this.seriesRepository = seriesRepository;
//    }
//
//    public IUseCaseManager build() {
//        this.eventManager = EventManagerFactory.build(this.eventRepository);
//        this.userManager = UserManagerFactory.build(this.userRepository);
//        this.seriesManager = SeriesManagerFactory.build(this.seriesRepository);
//        // TODO: alertManager, tagManager
//        return new UseCaseManager(this.eventManager, this.userManager, this.seriesManager);
//    }
//}

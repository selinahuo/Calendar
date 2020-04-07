import controller.Controller;
import dataaccess.RepositoryFactory;
import usecases.UseCaseManager;
import usecases.alerts.AlertManager;
import usecases.alerts.IAlertRepository;
import usecases.calendar.CalendarManager;
import usecases.calendar.ICalendarRepository;
import usecases.events.EventManager;
import usecases.events.IEventRepository;
import usecases.invitations.IInvitationRepository;
import usecases.invitations.InvitationManager;
import usecases.notes.IMemoRepository;
import usecases.notes.ITagRepository;
import usecases.notes.MemoManager;
import usecases.notes.TagManager;
import usecases.series.ISeriesRepository;
import usecases.series.SeriesManager;
import usecases.users.IUserRepository;
import usecases.users.UserManager;
import views.ViewManager;

import java.time.LocalDateTime;

public class CalendarApp {
    public void run() {
        IEventRepository eventRepository = RepositoryFactory.getEventRepository();
        EventManager eventManager = new EventManager(eventRepository);

        IAlertRepository alertRepository = RepositoryFactory.getAlertRepository();
        AlertManager alertManager = new AlertManager(alertRepository, eventManager);

        ICalendarRepository calendarRepository = RepositoryFactory.getCalendarRepository();
        CalendarManager calendarManager = new CalendarManager(calendarRepository, eventManager);

        IInvitationRepository invitationRepository = RepositoryFactory.getInvitationRepository();
        InvitationManager invitationManager = new InvitationManager(invitationRepository, eventManager);

        IMemoRepository memoRepository = RepositoryFactory.getMemoRepository();
        MemoManager memoManager = new MemoManager(memoRepository, eventManager);

        ITagRepository tagRepository = RepositoryFactory.getTagRepository();
        TagManager tagManager = new TagManager(tagRepository, eventManager);

        ISeriesRepository seriesRepository = RepositoryFactory.getSeriesRepository();
        SeriesManager seriesManager = new SeriesManager(seriesRepository, eventManager);

        IUserRepository userRepository = RepositoryFactory.getUserRepository();
        UserManager userManager = new UserManager(userRepository);

        eventManager.addObservers(alertManager);
        eventManager.addObservers(calendarManager);
        eventManager.addObservers(invitationManager);
        eventManager.addObservers(memoManager);
        eventManager.addObservers(tagManager);
        eventManager.addObservers(seriesManager);

        UseCaseManager useCaseManager = new UseCaseManager(alertManager, calendarManager, eventManager, invitationManager, memoManager, tagManager, seriesManager, userManager);
        Controller controller = new Controller(useCaseManager);

        userManager.createUser("Michael", "password");
        userManager.createUser("Jim", "password");
        String userID = userManager.getUsers().get(0).getUserID();
        String userID2 = userManager.getUsers().get(1).getUserID();

//        String eventID = eventManager.createEvent("Event", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Place", userID);
//        alertManager.createIndividualAlert(eventID, "Alert", LocalDateTime.now(), userID);
//        calendarManager.createCalendar(userID, "Calendar");
//        memoManager.createMemo("Memo", "Memo content", userID);
//        tagManager.createTag("Tag", userID);
//        seriesManager.createSeriesFromEventFormula("236 Lecture", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "w", 2, userID);
//        invitationManager.createInvitation(eventID, userID, userID2, "Hi");

        ViewManager vm = new ViewManager(controller);
        vm.run();
    }
}
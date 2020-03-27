import dataaccess.*;
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

public class Main {
    public static void main(String[] args) {
        IEventRepository eventRepository = new SerializableEventRepository();
        EventManager eventManager = new EventManager(eventRepository);

        IAlertRepository alertRepository = new SerializableAlertRepository();
        AlertManager alertManager = new AlertManager(alertRepository, eventManager);

        ICalendarRepository calendarRepository = new SerializableCalendarRepository();
        CalendarManager calendarManager = new CalendarManager(calendarRepository);

        IInvitationRepository invitationRepository = new SerializableInvitationRepository();
        InvitationManager invitationManager = new InvitationManager(invitationRepository);

        IMemoRepository memoRepository = new SerializableMemoRepository();
        MemoManager memoManager = new MemoManager(memoRepository, eventManager);

        ITagRepository tagRepository = new SerializableTagRepository();
        TagManager tagManager = new TagManager(tagRepository, eventManager);

        ISeriesRepository seriesRepository = new SerializableSeriesRepository();
        SeriesManager seriesManager = new SeriesManager(seriesRepository, eventManager);


        IUserRepository userRepository = new SerializableUserRepository();
        UserManager userManager = new UserManager(userRepository);

        eventManager.addObservers(alertManager);
        eventManager.addObservers(invitationManager);
        eventManager.addObservers(memoManager);
        eventManager.addObservers(tagManager);
        // TODO series manager has to implement
//        eventManager.addObservers(seriesManager);
    }
}

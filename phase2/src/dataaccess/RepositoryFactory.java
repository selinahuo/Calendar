package dataaccess;

import usecases.alerts.IAlertRepository;
import usecases.calendar.ICalendarRepository;
import usecases.events.IEventRepository;
import usecases.invitations.IInvitationRepository;
import usecases.notes.IMemoRepository;
import usecases.notes.ITagRepository;
import usecases.series.ISeriesRepository;
import usecases.users.IUserRepository;

public class RepositoryFactory {
    public static IAlertRepository getAlertRepository() {
        return new SerializableAlertRepository();
    }

    public static ICalendarRepository getCalendarRepository() {
        return new SerializableCalendarRepository();
    }

    public static IEventRepository getEventRepository() {
        return new SerializableEventRepository();
    }

    public static IInvitationRepository getInvitationRepository() {
        return new SerializableInvitationRepository();
    }

    public static IMemoRepository getMemoRepository() {
        return new SerializableMemoRepository();
    }

    public static ISeriesRepository getSeriesRepository() {
        return new SerializableSeriesRepository();
    }

    public static ITagRepository getTagRepository() {
        return new SerializableTagRepository();
    }

    public static IUserRepository getUserRepository() {
        return new SerializableUserRepository();
    }
}

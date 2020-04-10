package dataaccess;

import usecases.alerts.IAlertRepository;
import usecases.calendar.ICalendarRepository;
import usecases.events.IEventRepository;
import usecases.invitations.IInvitationRepository;
import usecases.notes.IMemoRepository;
import usecases.notes.ITagRepository;
import usecases.series.ISeriesRepository;
import usecases.users.IUserRepository;

/**
 * Factory to get repositories.
 * Not exactly pattern from class, but allows the CalendarApp to construct repositories without depending on the
 * serializable implementation.
 */
public class RepositoryFactory {
    /**
     * Construct a repository satisfying IAlertRepository
     * @return IAlertRepository implementation
     */
    public static IAlertRepository getAlertRepository() {
        return new SerializableAlertRepository();
    }

    /**
     * Construct a repository satisfying ICalendarRepository
     * @return ICalendarRepository implementation
     */
    public static ICalendarRepository getCalendarRepository() {
        return new SerializableCalendarRepository();
    }

    /**
     * Construct a repository satisfying IEventRepository
     * @return IEventRepository implementation
     */
    public static IEventRepository getEventRepository() {
        return new SerializableEventRepository();
    }

    /**
     * Construct a repository satisfying IInvitationRepository
     * @return IInvitationRepository implementation
     */
    public static IInvitationRepository getInvitationRepository() {
        return new SerializableInvitationRepository();
    }

    /**
     * Construct a repository satisfying IMemoRepository
     * @return IMemoRepository implementation
     */
    public static IMemoRepository getMemoRepository() {
        return new SerializableMemoRepository();
    }

    /**
     * Construct a repository satisfying ISeriesRepository
     * @return ISeriesRepository implementation
     */
    public static ISeriesRepository getSeriesRepository() {
        return new SerializableSeriesRepository();
    }

    /**
     * Construct a repository satisfying ITagRepository
     * @return ITagRepository implementation
     */
    public static ITagRepository getTagRepository() {
        return new SerializableTagRepository();
    }

    /**
     * Construct a repository satisfying IUserRepository
     * @return IUserRepository implementation
     */
    public static IUserRepository getUserRepository() {
        return new SerializableUserRepository();
    }
}

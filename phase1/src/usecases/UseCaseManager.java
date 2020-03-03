package usecases;

import entities.CalendarEvent;
import usecases.events.IEventManager;
import usecases.series.ISeriesManager;
import usecases.users.IUserManager;

class UseCaseManager implements IUseCaseManager {
    private IEventManager eventManager;
    private IUserManager userManager;
    private ISeriesManager seriesManager;
//    private ISeriesManager seriesManager;
//    private IAlertManager alertManager;
//    private ITagManager tagManager;

    public UseCaseManager(IEventManager eventManager, IUserManager userManager, ISeriesManager seriesManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
        this.seriesManager = seriesManager;
    }

    public boolean createEvent(CalendarEvent event) {
        return this.eventManager.createEvent(event);
    }

    // === USER === //
    // user sign in

    // === CalendarEvent === //
    // createUsersEvent
    // getUsersEvent (by time start and end) (past/present/future)
    // getUsersEvent (by name/id)

    // === Alerts === //
    // createUsersAlert (both types)
    // getUsersAlerts (active, inactive)

    // === Series === //
    // createEventFromSeries(series details)
    // createSeriesFromEvents
    // getUsersEventsBySeriesName (by name/id)

    // === MemoTagging === //
    // addMemoToEvent
        // addTagToEvent
    // getMemos/Tags
    // getTags
    // getSingularEventsMemos/Tags
    // getEventByMemo
}

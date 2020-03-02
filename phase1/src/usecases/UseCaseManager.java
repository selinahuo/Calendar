package usecases;

import entities.CalendarEvent;
import usecases.event.IEventManager;

class UseCaseManager implements IUseCaseManager {
    private IEventManager eventManager;
//    private ISeriesManager seriesManager;
//    private IAlertManager alertManager;
//    private ITagManager tagManager;

    public UseCaseManager(IEventManager eventManager) {
        this.eventManager = eventManager;
    }

    public CalendarEvent createEvent(CalendarEvent event) {
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

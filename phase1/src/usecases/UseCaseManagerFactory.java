package usecases;

import usecases.alerts.IAlertManager;
import usecases.events.IEventManager;
import usecases.notes.INoteManager;
import usecases.series.ISeriesManager;
import usecases.users.IUserManager;

public class UseCaseManagerFactory {
    public static IUseCaseManager build(IEventManager eventManager, IUserManager userManager, IAlertManager alertManager, ISeriesManager seriesManager, INoteManager noteManager) {
        return new UseCaseManager(eventManager, userManager, alertManager, seriesManager, noteManager);
    }
}

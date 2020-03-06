package usecases.alerts;


import usecases.events.IEventManager;

/**
 * AlertManagerFactory which instantiates AlertManagers
 */
public class AlertManagerFactory {
    /**
     * Builds a new AlertManager instance using an injected AlertRepository implementation.
     * @param repository the injected AlertRepositoryImplementation
     * @return the new AlertManager instance
     */
    public static IAlertManager build(IAlertRepository repository, IAlertManager alertManager, IEventManager eventManager) {
        return new AlertManager(repository, alertManager, eventManager);
    }
}

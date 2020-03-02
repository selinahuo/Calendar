package usecases.event;

/**
 * EventManagerFactory which instantiates EventManagers
 */
public class EventManagerFactory {
    /**
     * Builds a new EventManager instance using an injected EventRepository implementation.
     * @param repository the injected EventRepositoryImplementation
     * @return the new EventManager instance
     */
    public static IEventManager build(IEventRepository repository) {
        return new EventManager(repository);
    }
}

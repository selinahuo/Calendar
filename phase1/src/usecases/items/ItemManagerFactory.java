package usecases.items;

/**
 * EventManagerFactory which instantiates EventManagers
 */
public class ItemManagerFactory {
    /**
     * Builds a new EventManager instance using an injected EventRepository implementation.
     * @param repository the injected EventRepositoryImplementation
     * @return the new EventManager instance
     */
    public static IItemManager build(IItemRepository repository) {
        return new ItemManager(repository);
    }
}

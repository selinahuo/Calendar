package usecases.series;

import usecases.events.IEventManager;

/**
 * SeriesManagerFactory which instantiates SeriesManagers
 */
public class SeriesManagerFactory {
    /**
     * Builds a new SeriesManager instance using an injected SeriesRepository implementation
     * @param repository the injected SeriesRepositoryImplementation
     * @return the new SeriesManager instance
     */
    public static ISeriesManager build(ISeriesRepository repository, IEventManager eventManager) {
        return new SeriesManager(repository, eventManager);
    }
}

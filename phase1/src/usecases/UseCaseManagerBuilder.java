package usecases;

import usecases.event.EventManagerFactory;
import usecases.event.IEventManager;
import usecases.event.IEventRepository;

public class UseCaseManagerBuilder {
    private IEventRepository eventRepository;
    private IEventManager eventManager;

    public UseCaseManagerBuilder(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public IUseCaseManager build() {
        this.eventManager = EventManagerFactory.build(this.eventRepository);
        // TODO: seriesManager, alertManager, tagManager
        return new UseCaseManager(this.eventManager);
    }
}

package usecases;

import usecases.events.EventManagerFactory;
import usecases.events.IEventManager;
import usecases.events.IEventRepository;
import usecases.users.IUserManager;
import usecases.users.IUserRepository;
import usecases.users.UserManagerFactory;

public class UseCaseManagerBuilder {
    private IEventRepository eventRepository;
    private IEventManager eventManager;
    private IUserRepository userRepository;
    private IUserManager userManager;

    public UseCaseManagerBuilder(IEventRepository eventRepository, IUserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public IUseCaseManager build() {
        this.eventManager = EventManagerFactory.build(this.eventRepository);
        this.userManager = UserManagerFactory.build(this.userRepository);
        // TODO: seriesManager, alertManager, tagManager
        return new UseCaseManager(this.eventManager, this.userManager);
    }
}

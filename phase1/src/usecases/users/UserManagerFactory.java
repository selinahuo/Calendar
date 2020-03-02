package usecases.users;

/**
 * UserManagerFactory which instantiates UserManagers
 */
public class UserManagerFactory {
    /**
     * Builds a new UserManager instance using an injected UserRepository implementation
     * @param repository the injected UserRepositoryImplementation
     * @return the new UserManager instance
     */
    public static IUserManager build(IUserRepository repository) { return new UserManager(repository); }
}

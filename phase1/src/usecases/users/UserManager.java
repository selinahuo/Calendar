package usecases.users;

import entities.User;
import usecases.events.IEventRepository;

class UserManager implements IUserManager {
    private IUserRepository repository;

    public UserManager(IUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Create and save a new User.
     *
     * @param username the user's username
     * @param password the user's password
     * @return whether User creation was successful
     */
    @Override
    public boolean createUser(String username, String password) {
        return false;
    }

    /**
     * Get a User by their ID.
     *
     * @param id the user's ID
     * @return the matching User
     */
    @Override
    public User getUserByID(String id) {
        return null;
    }

    /**
     * Get a User by their Username.
     *
     * @param username the user's username
     * @return the matching User
     */
    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}

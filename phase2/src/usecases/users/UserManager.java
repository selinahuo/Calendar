package usecases.users;

import entities.User;

import java.util.ArrayList;

/**
 * Manager responsible for user use cases
 */
public class UserManager {
    private IUserRepository repository;

    /**
     * Construct a UserManager
     * @param repository injected user repository dependency
     */
    public UserManager(IUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Authenticate a user
     * @param username username of user
     * @param password password of user
     * @return ID of user if successful or null if unsuccessful
     */
    public String authenticateUser(String username, String password) {
        User user = repository.fetchUserByUsername(username);
        if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
            return user.getUserID();
        }
        return null;
    }

    /**
     * Create a new user
     * @param username user's username
     * @param password user's password
     * @return true if successful; false if username is already taken
     */
    public boolean createUser(String username, String password) {
        User checkUser = repository.fetchUserByUsername(username);
        if (checkUser != null) {
            return false;
        }
        User user =  new User(username, password);
        repository.saveUser(user);
        return true;
    }

    /**
     * Get a list of all user's
     * @return list of all user's
     */
    public ArrayList<User> getUsers() {
        return repository.fetchUsers();
    }
}

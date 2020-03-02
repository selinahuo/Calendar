package usecases.users;

import entities.User;

/**
 * IUserManager provides a public interface for user operations
 */
public interface IUserManager {
    /**
     * Create and save a new User.
     * @param username the user's username
     * @param password the user's password
     * @return whether User creation was successful
     */
    boolean createUser(String username, String password);

    /**
     * Get a User by their ID.
     * @param id the user's ID
     * @return the matching User
     */
    User getUserByID(String id);

    /**
     * Get a User by their Username.
     * @param username the user's username
     * @return the matching User
     */
    User getUserByUsername(String username);
}

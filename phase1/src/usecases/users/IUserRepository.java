package usecases.users;

import entities.User;

/**
 * Provides a public interface for user repository operations
 */
public interface IUserRepository {
    /**
     * Save a User.
     * @param user the User to save
     * @return true if User creation was successful, false otherwise
     */
    boolean saveUser(User user);

    /**
     * Fetch an User by their ID.
     * @param id the ID to filter by.
     * @return the corresponding User or null if it does not exist
     */
    User fetchUserByID(String id);

    /**
     * Fetch User by their username.
     * @param username events must have this name
     * @return User with specified username
     */
    User fetchUserByUsername(String username);
}

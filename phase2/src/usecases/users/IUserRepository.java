package usecases.users;

import entities.User;

import java.util.ArrayList;

/**
 * Provides a public interface for user repository operations
 */
public interface IUserRepository {
    /**
     * Save a user to repository
     * @param user user to save
     */
    void saveUser(User user);

    /**
     * Get user by username
     * @param username username to match
     * @return matching user entity
     */
    User fetchUserByUsername(String username);

    /**
     * Get list of all users in repository
     * @return list of all users
     */
    ArrayList<User> fetchUsers();
}

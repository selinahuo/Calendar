package dataacess;

import entities.User;
import usecases.users.IUserRepository;

public class CSVUserRepository implements IUserRepository {
    /**
     * Save a User.
     *
     * @param user the User to save
     * @return true if User creation was successful, false otherwise
     */
    @Override
    public boolean saveUser(User user) {
        System.out.println("user creating");
        return false;
    }

    /**
     * Fetch an User by their ID.
     *
     * @param id the ID to filter by.
     * @return the corresponding User or null if it does not exist
     */
    @Override
    public User fetchUserByID(String id) {
        return null;
    }

    /**
     * Fetch User by their username.
     *
     * @param username events must have this name
     * @return User with specified username
     */
    @Override
    public User fetchUserByUsername(String username) {
        return null;
    }
}

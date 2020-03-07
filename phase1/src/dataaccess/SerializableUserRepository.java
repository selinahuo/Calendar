package dataaccess;

import entities.User;
import usecases.users.IUserRepository;

public class SerializableUserRepository extends SerializableRepository<User> implements IUserRepository {
    public SerializableUserRepository() {
        super("users.ser");
    }

    /**
     * Fetch User by their username.
     *
     * @param username events must have this name
     * @return User with specified username
     */
    @Override
    public User fetchUserByUsername(String username) {
        for (User user: deserialize()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}

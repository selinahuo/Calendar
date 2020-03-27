package dataaccess;

import entities.User;
import usecases.users.IUserRepository;

import java.util.ArrayList;

public class SerializableUserRepository extends SerializableRepository<User> implements IUserRepository {
    public SerializableUserRepository() {
        super("./users.ser");
    }

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public User fetchUserByUsername(String username) {
        return null;
    }

    @Override
    public ArrayList<User> fetchUsers() {
        return null;
    }
}

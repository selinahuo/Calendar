package dataaccess;

import entities.User;
import usecases.users.IUserRepository;

import java.util.ArrayList;

class SerializableUserRepository extends SerializableRepository<User> implements IUserRepository {
    SerializableUserRepository() {
        super("./users.ser");
    }

    @Override
    public void saveUser(User user) {
        saveItem(user);
    }

    @Override
    public User fetchUserByUsername(String username) {
        return fetchSingular((User u) -> u.getUsername().equals(username));
    }

    @Override
    public ArrayList<User> fetchUsers() {
        return fetchPlural((User u) -> true);
    }
}

package usecases.users;

import entities.User;

import java.util.ArrayList;

public class UserManager {
    private IUserRepository repository;

    public UserManager(IUserRepository repository) {
        this.repository = repository;
    }

    public String authenticateUser(String username, String password) {
        User user = repository.fetchUserByUsername(username);
        if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
            return user.getUserID();
        }
        return null;
    }

    public boolean createUser(String username, String password) {
        User checkUser = repository.fetchUserByUsername(username);
        if (checkUser != null) {
            return false;
        }
        User user =  new User(username, password);
        repository.saveUser(user);
        return true;
    }

    public ArrayList<User> getUsers() {
        return repository.fetchUsers();
    }
}

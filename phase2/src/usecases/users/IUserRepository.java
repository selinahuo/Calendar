package usecases.users;

import entities.User;

import java.util.ArrayList;

public interface IUserRepository {
    boolean saveUser(User user);

    User fetchUserByUsername(String username);

    ArrayList<User> fetchUsers();
}
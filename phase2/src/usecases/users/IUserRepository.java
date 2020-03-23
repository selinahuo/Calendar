package usecases.users;

import entities.User;

import java.util.ArrayList;

public interface IUserRepository {
    User fetchUserByUsername(String username);
    boolean saveUser(User user);

    ArrayList<User> fetchUsers();
}

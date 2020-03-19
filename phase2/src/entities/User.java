package entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class representing an User
 */
public class User implements Serializable {
    private String userID = UUID.randomUUID().toString();
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

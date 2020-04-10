package entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class representing an User
 */
public class User implements Serializable {
    private final String userID = UUID.randomUUID().toString();
    private String username;
    private String password;

    /**
     * Construct a new user
     * @param username username of new user
     * @param password password of new user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get user's unique ID
     * @return user's unique ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Get user's username
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get user's password
     * @return user's password
     */
    public String getPassword() {
        return password;
    }
}

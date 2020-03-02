package entities;

/**
 * Class representing a CalendarApp user
 */
public class User {
    private String userID;
    private String username;
    private String password;
    private String[] events;

    /**
     * Construct a new User.
     * @param username the user's username
     * @param password the user's password
     * @param events list of the user's events' eventIDs
     */
    public User(String username, String password, String[] events) {
        this.username = username;
        this.password = password;
        this.events = events;
    }

    /**
     * Get the user's ID.
     * @return user's ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Get the user's username.
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the user's password.
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the user's eventIDs.
     * @return user's eventIDs
     */
    public String[] getEvents() {
        return events;
    }
}

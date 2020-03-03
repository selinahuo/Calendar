package entities;

/**
 * Class representing a CalendarApp user
 */
public class User {
    private String userID;
    private String username;
    private String password;
    private String[] events;
    private String[] series;

    /**
     * Construct a new User.
     * @param userID the user's ID
     * @param username the user's username
     * @param password the user's password
     * @param events list of the user's events' eventIDs
     * @param series list of the user's series' seriesIDs
     */
    public User(String userID, String username, String password, String[] events, String[] series) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.events = events;
        this.series = series;
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

    /**
     * Get the user's seriesIDs.
     * @return user's seriesIDs
     */
    public String[] getSeries() {
        return series;
    }
}

package views;

/**
 * Class representing session storage across view navigation
 */
public class LocalStorage {
    private String userID = "";
    private String clipEvent = "";
    private String clipUser = "";

    /**
     * Get the current session's user ID
     *
     * @return user ID or "" if not authenticated
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Set the current session's user ID
     *
     * @param userID user ID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Get event ID on current session's clipboard
     *
     * @return event ID on clipboard
     */
    public String getClipEvent() {
        return clipEvent;
    }

    /**
     * Copy an event on to the current session's clipboard
     *
     * @param clipEvent event ID to clip
     */
    public void setClipEvent(String clipEvent) {
        this.clipEvent = clipEvent;
    }

    /**
     * Get user ID on current session's clipboard
     *
     * @return user ID on clipboard
     */
    public String getClipUser() {
        return clipUser;
    }

    /**
     * Copy an user on to the current session's clipboard
     *
     * @param clipUser user ID to clip
     */
    public void setClipUser(String clipUser) {
        this.clipUser = clipUser;
    }
}

package views;

public class LocalStorage {
    private String userID = "";
    private String clipEvent = "";
    private String clipUser = "";

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getClipEvent() {
        return clipEvent;
    }

    public void setClipEvent(String clipEvent) {
        this.clipEvent = clipEvent;
    }

    public String getClipUser() {
        return clipUser;
    }

    public void setClipUser(String clipUser) {
        this.clipUser = clipUser;
    }
}

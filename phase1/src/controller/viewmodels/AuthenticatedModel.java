package controller.viewmodels;

public class AuthenticatedModel extends ViewModel {
    private String userID;
    private String username;

    public AuthenticatedModel(String userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package entities;

import java.io.Serializable;

public class Tag implements Serializable {
    private String tagID;
    private String name;
    private int count;
    private String userID;

    public Tag(String tagID, String name, int count, String userID) {
        this.tagID = tagID;
        this.name = name;
        this.count = count;
        this.userID = userID;
    }

    public String getTagID() {
        return tagID;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getUserID() {
        return userID;
    }
}

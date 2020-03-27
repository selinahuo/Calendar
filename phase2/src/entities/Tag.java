package entities;

import java.io.Serializable;
import java.util.UUID;

public class Tag implements Serializable {
    private String tagID = UUID.randomUUID().toString();
    private String name;
    private int count;
    private String userID;

    public Tag(String name, String userID) {
        this.tagID = tagID;
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void addCount(){
        this.count++;
    }

    public void removeCount(){
        this.count--;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Tag: %s | Count: %d | User: %s",
                getTagID(), getName(), getCount(), getUserID());
    }
}

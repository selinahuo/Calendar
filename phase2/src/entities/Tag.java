package entities;

import java.io.Serializable;

public class Tag implements Serializable {
    private String tagID;
    private String name;
    private int count;
    private String userID;

    public Tag(String name, String tagID, String userID) {
        this.tagID = tagID;
        this.name = name;
        this.count = 0;
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

}

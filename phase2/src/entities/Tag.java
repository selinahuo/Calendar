package entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class representing  a Calendar Tag
 */
public class Tag implements Serializable {
    private final String tagID = UUID.randomUUID().toString();
    private String name;
    private int count;
    private String userID;

    /**
     * Constructor of an individual alert instance.
     *
     * @param name the name of this tag
     * @param userID the userID of the user that is associated with this tagz
     */
    public Tag(String name, String userID) {
        this.name = name;
        this.userID = userID;
    }

    /**
     * A public method that returns the ID of this tag.
     *
     * @return a String representation of the tag's ID
     */
    public String getTagID() {
        return tagID;
    }

    /**
     * A public method that returns this tag's name.
     *
     * @return a String representation of the tag's name.
     */
    public String getName() {
        return name;
    }

    /**
     * A public method that returns the count of this tag.
     *
     * @return an integer representation of the number of events associated with this tag.
     */
    public int getCount() {
        return count;
    }

    /**
     * A public method that returns the id of user who owns the tag.
     *
     * @return a String representation of the tag's userID.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * A public method that changes the tag's name.
     *
     * @param name a new note for the tag's description
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A public method that increments the count of users associated with this tag
     */
    public void addCount(){
        this.count++;
    }

    /**
     * A public method that decrements the count of users associated with this tag
     */
    public void removeCount(){
        this.count--;
    }
}

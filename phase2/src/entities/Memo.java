package entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class representing  a Calendar Memo
 */
public class Memo implements Serializable {
    private String note;
    private String name;
    private final String memoID = UUID.randomUUID().toString();
    private String userID;
    private int count;

    /**
     * Constructor of an individual alert instance.
     *
     * @param name the name of this memo
     * @param userID the userID of the user that is associated with this memo
     * @param note the description of this memo
     */
    public Memo(String name, String note, String userID) {
        this.name = name;
        this.note = note;
        this.userID = userID;
    }

    /**
     * A public method that returns the note of this memo.
     *
     * @return a String representation of the memo's note.
     */
    public String getNote() {
        return note;
    }

    /**
     * A public method that returns the count of this memo.
     *
     * @return an integer representation of the number of events associated with this memo.
     */
    public int getCount() {return count;}

    /**
     * A public method that returns this memo's name.z
     *
     * @return a String representation of the memo's name.
     */
    public String getName() {
        return name;
    }

    /**
     * A public method that returns this memo's memoID.
     *
     * @return a String representation of the memo's memoID.
     */
    public String getMemoID() {
        return memoID;
    }

    /**
     * A public method that returns the id of user who owns the memo.
     *
     * @return a String representation of the memo's userID.
     */
    public String getUserID() {return userID;}

    /**
     * A public method that changes the description of the memo's note.
     *
     * @param note a new note for the memo's description
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * A public method that changes the memo's name.
     *
     * @param name a new note for the memo's description
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A public method that changes the id of the owner of the memo.
     *
     * @param userID a new note for the memo's description
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * A public method that increments the count of users associated with this memo
     */
    public void addCount(){
        this.count++;
    }

    /**
     * A public method that decrements the count of users associated with this memo
     */
    public void removeCount(){
        this.count--;
    }
}
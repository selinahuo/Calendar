package entities;

import java.io.Serializable;

public class Memo implements Serializable {
    private String note;
    private String name;
    private String memoID;
    private String userID;
    private int count;

    public Memo(String name, String note, String memoID, String userID) {
        this.name = name;
        this.note = note;
        this.memoID = memoID;
        this.userID = userID;
        this.count = 0;
    }

    public String getNote() {
        return note;
    }

    public int getCount() {return count;}

    public String getName() {
        return name;
    }

    public String getMemoID() {
        return memoID;
    }

    public String getUserID() {return userID;}

    public void setNote(String note) {
        this.note = note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void addCount(){
        this.count++;
    }

    public void removeCount(){
        this.count--;
    }
}
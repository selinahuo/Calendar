package entities;

import java.io.Serializable;

public class Memo implements Serializable {
    String note;
    String name;
    String memoID;
    String userID;

    public Memo(String name, String note, String memoID, String userID) {
        this.name = name;
        this.note = note;
        this.memoID = memoID;
        this.userID = userID;
    }

    public String getNote() {
        return note;
    }

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

    public void setMemoID(String memoID) {
        this.memoID = memoID;
    }
}
package entities;

import java.util.ArrayList;

public class Memo{
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

    public String getUserID() {
        return userID;
    }
}

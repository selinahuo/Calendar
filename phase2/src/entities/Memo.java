package entities;

import java.io.Serializable;
import java.util.UUID;

public class Memo implements Serializable {
    private String note;
    private String name;
    private String memoID = UUID.randomUUID().toString();
    private String userID;
    private int count;

    public Memo(String name, String note, String userID) {
        this.name = name;
        this.note = note;
        this.userID = userID;
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

    @Override
    public String toString() {
        return String.format("ID: %s | Memo: %s - %s | Count: %d | User: %s",
                getMemoID(), getName(), getNote(), getCount(), getUserID());
    }
}
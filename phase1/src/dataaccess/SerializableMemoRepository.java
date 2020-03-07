package dataaccess;

import entities.CalendarEvent;
import entities.Memo;
import usecases.notes.IMemoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class SerializableMemoRepository implements IMemoRepository {
    private ArrayList<Memo> Memos;

    public SerializableMemoRepository() {
        this.Memos = new ArrayList<Memo>();
    }

    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream("memos.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.Memos);
            oos.close();
            fos.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void deserialize() {
        try {
            FileInputStream fis = new FileInputStream("memos.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.Memos = (ArrayList<Memo>) ois.readObject();
            ois.close();
            fis.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    /**
     * Saves a memo.
     *
     * @param memo the memo to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean saveMemo(Memo memo) {
        deserialize();
        this.Memos.add(memo);
        serialize();
        return true;
    }

    /**
     * Fetch a Memo by its name.
     *
     * @param memoName the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */

    @Override
    public Memo fetchMemoByName(String memoName) {
        deserialize();
        for (Memo memo : Memos) {
            if (memo.getName().equals(memoName)) {
                return memo;
            }
        }
        return null;
    }

    /**
     *
     * @param memoID the name to filter by.
     * @return
     */
    public Memo fetchMemoByMemoId(String memoID) {
        deserialize();
        for (Memo memo : Memos) {
            if (memo.getMemoID().equals(memoID)) {
                return memo;
            }
        }
        return null;
    }

    /**
     * Fetch a Memo by its Name.
     *
     * @param name   the name to filter by.
     * @param userID
     * @return the corresponding memo or null if it does not exist
     */
    @Override
    public Memo fetchMemoByIDAndUserID(String name, String userID) {
        deserialize();
        for (Memo memo: Memos) {
            if (memo.getName().equals(name) && memo.getUserID().equals(userID)) {
                return memo;
            }
        }
        return null;
    }

    @Override
    public Memo[] fetchMemosByUserID(String userID) {
        deserialize();
        List<Memo> memoList = new ArrayList<>();
        for (Memo memo: Memos) {
            if (memo.getUserID() == userID) {
                memoList.add(memo);
            }
        }
        Memo[] memoArray  = new Memo[memoList.size()];
        memoArray = memoList.toArray(memoArray);
        return memoArray;
    }
}

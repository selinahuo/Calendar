package dataaccess;

import entities.Memo;
import usecases.items.IMemoRepository;

import java.io.*;
import java.util.ArrayList;


public class SerializableMemoRepository implements IMemoRepository {
    private ArrayList<Memo> Memos;

    public SerializableMemoRepository() {
        this.Memos = new ArrayList<Memo>();
    }

    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream("Memos.csv");
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
            FileInputStream fis = new FileInputStream("Memo.csv");
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
     * Saves a tag.
     *
     * @param memo the tag to save
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
     * @return the corresponding Tag or null if it does not exist
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
     * @param memoid the name to filter by.
     * @return
     */
    public Memo fetchMemoByMemoId(String memoid) {
        deserialize();
        for (Memo memo : Memos) {
            if (memo.getMemoID().equals(memoid)) {
                return memo;
            }
        }
        return null;
    }

    /**
     * Fetch a Tag by its Name.
     *
     * @param name   the name to filter by.
     * @param userID
     * @return the corresponding Tag or null if it does not exist
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
}

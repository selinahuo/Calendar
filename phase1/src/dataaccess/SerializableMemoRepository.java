package dataaccess;

import entities.Memo;
import usecases.notes.IMemoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializableMemoRepository extends SerializableRepository<Memo> implements IMemoRepository {
    public SerializableMemoRepository() {
        super("memos.ser");
    }

    /**
     * Saves a memo.
     *
     * @param memo the memo to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean saveMemo(Memo memo) {
        ArrayList<Memo> memos = deserialize();
        memos.add(memo);
        serialize(memos);
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
        ArrayList<Memo> memos = deserialize();
        for (Memo memo : memos) {
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
        ArrayList<Memo> memos = deserialize();
        for (Memo memo : memos) {
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
        ArrayList<Memo> memos = deserialize();
        for (Memo memo: memos) {
            if (memo.getName().equals(name) && memo.getUserID().equals(userID)) {
                return memo;
            }
        }
        return null;
    }

    @Override
    public Memo[] fetchMemosByUserID(String userID) {
        ArrayList<Memo> memos = deserialize();
        List<Memo> memoList = new ArrayList<>();
        for (Memo memo: memos) {
            if (memo.getUserID().equals(userID)) {
                memoList.add(memo);
            }
        }
        Memo[] memoArray  = new Memo[memoList.size()];
        memoList.toArray(memoArray);
        return memoArray;
    }
}

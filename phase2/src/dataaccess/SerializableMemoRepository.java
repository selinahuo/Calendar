package dataaccess;

import entities.Memo;
import usecases.notes.IMemoRepository;

import java.util.ArrayList;

public class SerializableMemoRepository extends SerializableRepository<Memo> implements IMemoRepository {
    public SerializableMemoRepository() {
        super("memos.ser");
    }

    @Override
    public boolean saveMemo(Memo memo) {
        ArrayList<Memo> memos = deserialize();
        memos.add(memo);
        serialize(memos);
        return true;
    }

    @Override
    public Memo fetchMemoByMemoID(String memoID) {
        return fetchSingular((Memo memo) -> memo.getMemoID() != null && memo.getMemoID().equals(memoID));

    }

    @Override
    public Memo fetchMemoByMemoIDAndOwnerID(String memoID, String ownerID) {
        return fetchSingular((Memo memo) ->
                memo.getMemoID() != null && memo.getMemoID().equals(memoID)
                        && memo.getUserID() != null && memo.getUserID().equals(ownerID));
    }

    @Override
    public ArrayList<Memo> fetchMemosByOwnerID(String ownerID) {
        return fetchPlural((Memo memo) -> memo.getUserID() != null && memo.getUserID().equals(ownerID));
    }

    @Override
    public ArrayList<Memo> fetchMemosByNameAndOwnerID(String name, String ownerID) {
        return fetchPlural((Memo memo) ->
                memo.getName() != null && memo.getName().equals(name)
                        && memo.getUserID() != null && memo.getUserID().equals(ownerID)
        );
    }

    @Override
    public boolean editMemoName(String memoID, String name, String newName, String OwnerID) {
        ArrayList<Memo> memosArr = deserialize();
        for (Memo memos: memosArr) {
            if (memos.getMemoID().equals(memoID)) {
                memos.setName(newName);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editMemoNote(String memoID, String note, String newNote, String OwnerID) {
        ArrayList<Memo> memosArr = deserialize();
        for (Memo memos: memosArr) {
            if (memos.getMemoID().equals(memoID)) {
                memos.setNote(newNote);
                return true;
            }
        }
        return false;
    }



    @Override
    public boolean deleteMemo(String memoID, String ownerID) { return false; }
}

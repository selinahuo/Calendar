package dataaccess;
import entities.Memo;
import usecases.notes.IMemoRepository;

import java.util.ArrayList;

class SerializableMemoRepository extends SerializableRepository<Memo> implements IMemoRepository {
    SerializableMemoRepository() {
        super("./memos.ser");
    }

    @Override
    public void saveMemo(Memo memo) {
        saveItem(memo);
    }

    @Override
    public Memo fetchMemoByMemoID(String memoID) {
        return fetchSingular((Memo memo) -> memo.getMemoID().equals(memoID));

    }

    @Override
    public Memo fetchMemoByMemoIDAndOwnerID(String memoID, String ownerID) {
        return fetchSingular((Memo memo) -> memo.getMemoID().equals(memoID) && memo.getUserID().equals(ownerID));
    }

    @Override
    public ArrayList<Memo> fetchMemosByOwnerID(String ownerID) {
        return fetchPlural((Memo memo) -> memo.getUserID().equals(ownerID));
    }

    @Override
    public Memo fetchMemoByNameAndOwnerID(String name, String ownerID) {
        return fetchSingular((Memo memo) -> memo.getName().equals(name) && memo.getUserID().equals(ownerID));
    }

    @Override
    public boolean editMemoName(String memoID, String name, String ownerID) {
        return editSingular(
                (Memo memo) -> memo.getMemoID().equals(memoID) && memo.getUserID().equals(ownerID),
                (Memo memo) -> memo.setName(name)
        );
    }

    @Override
    public boolean editMemoNote(String memoID, String note, String ownerID) {
        return editSingular(
                (Memo memo) -> memo.getMemoID().equals(memoID) && memo.getUserID().equals(ownerID),
                (Memo memo) -> memo.setNote(note)
        );
    }

    @Override
    public boolean editMemoCountAdd(String memoID, String ownerID) {
        return editSingular(
                (Memo memo) -> memo.getMemoID().equals(memoID) && memo.getUserID().equals(ownerID),
                Memo::addCount
        );
    }

    @Override
    public boolean editMemoCountRemove(String memoID, String ownerID) {
        return editSingular(
                (Memo memo) -> memo.getMemoID().equals(memoID) && memo.getUserID().equals(ownerID),
                Memo::removeCount
        );
    }

    @Override
    public boolean deleteMemo(String memoID, String ownerID) {
        return deleteSingular((Memo memo) -> memo.getMemoID().equals(memoID) && memo.getUserID().equals(ownerID));
    }
}

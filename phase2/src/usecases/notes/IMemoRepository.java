package usecases.notes;

import entities.Memo;
import java.util.ArrayList;

public interface IMemoRepository {
    // write - Memos
    boolean saveMemo(Memo memo);

    // Fetch - singular - Memos
    Memo fetchMemoByMemoID(String memoID);
    Memo fetchMemoByMemoIDAndOwnerID(String memoID, String ownerID);

    // Fetch - plural - Memos
    // Generic
    ArrayList<Memo> fetchMemosByOwnerID(String ownerID);

    // Name - Memos
    ArrayList<Memo> fetchMemosByNameAndOwnerID(String name, String ownerID);

    // edit - Memos
    boolean editMemoName(String memoID, String name, String newName, String OwnerID);
    boolean editMemoNote(String memoID, String note, String newNote, String OwnerID);
    boolean editMemoID(String memoID, String newID, String OwnerID);

    // delete - Memo
    boolean deleteMemo(String memoID, String ownerID);
}

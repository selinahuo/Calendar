package usecases.notes;

import entities.CalendarEvent;
import entities.Memo;
import entities.Tag;
import usecases.events.EventManager;

import java.util.ArrayList;

public interface IMemoRepository {
    // write - Memos
    void saveMemo(Memo memo);

    // Fetch - singular - Memos
    Memo fetchMemoByMemoID(String memoID);
    Memo fetchMemoByMemoIDAndOwnerID(String memoID, String ownerID);

    // Fetch - plural - Memos
    // Generic
    ArrayList<Memo> fetchMemosByOwnerID(String ownerID);

    // Name - Memos
    ArrayList<Memo> fetchMemosByNameAndOwnerID(String name, String ownerID);

    // edit - Memos
    boolean editMemoName(String memoID, String name, String ownerID);
    boolean editMemoNote(String memoID, String note, String ownerID);
    boolean editMemoCountAdd(String memoID, String ownerID);
    boolean editMemoCountRemove(String memoID, String ownerID);

    // delete - Memo
    boolean deleteMemo(String memoID, String ownerID);

}

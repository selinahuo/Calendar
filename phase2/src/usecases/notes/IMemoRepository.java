package usecases.notes;

import entities.CalendarEvent;
import entities.Memo;
import entities.Tag;
import usecases.events.EventManager;

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
    boolean editMemoCountAdd(String tagID, String ownerID);
    boolean editMemoCountRemove(String tagID, String ownerID);

    // delete - Memo
    boolean deleteMemo(String memoID, String ownerID);

    ArrayList<CalendarEvent> fetchEventsByMemoIDAndOwnerID(String memoID, String ownerID);

}

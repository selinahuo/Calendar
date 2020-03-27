package usecases.notes;

import entities.CalendarEvent;
import entities.Memo;
import entities.Tag;
import usecases.events.EventManager;

import java.util.ArrayList;


public class MemoManager {
    private IMemoRepository repository;
    private EventManager eventManager;

    public MemoManager(IMemoRepository repository) {
        this.repository = repository;
    }

    // save - Memo
    public String createMemo(String name, String note, String memoID, String userID) {
        Memo memo = new Memo(name, note, memoID, userID);
        boolean success = repository.saveMemo(memo);
        if (success) {
            return memo.getMemoID();
        } else {
            return null;
        }
    }

    // get - singular - Memos
    public Memo getMemoByMemoID(String memoID){ return repository.fetchMemoByMemoID(memoID); }

    public Memo getMemoByMemoIDAndOwnerID(String memoID, String ownerID){
        return repository.fetchMemoByMemoIDAndOwnerID(memoID, ownerID);
    }

    // get - plural - Memos
    // Generic
    public ArrayList<Memo> getMemosByOwnerID(String ownerID){
        return repository.fetchMemosByOwnerID(ownerID);
    }

    // Name - Memos
    public ArrayList<Memo> getMemosByNameAndOwnerID(String name, String ownerID){
        return repository.fetchMemosByNameAndOwnerID(name, ownerID);
    }

    public ArrayList<CalendarEvent> getEventsByMemoIDAndOwnerID(String memoID, String ownerID) {
        return eventManager.getEventsByMemoIDAndOwnerID(memoID, ownerID);
    }

    // edit - Memos
    boolean editMemoName(String memoID, String name, String newName, String ownerID){
        return repository.editMemoName(memoID, name, newName, ownerID);
    }

    boolean editMemoNote(String memoID, String note, String newNote, String ownerID){
        return repository.editMemoNote(memoID, note, newNote, ownerID);
    }

    boolean deleteMemo(String memoID, String ownerID) {
        Memo memo = repository.fetchMemoByMemoIDAndOwnerID(memoID, ownerID);
        if (memo.getCount() <= 0) {
            return repository.deleteMemo(memoID, ownerID);
        }
        return false;
    }

    // Add/remove memos
    public boolean addMemoToEvent(String memoID, String eventID, String ownerID) {
        Memo memo = repository.fetchMemoByMemoIDAndOwnerID(memoID, ownerID);
        if (memo != null) {
            boolean success = eventManager.editMemoID(eventID, memoID, ownerID);
            if (success) {
                repository.editMemoCountAdd(memoID, ownerID);
            }
            return success;
        }
        return false;
    }

    public boolean removeMemoFromEvent(String eventID, String ownerID) {
        CalendarEvent event = eventManager.getEventByIDAndUserID(eventID, ownerID);
        if (event == null) {
            return false;
        }
        String memoID = event.getMemoID();
        if (memoID == "") {
            return true;
        }
        eventManager.editMemoID(eventID, "", ownerID);
        repository.editMemoCountRemove(memoID, ownerID);
        return true;
    }
}

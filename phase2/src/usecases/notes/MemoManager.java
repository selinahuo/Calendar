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

    // edit - Memos
    boolean editMemoName(String memoID, String name, String newName, String ownerID){
        return repository.editMemoName(memoID, name, newName, ownerID);
    }

    boolean editMemoNote(String memoID, String note, String newNote, String ownerID){
        return repository.editMemoNote(memoID, note, newNote, ownerID);
    }


    boolean editMemoCountAdd(String memoID, String ownerID){
        return repository.editMemoCountAdd(memoID, ownerID);
    }

    boolean editMemoCountRemove(String memoID, String ownerID){
        return repository.editMemoCountRemove(memoID, ownerID);
    }

    // delete - Memo
    boolean deleteMemo(String memoID, String ownerID){
        return repository.deleteMemo(memoID, ownerID);
    }

    public ArrayList<CalendarEvent> fetchEventsByMemoIDAndOwnerID(String memoID, String ownerID){
        ArrayList<CalendarEvent> events =  eventManager.getEventsByOwnerID(ownerID);
        ArrayList<CalendarEvent> newEvents = new ArrayList<>();
        for (CalendarEvent event : events){
            for (String id : event.getMemoIDs()){
                if (id.equals(memoID)){
                    newEvents.add(event);
                }
            }
        }
        return newEvents;
    }

    // Add/remove memos
    boolean addMemoToEvent(String memoID, String eventID, String ownerID){
        CalendarEvent event = eventManager.getEventByIDAndUserID(eventID, ownerID);
        ArrayList<String> ids = event.getTagIDs();
        ids.add(memoID);
        event.setTagIDs(ids);
        getMemoByMemoID(memoID).addCount();
        return true;
    }
    boolean removeMemoFromEvent(String memoID, String eventID, String ownerID){
        CalendarEvent event = eventManager.getEventByIDAndUserID(eventID, ownerID);
        ArrayList<String> ids = event.getMemoIDs();
        ArrayList<String> newIDs = new ArrayList<>();
        boolean removed = false;
        for (String id : ids){
            if (!id.equals(memoID)){
                newIDs.add(id);
            }else{
                removed = true;
                getMemoByMemoID(memoID).removeCount();
            }
        }
        event.setMemoIDs(newIDs);
        return removed;
    }
}

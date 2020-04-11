package usecases.notes;

import entities.CalendarEvent;
import entities.Memo;
import usecases.events.EventManager;
import usecases.events.IEventDeletionObserver;
import java.util.ArrayList;

/**
 * A class that is responsible for the creation, deletion and modification of memo instances in the Calendar program.
 */
public class MemoManager implements IEventDeletionObserver {
    private IMemoRepository repository;
    private EventManager eventManager;

    /**
     * Constructor for MemoManager.
     *
     * @param repository the repository associated with Memo
     * @param eventManager the eventManager of the calendar events that the memos are associated to
     */
    public MemoManager(IMemoRepository repository, EventManager eventManager) {
        this.eventManager = eventManager;
        this.repository = repository;
    }

    /**
     * Create an Individual Memo.
     *
     * @param name the name of this alert
     * @param note the message associated with
     * @param userID the user that this alert will notify
     * @return memo
     */
    public String createMemo(String name, String note, String userID) {
        Memo memo = new Memo(name, note, userID);
        repository.saveMemo(memo);
        return memo.getMemoID();
    }

    /**
     * Retrieve memo by its individual ID
     *
     * @param memoID the alertID of this memo.
     * @return the desired memo.
     */
    public Memo getMemoByMemoID(String memoID){ return repository.fetchMemoByMemoID(memoID); }

    /**
     * Retrieve memo by its individual ID
     *
     * @param memoID the memoID of this memo.
     * @param ownerID the ID of owner of the memo.
     * @return the desired memo.
     */
    public Memo getMemoByMemoIDAndOwnerID(String memoID, String ownerID){
        return repository.fetchMemoByMemoIDAndOwnerID(memoID, ownerID);
    }

    /**
     * Retrieve all memos currently belonging to the user with the identifier ownerID.
     *
     * @param ownerID userID of the respected user.
     * @return list of memos.
     */
    public ArrayList<Memo> getMemosByOwnerID(String ownerID){
        return repository.fetchMemosByOwnerID(ownerID);
    }

    /**
     * Retrieve a memo by its name and ownerID
     *
     * @param name the name of this memo.
     * @param ownerID the ID of the owner of the memo
     * @return the desired memo.
     */
    public Memo getMemoByNameAndOwnerID(String name, String ownerID){
        return repository.fetchMemoByNameAndOwnerID(name, ownerID);
    }

    /**
     * Retrieve a list of events which contain a specific memo.
     *
     * @param memoID the ID of the memo
     * @param ownerID the ID of the user who owns the memo.
     * @return the list of desired events.
     */
    public ArrayList<CalendarEvent> getEventsByMemoIDAndOwnerID(String memoID, String ownerID) {
        return eventManager.getEventsByMemoIDAndOwnerID(memoID, ownerID);
    }

    /**
     * Edit the memo's name.
     *
     * @param memoID the memoID of this memo
     * @param name the new name of this memo
     * @param ownerID the ownerID of the user that is associated with this memo
     * @return true is modification is successful
     */
    public boolean editMemoName(String memoID, String name, String ownerID){
        return repository.editMemoName(memoID, name, ownerID);
    }

    /**
     * Edit the memo's note.
     *
     * @param memoID the memoID of this memo
     * @param note the new note of this memo
     * @param ownerID the ownerID of the user that is associated with this memo
     * @return true is modification is successful
     */
    public boolean editMemoNote(String memoID, String note, String ownerID){
        return repository.editMemoNote(memoID, note, ownerID);
    }

    /**
     * Deletes the memo if it is no longer contained by any event.
     *
     * @param memoID the memoID of this memo
     * @param ownerID the ownerID of the user that is associated with this memo
     * @return true if the memo is deleted
     */
    public boolean deleteMemo(String memoID, String ownerID) {
        Memo memo = repository.fetchMemoByMemoIDAndOwnerID(memoID, ownerID);
        if (memo != null){
            if (memo.getCount() <= 0) {
                return repository.deleteMemo(memoID, ownerID);
            }
        }
        return false;
    }

    /**
     * Adds the memo to  event.
     *
     * @param memoID the memoID of this memo
     * @param ownerID the ownerID of the user that is associated with this memo
     * @return true if the memo is deleted
     */
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
    /**
     * Removes the memo from an event.
     *
     * @param eventID the ID of the event the memo will be removed from.
     * @param ownerID the ownerID of the user that is associated with this memo
     * @return true if the memo is removed
     */
    public boolean removeMemoFromEvent(String eventID, String ownerID) {
        CalendarEvent event = eventManager.getEventByIDAndUserID(eventID, ownerID);
        if (event == null) {
            return false;
        }
        String memoID = event.getMemoID();
        if (memoID.equals("")) {
            return true;
        }
        eventManager.editMemoID(eventID, "", ownerID);
        repository.editMemoCountRemove(memoID, ownerID);
        return true;
    }

    /**
     * Handle event deletion by removing count of memo associated with deleted event
     *
     * @param event event which was deleted and reacted to
     */
    @Override
    public void handleEventDeletion(CalendarEvent event) {
        repository.editMemoCountRemove(event.getMemoID(), event.getUserID());
    }
}

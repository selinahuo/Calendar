package usecases.notes;

import entities.Memo;

import java.util.ArrayList;

public interface IMemoRepository {
    // write - Memos
    void saveMemo(Memo memo);

    /**
     * Fetch memo by its unique ID
     * @param memoID unique memo ID
     * @return matching memo entity
     */
    Memo fetchMemoByMemoID(String memoID);

    /**
     * Fetch memo by its unique ID and owner ID
     * @param memoID unique memo ID
     * @param ownerID unique owner ID
     * @return matching memo entity
     */
    Memo fetchMemoByMemoIDAndOwnerID(String memoID, String ownerID);

    /**
     * Fetch a list of memos by owner ID
     * @param ownerID unique owner ID
     * @return list of memo entities
     */
    ArrayList<Memo> fetchMemosByOwnerID(String ownerID);

    /**
     * Fetch memo by its name and owner ID
     * @param name memo name
     * @param ownerID unique owner ID
     * @return matching memo entity
     */
    Memo fetchMemoByNameAndOwnerID(String name, String ownerID);

    /**
     * Edit a memo's name
     * @param memoID unique memo ID
     * @param name the new name
     * @param ownerID owner of the memo
     * @return whether the edit was successful
     */
    boolean editMemoName(String memoID, String name, String ownerID);

    /**
     * Edit a memo's note
     * @param memoID unique memo ID
     * @param note the new note
     * @param ownerID owner of the memo
     * @return whether the edit was successful
     */
    boolean editMemoNote(String memoID, String note, String ownerID);

    /**
     * Increment a memo's count
     * @param memoID unique memo ID
     * @param ownerID owner of the memo
     * @return whether the edit was successful
     */
    boolean editMemoCountAdd(String memoID, String ownerID);

    /**
     * decrement a memo's count
     * @param memoID unique memo ID
     * @param ownerID owner of the memo
     * @return whether the edit was successful
     */
    boolean editMemoCountRemove(String memoID, String ownerID);

    /**
     * Deletes a memo
     * @param memoID unique memo ID
     * @param ownerID owner of the memo
     * @return whether the deletion was successful
     */
    boolean deleteMemo(String memoID, String ownerID);

}

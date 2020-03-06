package usecases.items;

import entities.Memo;

/**
 * Provides a public interface for event repository operations
 */
public interface IMemoRepository {
    /**
     * Saves a Memo.
     * @param memo the memo to save
     * @return true if event creation was successful, false otherwise
     */
    boolean saveMemo(Memo memo);

    /**
     * Fetch a Memo by its Name.
     * @param name the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    entities.Memo fetchMemoByName(String name);

    /**
     * Fetch a Memo by its id.
     * @param memoid the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    entities.Memo fetchMemoByMemoId(String memoid);

    /**
     * Fetch a Memo that has an ID in the list of IDS with a matching name
     * @param memoID name to match the Memo by
     * @param userID returned Series must have an ID in this list
     * @return a Memo fitting the name and ID criteria
     */
    entities.Memo fetchMemoByIDAndUserID(String memoID, String userID);
}

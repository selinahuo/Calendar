package usecases.items;

import entities.Memo;

import entities.Tag;


/**
 * Provides a public interface for event repository operations
 */
public interface IItemRepository {
    /**
     * Saves a Memo.
     * @param memo the memo to save
     * @return true if event creation was successful, false otherwise
     */
    boolean saveMemo(Memo memo);

    /**
     * Saves a tag.
     * @param tag the tag to save
     * @return true if event creation was successful, false otherwise
     */
    boolean saveTag(Tag tag);

    /**
     * Fetch a Memo by its Name.
     * @param name the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    entities.Memo fetchMemoByName(String name);

    /**
     * Fetch a Tag by its Name.
     * @param name the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    entities.Tag fetchTagByName(String name);

    /**
     * Fetch a Memo by its id.
     * @param id the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    entities.Memo fetchMemoById(String id);

    /**
     * Fetch a Tag by its id.
     * @param id the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    entities.Tag fetchTagById(String id);

    /**
     * Fetch a Tag that has an ID in the list of IDS with a matching name
     * @param tagID name to match the Tag by
     * @param userID returned Tag must have an ID in this list
     * @return a Tag fitting the name and ID criteria
     */
    entities.Tag fetchTagByIDAndUserID(String tagID, String userID);

    /**
     * Fetch a Memo that has an ID in the list of IDS with a matching name
     * @param memoID name to match the Memo by
     * @param userID returned Series must have an ID in this list
     * @return a Memo fitting the name and ID criteria
     */
    entities.Memo fetchMemoByIDAndUserID(String memoID, String userID);
}

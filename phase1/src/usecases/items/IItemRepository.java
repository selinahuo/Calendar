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
}

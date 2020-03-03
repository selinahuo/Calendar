package usecases.items;

import entities.Tag;

import entities.Memo;

public interface IItemManager {
    /**
     * Create and save a Memo.
     * @param memo the Memo to save
     * @return true if event creation was successful, false otherwise
     */
    boolean createMemo(Memo memo);

    /**
     * Create and save a Tag.
     * @param tag the Tag to save
     * @return true if event creation was successful, false otherwise
     */
    boolean createTag(Tag tag);

    /**
     * Get a Memo by its name.
     * @param name the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    Memo getMemoByName(String name);

    /**
     * Get a Tag by its name.
     * @param name the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    Tag getTagByName(String name);
}

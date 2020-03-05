package usecases.items;

import entities.Tag;

import entities.Memo;

import java.util.ArrayList;

public interface IItemManager {
    /**
     * Create a Memo.
     * @param name the name of the memo
     * @param note the note which the memo contains
     * @param id the unique id of the memo
     * @return true if event creation was successful, false otherwise
     */
    boolean createMemo(String name, String note, String id);

    /**
     * Create a Tag.
     * @param name the Tag to create
     * @param id the unique id of this tag
     * @return true if event creation was successful, false otherwise
     */
    boolean createTag(String name, String id);

    /**
     * Get a Memo by its name.
     * @param name the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    Memo getMemoByName(String name);

    /**
     * Get a Memo by its id.
     * @param id the id to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    Memo getMemoById(String id);

    /**
     * Get a Tag by its name.
     * @param name the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    Tag getTagByName(String name);

    /**
     * Get a Tag by its id.
     * @param id the id to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    Tag getTagById(String id);
}

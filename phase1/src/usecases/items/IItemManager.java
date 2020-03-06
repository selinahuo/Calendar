package usecases.items;

import entities.CalendarEvent;
import entities.Tag;

import entities.Memo;

import java.util.ArrayList;

public interface IItemManager {
    /**
     *
     * @param name
     * @param note
     * @param memoid
     * @param userid
     * @return
     */
    boolean createMemo(String name, String note, String memoid, String userid);

    /**
     * Create a Tag.
     * @param name the Tag to create
     * @param userID the unique id of this tag
     * @return true if event creation was successful, false otherwise
     */
    boolean createTag(String name, int count, String userID);

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
     * Get a Tag by its id.
     * @param id the id to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    Tag getTagById(String id);

    CalendarEvent[] getEventsByTagNameAndUserID(String tagName, String userID);

    CalendarEvent[] getEventsByMemoIDAndUserID(String tagID, String userID);
}

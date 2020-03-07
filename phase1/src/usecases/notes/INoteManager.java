package usecases.notes;

import entities.CalendarEvent;
import entities.Tag;

import entities.Memo;

public interface INoteManager {
    /**
     *
     * @param name
     * @param note
     * @param userID
     * @return
     */
    boolean createMemo(String name, String note, String userID);
    boolean attachMemoToEvent(String memoID, String eventID, String userID);

        /**
         * Create a Tag.
         * @param name the Tag to create
         * @param userID the unique id of this tag
         * @return true if event creation was successful, false otherwise
         */
//    boolean createTag(String name, int count, String userID);

    boolean tagEvent(String eventID, String tagName, String userID);

    /**
     * Get a Memo by its name.
     * @param name the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
//    Memo getMemoByName(String name);

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
//    Tag getTagById(String id);

    CalendarEvent[] getEventsByTagNameAndUserID(String tagName, String userID);
    CalendarEvent[] getEventsByMemoIDAndUserID(String memoID, String userID);
    Memo[] getMemosByUserID(String userID);
}

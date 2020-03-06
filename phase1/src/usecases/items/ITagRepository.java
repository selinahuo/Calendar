package usecases.items;

import entities.Tag;

public interface ITagRepository {
    /**
     * Saves a tag.
     * @param tag the tag to save
     * @return true if event creation was successful, false otherwise
     */
    boolean saveTag(Tag tag);

    /**
     * Fetch a Tag by its id.
     * @param id the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    Tag fetchTagByID(String id);

    /**
     * Fetch a Tag by its Name.
     * @param name the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    Tag fetchTagByNameAndUserID(String name, String userID);
}

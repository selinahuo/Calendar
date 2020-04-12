package usecases.notes;
import entities.Tag;
import java.util.ArrayList;

public interface ITagRepository {
    /**
     * Save a tag
     * @param tag tag to save
     */
    void saveTag(Tag tag);

    /**
     * Fetch tag by its unique ID
     * @param tagID unique tag ID
     * @return matching tag entity
     */
    Tag fetchTagByTagID(String tagID);

    /**
     * Fetch tag by its unique ID and owner ID
     * @param tagID unique tag ID
     * @param ownerID unique owner ID
     * @return matching tag entity
     */
    Tag fetchTagByTagIDAndOwnerID(String tagID, String ownerID);

    /**
     * Fetch a list of tags by owner ID
     * @param ownerID unique owner ID
     * @return list of tag entities
     */
    ArrayList<Tag> fetchTagsByOwnerID(String ownerID);

    /**
     * Fetch a tag by its name and owner ID
     * @param name tag name
     * @param ownerID unique owner ID
     * @return matching tag entity
     */
    Tag fetchTagByNameAndOwnerID(String name, String ownerID);

    /**
     * Edit a tag's name
     * @param tagID unique tag ID
     * @param name the new name
     * @param ownerID owner of the tag
     * @return whether the edit was successful
     */
    boolean editTagName(String tagID, String name, String ownerID);

    /**
     * Increment a tag's count
     * @param tagID unique tag ID
     * @param ownerID owner of the tag
     * @return whether the edit was successful
     */
    boolean editTagCountAdd(String tagID, String ownerID);

    /**
     * decrement a tag's count
     * @param tagID unique tag ID
     * @param ownerID owner of the tag
     * @return whether the edit was successful
     */
    boolean editTagCountRemove(String tagID, String ownerID);

    /**
     * Deletes a tag
     * @param tagID unique tag ID
     * @param ownerID owner of the tag
     * @return whether the deletion was successful
     */
    boolean deleteTag(String tagID, String ownerID);
}

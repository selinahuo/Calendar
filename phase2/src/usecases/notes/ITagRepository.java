package usecases.notes;

import entities.Tag;
import java.util.ArrayList;

public interface ITagRepository {
    // write - Tags
    boolean saveTag(Tag tag);

    // Fetch - singular - Tags
    Tag fetchTagByTagID(String tagID);
    Tag fetchTagByTagIDAndOwnerID(String tagID, String ownerID);
    Tag fetchTagByTagIDAndCollaboratorID(String tagID, String collaboratorID);
    Tag fetchTagByTagIDAndUserID(String tagID, String userID);

    // Fetch - plural - Tags
    // Generic
    ArrayList<Tag> fetchTagsByOwnerID(String ownerID);
    ArrayList<Tag> fetchTagsByCollaboratorID(String collaboratorID);
    ArrayList<Tag> fetchTagsByUserID(String userID);

    // Name - Tags
    ArrayList<Tag> fetchTagsByNameAndOwnerID(String name, String ownerID);
    ArrayList<Tag> fetchTagsByNameAndCollaboratorID(String name, String collaboratorID);
    ArrayList<Tag> fetchTagsByNameAndUserID(String name, String userID);

    // edit - Tags
    boolean editTagName(String tagID, String name, String ownerID);
    boolean editTagID(String tagID, String newID, String ownerID);
    boolean editTagCountAdd(String tagID, String ownerID);
    boolean editTagCountRemove(String tagID, String ownerID);

    // delete - Tag
    boolean deleteTag(String tagID, String ownerID);
}

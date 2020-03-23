package usecases.notes;

import entities.Tag;
import entities.Memo;

import java.util.ArrayList;


public class TagManager {
    private ITagRepository repository;

    public TagManager(ITagRepository repository) {
        this.repository = repository;
    }

    // save - Tag
    public String createTag(String tagID, String name, String userID) {
        Tag tag = new Tag(name, tagID, userID);
        boolean success = repository.saveTag(tag);
        if (success) {
            return tag.getTagID();
        } else {
            return null;
        }
    }

    // get - singular - Tags
    public Tag getTagByTagID(String tagID) {return repository.fetchTagByTagID(tagID);}

    public Tag getTagByTagIDAndOwnerID(String tagID, String ownerID){
        return repository.fetchTagByTagIDAndOwnerID(tagID, ownerID);
    }
    public Tag getTagByTagIDAndCollaboratorID(String tagID, String collaboratorID){
        return repository.fetchTagByTagIDAndCollaboratorID(tagID, collaboratorID);
    }
    public Tag getTagByTagIDAndUserID(String tagID, String userID){
        return repository.fetchTagByTagIDAndUserID(tagID, userID);
    }

    // get - plural - Tags
    // Generic
    public ArrayList<Tag> getTagsByOwnerID(String ownerID){
        return repository.fetchTagsByOwnerID(ownerID);
    }

    public ArrayList<Tag> getTagsByCollaboratorID(String collaboratorID){
        return repository.fetchTagsByCollaboratorID(collaboratorID);
    }

    public ArrayList<Tag> getTagsByUserID(String userID){
        return repository.fetchTagsByUserID(userID);
    }


    // Name - Tags
    public ArrayList<Tag> getTagsByNameAndOwnerID(String name, String ownerID){
        return repository.fetchTagsByNameAndOwnerID(name, ownerID);
    }
    public ArrayList<Tag> getTagsByNameAndCollaboratorID(String name, String collaboratorID){
        return repository.fetchTagsByNameAndCollaboratorID(name, collaboratorID);
    }
    public ArrayList<Tag> getTagsByNameAndUserID(String name, String userID){
        return repository.fetchTagsByNameAndUserID(name, userID);
    }

    // edit - Tags
    boolean editTagName(String tagID, String name, String OwnerID){
        return repository.editTagName(tagID, name, OwnerID);
    }

    boolean editTagID(String tagID, String newID, String OwnerID){
        return repository.editTagID(tagID, newID, OwnerID);
    }

    boolean editTagCountAdd(String tagID, String ownerID){
        return repository.editTagCountAdd(tagID, ownerID);
    }

    boolean editTagCountRemove(String tagID, String ownerID){
        return repository.editTagCountRemove(tagID, ownerID);
    }

    // delete - Tag
    boolean deleteTag(String tagID, String ownerID){
        return repository.deleteTag(tagID, ownerID);
    }
}

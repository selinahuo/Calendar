package usecases.notes;

import entities.CalendarEvent;
import entities.Tag;

import java.util.ArrayList;
import java.util.Calendar;


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

    // get - plural - Tags
    // Generic
    public ArrayList<Tag> getTagsByOwnerID(String ownerID){
        return repository.fetchTagsByOwnerID(ownerID);
    }

    // Name - Tags
    public ArrayList<Tag> getTagsByNameAndOwnerID(String name, String ownerID){
        return repository.fetchTagsByNameAndOwnerID(name, ownerID);
    }

    // edit - Tags
    boolean editTagName(String tagID, String name, String newName, String OwnerID){
        return repository.editTagName(tagID, name, newName, OwnerID);
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

    //Events
    public ArrayList<CalendarEvent> getEventsByTagIDAndOwnerID(String tagID, String ownerID){
        return repository.fetchEventsByTagIDAndOwnerID(tagID, ownerID);
    }
}

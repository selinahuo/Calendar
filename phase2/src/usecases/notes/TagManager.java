package usecases.notes;

import entities.CalendarEvent;
import entities.Tag;
import usecases.events.EventManager;
import usecases.events.IEventDeletionObserver;

import java.util.ArrayList;
import java.util.Calendar;


public class TagManager implements IEventDeletionObserver {
    private ITagRepository repository;
    private EventManager eventManager;

    public TagManager(ITagRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    // save - Tag
    public String createTag(String name, String userID) {
        Tag tag = new Tag(name, userID);
        repository.saveTag(tag);
        return tag.getTagID();
    }

    // get - singular - Tags
    public Tag getTagByTagID(String tagID) {
        return repository.fetchTagByTagID(tagID);
    }

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
    boolean editTagName(String tagID, String name, String OwnerID){
        return repository.editTagName(tagID, name, OwnerID);
    }

    // delete - Tag
    boolean deleteTag(String tagID, String ownerID) {
        Tag tag = repository.fetchTagByTagIDAndOwnerID(tagID, ownerID);
        if (tag.getCount() <= 0) {
            return repository.deleteTag(tagID, ownerID);
        }
        return false;
    }

    //Events
    public ArrayList<CalendarEvent> getEventsByTagIDAndOwnerID(String tagID, String ownerID) {
        return eventManager.getEventsByTagIDAndOwnerID(tagID, ownerID);
    }

    // tag/untag tags
    public boolean addTagToEvent(String tagID, String eventID, String ownerID) {
        Tag tag = repository.fetchTagByTagIDAndOwnerID(tagID, ownerID);
        CalendarEvent event = eventManager.getEventByIDAndOwnerID(eventID, ownerID);
        if (tag != null && event != null) {
            ArrayList<String> tags = event.getTagIDs();
            if (!tags.contains(tagID)) {
                tags.add(tagID);
                eventManager.editTagIDs(eventID, tags, ownerID);
                repository.editTagCountAdd(tagID, ownerID);
            }
            return true;
        }
        return false;
    }

    public boolean removeTagFromEvent(String tagID, String eventID, String ownerID) {
        Tag tag = repository.fetchTagByTagIDAndOwnerID(tagID, ownerID);
        CalendarEvent event = eventManager.getEventByIDAndOwnerID(eventID, ownerID);
        if (tag != null && event != null) {
            ArrayList<String> tags = event.getTagIDs();
            if (tags.contains(tagID)) {
                tags.remove(tagID);
                eventManager.editTagIDs(eventID, tags, ownerID);
                repository.editTagCountRemove(tagID, ownerID);
            }
            return true;
        }
        return false;
    }

    @Override
    public void handleEventDeletion(CalendarEvent event) {
        String ownerID = event.getUserID();
        for (String tagID : event.getTagIDs()) {
            repository.editTagCountRemove(tagID, ownerID);
        }
    }
}

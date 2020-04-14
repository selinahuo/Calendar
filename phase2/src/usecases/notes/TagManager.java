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
    /**
     * Constructor for TagManager.
     *
     * @param repository the repository associated with tag
     * @param eventManager the eventManager of the calendar events that the tags are associated to
     */
    public TagManager(ITagRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }
    /**
     * Create an Individual Tag
     *
     * @param name the name of this alert
     * @param userID the user that this alert will notify
     * @return tag
     */
    // save - Tag
    public String createTag(String name, String userID) {
        Tag tag = new Tag(name, userID);
        repository.saveTag(tag);
        return tag.getTagID();
    }

    /**
     * Retrieve tag by its individual ID
     *
     * @param tagID the alertID of this tag.
     * @return the desired tag.
     */
    public Tag getTagByTagID(String tagID) {
        return repository.fetchTagByTagID(tagID);
    }

    /**
     * Retrieve tag by its individual ID
     *
     * @param tagID the tagID of this tag.
     * @param ownerID the ID of owner of the tag.
     * @return the desired tag.
     */
    public Tag getTagByTagIDAndOwnerID(String tagID, String ownerID){
        return repository.fetchTagByTagIDAndOwnerID(tagID, ownerID);
    }

    /**
     * Retrieve all tags currently belonging to the user with the identifier ownerID.
     *
     * @param ownerID userID of the respected user.
     * @return list of tags.
     */
    public ArrayList<Tag> getTagsByOwnerID(String ownerID){
        return repository.fetchTagsByOwnerID(ownerID);
    }

    /**
     * Retrieve a tag by its name and ownerID
     *
     * @param name the name of this tag.
     * @param ownerID the ID of the owner of the tag
     * @return the desired tag.
     */
    public Tag getTagByNameAndOwnerID(String name, String ownerID){
        return repository.fetchTagByNameAndOwnerID(name, ownerID);
    }

    /**
     * Edit the tag's name.
     *
     * @param tagID the tagID of this tag
     * @param name the new name of this tag
     * @param ownerID the ownerID of the user that is associated with this tag
     * @return true is modification is successful
     */
    boolean editTagName(String tagID, String name, String ownerID){
        return repository.editTagName(tagID, name, ownerID);
    }

    /**
     * Deletes the tag if it is no longer contained by any event.
     *
     * @param tagID the tagID of this tag
     * @param ownerID the ownerID of the user that is associated with this tag
     * @return true if the tag is deleted
     */
    public boolean deleteTag(String tagID, String ownerID) {
        Tag tag = repository.fetchTagByTagIDAndOwnerID(tagID, ownerID);
        if (tag != null){
            if (tag.getCount() <= 0) {
                return repository.deleteTag(tagID, ownerID);
            }
        }
        return false;
    }


    /**
     * Adds the tag to  event.
     *
     * @param tagID the tagID of this tag
     * @param ownerID the ownerID of the user that is associated with this tag
     * @return true if the tag is deleted
     */
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

    /**
     * Removes the tag from an event.
     *
     * @param eventID the ID of the event the tag will be removed from.
     * @param ownerID the ownerID of the user that is associated with this tag
     * @return true if the tag is removed
     */
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

    /**
     * Handle event deletion by removing count of tags associated with deleted event
     *
     * @param event event which was deleted and reacted to
     */
    @Override
    public void handleEventDeletion(CalendarEvent event) {
        String ownerID = event.getUserID();
        for (String tagID : event.getTagIDs()) {
            repository.editTagCountRemove(tagID, ownerID);
        }
    }
}

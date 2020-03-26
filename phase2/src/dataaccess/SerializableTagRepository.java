package dataaccess;

import usecases.events.EventManager;
import entities.CalendarEvent;
import entities.Tag;
import usecases.notes.ITagRepository;
import usecases.notes.TagManager;

import java.util.ArrayList;

public class SerializableTagRepository extends SerializableRepository<Tag> implements ITagRepository {
        public SerializableTagRepository() {
            super("tags.ser");
        }
        private EventManager eventManager;
        private TagManager tagManager;

    @Override
    public boolean saveTag(Tag tag) {
        ArrayList<Tag> tags = deserialize();
        tags.add(tag);
        serialize(tags);
        return true;
    }

    @Override
    public Tag fetchTagByTagID(String tagID) {
        return fetchSingular((Tag tag) -> tag.getTagID() != null && tag.getTagID().equals(tagID));
    }

    @Override
    public Tag fetchTagByTagIDAndOwnerID(String tagID, String ownerID) {
        return fetchSingular((Tag tag) ->
                tag.getTagID() != null && tag.getTagID().equals(tagID)
                        && tag.getUserID() != null && tag.getUserID().equals(ownerID));
    }

    @Override
    public ArrayList<Tag> fetchTagsByOwnerID(String ownerID) {
        return fetchPlural((Tag tag) -> tag.getUserID() != null && tag.getUserID().equals(ownerID));
    }

    @Override
    public ArrayList<Tag> fetchTagsByNameAndOwnerID(String name, String ownerID) {
        return fetchPlural((Tag tag) ->
                tag.getName() != null && tag.getName().equals(name)
                        && tag.getUserID() != null && tag.getUserID().equals(ownerID)
        );
    }

    @Override
    public boolean editTagName(String tagID, String name, String newName, String ownerID) {
        ArrayList<Tag> tagsArr = deserialize();
        for (Tag tags: tagsArr) {
            if (tags.getTagID().equals(tagID)) {
                tags.setName(newName);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean editTagCountAdd(String tagID, String ownerID) {
        ArrayList<Tag> tagsArr = deserialize();
        for (Tag tags: tagsArr) {
            if (tags.getTagID().equals(tagID)) {
                tags.addCount();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editTagCountRemove(String tagID, String ownerID) {
        ArrayList<Tag> tagsArr = deserialize();
        for (Tag tags: tagsArr) {
            if (tags.getTagID().equals(tagID)) {
                tags.removeCount();
                if (tags.getCount() == 0){
                    deleteTag(tags.getTagID(), tags.getUserID());
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteTag(String tagID, String ownerID) {return false;}

    @Override
    public boolean addTagToEvent(String tagID, String eventID, String ownerID) {
        CalendarEvent event = eventManager.getEventByIDAndUserID(eventID, ownerID);
        ArrayList<String> ids = event.getTagIDs();
        ids.add(tagID);
        event.setTagIDs(ids);
        tagManager.getTagByTagID(tagID).addCount();
        return true;
    }

    @Override
    public boolean removeTagFromEvent(String tagID, String eventID, String ownerID) {
        CalendarEvent event = eventManager.getEventByIDAndUserID(eventID, ownerID);
        ArrayList<String> ids = event.getTagIDs();
        ArrayList<String> newIDs = new ArrayList<>();
        boolean removed = false;
        for (String id : ids){
            if (!id.equals(tagID)){
                newIDs.add(id);
            }else{
                removed = true;
                tagManager.getTagByTagID(tagID).removeCount();
            }
        }
        event.setTagIDs(newIDs);
        return removed;
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByTagIDAndOwnerID(String tagID, String ownerID){
        ArrayList<CalendarEvent> events =  eventManager.getEventsByOwnerID(ownerID);
        ArrayList<CalendarEvent> newEvents = new ArrayList<>();
        for (CalendarEvent event : events){
            for (String id : event.getTagIDs()){
                if (id.equals(tagID)){
                    newEvents.add(event);
                }
            }
        }
        return newEvents;
    }
}

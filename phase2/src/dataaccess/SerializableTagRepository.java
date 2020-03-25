package dataaccess;

import entities.Memo;
import entities.Tag;
import usecases.notes.ITagRepository;

import java.util.ArrayList;

public class SerializableTagRepository extends SerializableRepository<Tag> implements ITagRepository {
        public SerializableTagRepository() {
            super("tags.ser");
        }

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
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteTag(String tagID, String ownerID) {return false;}
}

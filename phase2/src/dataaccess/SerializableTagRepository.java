package dataaccess;

import entities.Tag;
import usecases.notes.ITagRepository;

import java.util.ArrayList;

class SerializableTagRepository extends SerializableRepository<Tag> implements ITagRepository {
    SerializableTagRepository() {
            super("./tags.ser");
        }

    @Override
    public void saveTag(Tag tag) {
        saveItem(tag);
    }

    @Override
    public Tag fetchTagByTagID(String tagID) {
        return fetchSingular((Tag tag) -> tag.getTagID().equals(tagID));
    }

    @Override
    public Tag fetchTagByTagIDAndOwnerID(String tagID, String ownerID) {
        return fetchSingular((Tag tag) -> tag.getTagID().equals(tagID) && tag.getUserID().equals(ownerID));
    }

    @Override
    public ArrayList<Tag> fetchTagsByOwnerID(String ownerID) {
        return fetchPlural((Tag tag) -> tag.getUserID().equals(ownerID));
    }

    @Override
    public Tag fetchTagByNameAndOwnerID(String name, String ownerID) {
        return fetchSingular((Tag tag) -> tag.getName().equals(name) && tag.getUserID().equals(ownerID));
    }

    @Override
    public boolean editTagName(String tagID, String name, String ownerID) {
        return editSingular(
                (Tag tag) -> tag.getTagID().equals(tagID) && tag.getUserID().equals(ownerID),
                (Tag tag) -> tag.setName(name)
        );
    }


    @Override
    public boolean editTagCountAdd(String tagID, String ownerID) {
        return editSingular(
                (Tag tag) -> tag.getTagID().equals(tagID) && tag.getUserID().equals(ownerID),
                Tag::addCount
        );
    }

    @Override
    public boolean editTagCountRemove(String tagID, String ownerID) {
        return editSingular(
                (Tag tag) -> tag.getTagID().equals(tagID) && tag.getUserID().equals(ownerID),
                Tag::removeCount
        );
    }

    @Override
    public boolean deleteTag(String tagID, String ownerID) {
        return deleteSingular((Tag tag) -> tag.getTagID().equals(tagID) && tag.getUserID().equals(ownerID));
    }
}

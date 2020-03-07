package dataaccess;

import entities.Tag;
import usecases.notes.ITagRepository;

import java.io.*;
import java.util.ArrayList;

public class SerializableTagRepository extends SerializableRepository<Tag> implements ITagRepository {
    public SerializableTagRepository() {
        super("tags.ser");
    }

    /**
     * Saves a tag.
     *
     * @param tag the tag to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean saveTag(Tag tag) {
        ArrayList<Tag> tags = deserialize();
        tags.add(tag);
        serialize(tags);
        return true;
    }

    /**
     * Fetch a Tag by its id.
     *
     * @param id the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    @Override
    public Tag fetchTagByID(String id) {
        ArrayList<Tag> tags = deserialize();
        for (Tag tag : tags) {
            if (tag.getTagID().equals(id)) {
                return tag;
            }
        }
        return null;
    }

    /**
     * Fetch a Tag by its Name.
     *
     * @param name   the name to filter by.
     * @param userID
     * @return the corresponding Tag or null if it does not exist
     */
    @Override
    public Tag fetchTagByNameAndUserID(String name, String userID) {
        ArrayList<Tag> tags = deserialize();
        for (Tag tag: tags) {
            if (tag.getName().equals(name) && tag.getUserID().equals(userID)) {
                return tag;
            }
        }
        return null;
    }
}

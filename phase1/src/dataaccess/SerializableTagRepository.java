package dataaccess;

import entities.Tag;
import usecases.items.ITagRepository;

import java.io.*;
import java.util.ArrayList;

public class SerializableTagRepository implements ITagRepository {
    private ArrayList<Tag> tags;

    public SerializableTagRepository() {
        this.tags = new ArrayList<Tag>();
    }

    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream("tags");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.tags);
            oos.close();
            fos.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void deserialize() {
        try {
            FileInputStream fis = new FileInputStream("tags");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.tags = (ArrayList<Tag>) ois.readObject();
            ois.close();
            fis.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    /**
     * Saves a tag.
     *
     * @param tag the tag to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean saveTag(Tag tag) {
        deserialize();
        this.tags.add(tag);
        serialize();
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
        deserialize();
        for (Tag tag : tags) {
            if (tag.getTagID() == id) {
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
        deserialize();
        for (Tag tag: tags) {
            if (tag.getName().equals(name) && tag.getUserID().equals(userID)) {
                return tag;
            }
        }
        return null;
    }
}

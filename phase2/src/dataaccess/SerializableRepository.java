package dataaccess;

import java.io.*;
import java.util.ArrayList;

/**
 * Abstract class for repositories taking advantage of serialization
 * @param <T> type of object stored by repository
 */
abstract class SerializableRepository<T> {
    private ArrayList<T> items = new ArrayList<T>();
    private String serFile;

    SerializableRepository(String serFile) {
        this.serFile = serFile;
    }

    protected void serialize() {
        serialize(items);
    }

    protected void serialize(ArrayList<T> items) {
        try { // Write items to serialize file
            this.items = items;
            FileOutputStream fos = new FileOutputStream(serFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.items);
            oos.close();
            fos.close();
        } catch(IOException ioe) {
            // Exit if fail to write to file
            ioe.printStackTrace();
            System.exit(1);
        }
    }

    protected ArrayList<T> deserialize() {
        try { // Try to read a serial file and return the contents
            FileInputStream fis = new FileInputStream(serFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.items = (ArrayList<T>) ois.readObject();
            ois.close();
            fis.close();
            return this.items;
        } catch(IOException ioe) {
            try {
                // if the file does not exist create the file and then try to deserialize
                serialize();
                return deserialize();
            } catch(Exception e) {
                ioe.printStackTrace();
                System.exit(1);
            }
        } catch(ClassNotFoundException c) {
            c.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    /**
     * Save an item to repository
     * @param item item to save
     */
    protected void saveItem(T item) {
        deserialize();
        items.add(item);
        serialize();
    }

    /**
     * Fetch first item matching filter
     * @param filter test to match item
     * @return item matching filter or null if does not exist
     */
    protected T fetchSingular(IFilter<T> filter) {
        deserialize();
        for (T item : items) {
            if (filter.filter(item)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Fetch all items matching filter
     * @param filter test to match item
     * @return list of items matching filter
     */
    protected ArrayList<T> fetchPlural(IFilter<T> filter) {
        deserialize();
        ArrayList<T> entities = new ArrayList<>();
        for (T item : items) {
            if (filter.filter(item)) {
                entities.add(item);
            }
        }
        return entities;
    }

    /**
     * Edit first item matching filter
     * @param filter test to match item
     * @param edit method to edit the item
     * @return return whether edit was successful
     */
    protected boolean editSingular(IFilter<T> filter, IEdit<T> edit) {
        T itemToEdit = fetchSingular(filter);
        if (itemToEdit != null) {
            edit.edit(itemToEdit);
            serialize();
            return true;
        }
        return false;
    }

    /**
     * Delete first item matching filter
     * @param filter test to match item
     * @return whether deletion was successful
     */
    protected boolean deleteSingular(IFilter<T> filter) {
        T itemToDelete = fetchSingular(filter);
        if (itemToDelete != null) {
            items.remove(itemToDelete);
            serialize();
            return true;
        }
        return false;
    }

    /**
     * Delete all items matching filter
     * @param filter test to match items
     */
    protected void deletePlural(IFilter<T> filter) {
        ArrayList<T> itemsToDelete = fetchPlural(filter);
        for (T item : itemsToDelete) {
            items.remove(item);
            serialize();
        }
    }
}

package dataaccess;

import java.io.*;
import java.util.ArrayList;

public abstract class SerializableRepository<T> {
    private ArrayList<T> items;
    private String serFile;

    public SerializableRepository(String serFile) {
        this.items = new ArrayList<T>();
        this.serFile = serFile;
    }

    protected void serialize() {
        serialize(items);
    }

    protected void serialize(ArrayList<T> items) {
        try {
            this.items = items;
            FileOutputStream fos = new FileOutputStream(serFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.items);
            oos.close();
            fos.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }

    protected ArrayList<T> deserialize() {
        try {
            FileInputStream fis = new FileInputStream(serFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.items = (ArrayList<T>) ois.readObject();
            ois.close();
            fis.close();
            return this.items;
        } catch(IOException ioe) {
            try {
                serialize(this.items);
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

    protected void saveItem(T item) {
        deserialize();
        items.add(item);
        serialize();
    }

    protected T fetchSingular(IFilter<T> filter) {
        deserialize();
        for (T item : items) {
            if (filter.filter(item)) {
                return item;
            }
        }
        return null;
    }

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

    protected boolean editSingular(IFilter<T> filter, IEdit<T> edit) {
        T itemToEdit = fetchSingular(filter);
        if (itemToEdit != null) {
            edit.edit(itemToEdit);
            serialize();
            return true;
        }
        return false;
    }

    protected boolean deleteSingular(IFilter<T> filter) {
        T itemToDelete = fetchSingular(filter);
        if (itemToDelete != null) {
            items.remove(itemToDelete);
            serialize();
            return true;
        }
        return false;
    }

    protected void deletePlural(IFilter<T> filter) {
        ArrayList<T> itemsToDelete = fetchPlural(filter);
        for (T item : itemsToDelete) {
            items.remove(item);
            serialize();
        }
    }
}

package dataaccess;

import entities.Tag;
import entities.User;
import usecases.users.IUserRepository;

import java.io.*;
import java.util.ArrayList;

public class SerializableUserRepository implements IUserRepository {
    private ArrayList<User> users;

    public SerializableUserRepository() {
        this.users = new ArrayList<User>();
    }

    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream("users.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.users);
            oos.close();
            fos.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void deserialize() {
        try {
            FileInputStream fis = new FileInputStream("users.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.users = (ArrayList<User>) ois.readObject();
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
     * Fetch User by their username.
     *
     * @param username events must have this name
     * @return User with specified username
     */
    @Override
    public User fetchUserByUsername(String username) {
        deserialize();
        for (User user: this.users) {
            if (user.getUsername() == username) {
                return user;
            }
        }
        return null;
    }
}

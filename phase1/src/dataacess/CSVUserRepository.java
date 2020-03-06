package dataacess;

import entities.User;
import usecases.users.IUserRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class CSVUserRepository implements IUserRepository {

    private static String pathToCsv = "User.csv";
    private final static String cvsSplitBy = ";";
    /**
     * Save a User.
     *
     * @param user the User to save
     * @return true if User creation was successful, false otherwise
     */
    @Override
    public boolean saveUser(User user) {
        System.out.println("user creating");
        try{
            FileWriter fw = new FileWriter("User.csv");
            String userID = user.getUserID();
            String userName = user.getUsername();
            String userPassword = user.getPassword();
            String userInfo = userID + userName + userPassword;
            fw.write(userInfo);
            fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Fetch an User by their ID.
     *
     * @param userID the ID to filter by.
     * @return the corresponding User or null if it does not exist
     */
    @Override
    public User fetchUserByID(String userID) {
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(pathToCsv))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] event = line.split(cvsSplitBy);
                if (event[0].equals(userID)){
                    br.close();
                    return new User(event[0], event[1], event[2]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fetch User by their username.
     *
     * @param username events must have this name
     * @return User with specified username
     */
    @Override
    public User fetchUserByUsername(String username) {
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(pathToCsv))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] event = line.split(cvsSplitBy);
                if (event[1].equals(username)){
                    br.close();
                    return new User(event[0], event[1], event[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

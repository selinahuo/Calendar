package controller;

import controller.viewmodels.ListModel;
import entities.User;

import java.util.ArrayList;

public class UserAdapter {
    public static String createUserString(User user) {
        return String.format("ID: %s | User: %s", user.getUserID(), user.getUsername());
    }

    public static ListModel createUserListModel(ArrayList<User> users) {
        ArrayList<String> userList = new ArrayList<>();
        for (User user: users) {
            userList.add(createUserString(user));
        }
        return new ListModel(userList);
    }
}

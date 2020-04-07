package controller;

import controller.viewmodels.ListModel;
import entities.User;

import java.util.ArrayList;

class UserAdapter {
    private static String createUserString(User user) {
        return String.format("ID: %s | User: %s", user.getUserID(), user.getUsername());
    }

    static ListModel createUserListModel(ArrayList<User> users) {
        ArrayList<String> userList = new ArrayList<>();
        for (User user: users) {
            userList.add(createUserString(user));
        }
        return new ListModel(userList);
    }
}

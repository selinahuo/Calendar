package controller;

import usecases.UseCaseManager;

public class Controller {
    private UseCaseManager useCaseManager;

    public Controller(UseCaseManager useCaseManager) {
        this.useCaseManager = useCaseManager;
    }

    public boolean createUser(String username, String password) {
        return useCaseManager.createUser(username, password);
    }

    public String loginUser(String username, String password) {
        return useCaseManager.loginUser(username, password);
    }
}

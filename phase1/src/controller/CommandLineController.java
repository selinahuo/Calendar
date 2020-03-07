package controller;

import controller.viewmodels.AuthenticatedModel;
import usecases.IUseCaseManager;

public class CommandLineController {
//    private IUseCaseManager useCaseManager;
//
//    public CommandLineController(IUseCaseManager useCaseManager) {
//        this.useCaseManager = useCaseManager;
//    }

    public AuthenticatedModel authenticateUser(String username, String password) {
        if (username.equals("Michael") && password.equals("123")) {
            return new AuthenticatedModel("user id", username);
        }
        return null;
    }
}

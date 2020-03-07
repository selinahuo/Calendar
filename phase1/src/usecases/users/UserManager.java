package usecases.users;

import entities.User;

class UserManager implements IUserManager {
    private IUserRepository repository;

    public UserManager(IUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Get a User by their Username.
     *
     * @param username the user's username
     * @param password
     * @return the matching User
     */
    @Override
    public String authenticateUser(String username, String password) {
        User user = this.repository.fetchUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user.getUserID();
        }
        return null;
    }

    // === END OF PHASE 1 === //

    /**
     * Create and save a new User.
     *
     * @param username the user's username
     * @param password the user's password
     * @return whether User creation was successful
     */
//    @Override
//    public boolean createUser(String username, String password) {
//        return false;
//    }

    /**
     * Get a User by their ID.
     *
     * @param id the user's ID
     * @return the matching User
     */
//    @Override
//    public User getUserByID(String id) {
//        return null;
//    }

    /**
     * Get a User by their Username.
     *
     * @param username the user's username
     * @return the matching User
     */
//    @Override
//    public User getUserByUsername(String username) {
//        return null;
//    }
}

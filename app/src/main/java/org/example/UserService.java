package org.example;

public class UserService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User createUser(String Username, String Password, PasswordRepo pwdRepo){
        User existing = repo.findByUsername(Username);
        if (existing != null) {
            return null;
        }

        User user = new User(Username, Password, pwdRepo);
        repo.saveUser(user);
        return user;
    }

    
}

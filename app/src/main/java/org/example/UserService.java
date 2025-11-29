package org.example;

public class UserService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User createUser(String Username, String Password, PasswordRepo pwdRepo){
        User existing = repo.findByUsername(Username);
        if (existing != null) {
            throw new IllegalArgumentException("Username Taken");
        }

        User user = new User(Username, Password, pwdRepo);
        repo.saveUser(user);
        return user;
    }

    
}

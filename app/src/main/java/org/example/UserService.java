package org.example;

public class UserService {
    private final UserRepo repo;
    private final EncryptionService service;

    public UserService(UserRepo repo, EncryptionService service) {
        this.repo = repo;
        this.service =service;
    }

    public User createUser(String Username, String Password, PasswordRepo pwdRepo){
        if (Username.isEmpty()){
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (Password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        User existing = repo.findByUsername(Username);
        if (existing != null) {
            throw new IllegalArgumentException("Username Taken");
        }

        User user = new User(Username, Password, pwdRepo);
        repo.saveUser(user);
        return user;
    }

    
}

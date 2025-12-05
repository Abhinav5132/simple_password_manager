package org.example;

import java.util.NoSuchElementException;

// TODO: needs to have save to file
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

        String hashedPwd = service.Hash(Password);

        User user = new User(Username, hashedPwd, pwdRepo);
        repo.saveUser(user);
        return user;
    }

    public User Login(String username, String password) {
        if (username.isEmpty()){
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        User existing = repo.findByUsername(username);
        if (existing == null) {
            throw new NoSuchElementException("User does not exist");
        }
        String hashedPwd = service.Hash(password);

        if (!existing.getPassword().equals(hashedPwd)) {
            throw new IllegalArgumentException("Incorrect Password");
        }
        return existing;

    }
    
}

package org.example;
import org.example.*;

public class User {
    private String Username;
    private String Password;
    private PasswordRepo passwordRepo;

    public User(String Username, String Password, PasswordRepo pwdRepo) {
        this.Username = Username;
        this.Password = Password;
        this.passwordRepo= pwdRepo;
    }
    public String getUsername() { return Username; }
    public String getPassword() { return Password; }
}

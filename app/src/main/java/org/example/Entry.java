package org.example;

public class Entry {
    private String name;
    private String username;
    private String password;

    public Entry(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword(EncryptionService service) {
        return service.Decrypt(this.password);
    }
}

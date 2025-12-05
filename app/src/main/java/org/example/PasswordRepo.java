package org.example;

import java.util.ArrayList;
import java.util.List;

public class PasswordRepo {
    private final List<Entry> entries = new ArrayList<>();
    private final EncryptionService service;

    public PasswordRepo(EncryptionService service){
        this.service = service;
    }

    public Entry[] getEntryByName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Site name cannot be null");
        }

        if(name.equals("") || name.equals(" ")){
            throw new IllegalArgumentException("Site name cannot be empty");
        }

        name = name.trim();
        List<Entry> matches = new ArrayList<>();
        for (Entry entry: entries) {
            if(entry.getName().equals(name)){
                matches.add(entry);
            }
        }

        if(matches.isEmpty()) {
            throw new IllegalArgumentException("No entry with Site name:" + name);
        }

        return matches.toArray(new Entry[0]);
    }

    public void addEntry(Entry entry) {
        if(entries.contains(entry)){
            throw new IllegalArgumentException("Entry for" + entry.getName() + "already exists");
        }

        if(entry == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }
        this.entries.add(entry);
    }

    public void removeEntry(Entry entry) {
        
        if(entry == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }

        if(!this.entries.contains(entry)) {
            throw new IllegalArgumentException("Repo does not contain entry");
            
        }
        this.entries.remove(entry);
    }
    
    public void editEntry(Entry entry) {

    }

    public void createEntry(String name ,String username, String password) {
        if (name == null){
            throw new IllegalArgumentException("Entry name cannot be null");
        }
        if(username == null ) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        if(password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        if (username.equals(" ")) {
            username = "";
        }

        if(
            name.equals("") || 
            password.equals("") || 
            password.equals(" ") || 
            name.equals(" ")) 
        {
            throw new IllegalArgumentException("Name and password fields cannot be empty");
        }
        password = service.Encrypt(password);
        Entry entry = new Entry(name.trim(), username.trim(), password.trim());

        this.addEntry(entry);
    }

    public int count(){
        return entries.size();
    }
    
}

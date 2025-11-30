package org.example;

import java.util.ArrayList;
import java.util.List;

public class PasswordRepo {
    private final List<Entry> entries = new ArrayList<>();

    public Entry getEntryByName() {
        return null;
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
        Entry entry = new Entry(name, username, password);

        this.addEntry(entry);
    }

    public int count(){
        return entries.size();
    }
    
}

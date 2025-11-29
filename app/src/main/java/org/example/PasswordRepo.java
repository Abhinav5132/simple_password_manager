package org.example;

import java.util.ArrayList;
import java.util.List;

public class PasswordRepo {
    private final List<Entry> entries = new ArrayList<>();

    public Entry getEntryByName() {
        return null;
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public void removeEntry(Entry entry) {

    }
    
    public void editEntry(Entry entry) {

    }

    public int count(){
        return entries.size();
    }
    
}

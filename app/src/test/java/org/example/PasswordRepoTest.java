package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class PasswordRepoTest {

    @Test
    public void addEntryShouldAddEntryToRepo() {
        PasswordRepo repo = new PasswordRepo();
        Entry entry = new Entry("Google.com", "alicia", "pass1234");

        repo.addEntry(entry);
        assertEquals(1, repo.count());
    }

    @Test
    public void addEntryShouldThrowWhenDuplicate() {
        PasswordRepo repo = new PasswordRepo();
        Entry entry = new Entry("Google.com", "alicia", "pass1234");

        repo.addEntry(entry);

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.addEntry(entry);
        });
        assertEquals(1, repo.count());

    }

    @Test
    public void addEntryShouldThrowWhenNull() {
        PasswordRepo repo = new PasswordRepo();
        Entry entry = null;

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.addEntry(entry);
        });
        assertEquals(0, repo.count());

    }

    @Test
    public void removeEntryShouldRemoveEntryFromRepo() {
        PasswordRepo repo = new PasswordRepo();
        Entry entry = new Entry("Google.com", "alicia", "pass1234");
        repo.addEntry(entry);
        assertEquals(1, repo.count());

        repo.removeEntry(entry);
        assertEquals(0, repo.count());
    }

    @Test
    public void removeEntryShouldThrowWhenNull() {
        PasswordRepo repo = new PasswordRepo();
        Entry entry = null; 

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.removeEntry(entry);
        });
        
    }

    @Test
    public void removeEntryShouldThrowIfNotExists() {
        PasswordRepo repo = new PasswordRepo();
        Entry entry = new Entry("Google.com", "alicia", "pass1234");
        
        assertThrows(IllegalArgumentException.class, ()-> {
            repo.removeEntry(entry);
        });
    }

    @Test
    public void createEntryShouldCreateAnEntryAndAddToRepo() {
        PasswordRepo repo = new PasswordRepo();
        repo.createEntry("Google.com", "alicia", "pass1234");

        assertEquals(1, repo.count());
    }

    @Test 
    public void createEntryShouldThrowIfAnyFeildIsNull(){
        PasswordRepo repo = new PasswordRepo();

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.createEntry(null, "alicia", "pass1234");
        });

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.createEntry("Google.com", null, "pass1234");
        });

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.createEntry("Google.com", "alicia", null);
        });
        
        assertThrows(IllegalArgumentException.class, ()-> {
            repo.createEntry(null, null, null);
        });
    }
}
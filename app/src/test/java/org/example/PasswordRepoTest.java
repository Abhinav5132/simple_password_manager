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
}

package org.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PasswordRepoTest {
    @Test
    public void addEntryShouldAddEntryToRepo() {
        PasswordRepo repo = new PasswordRepo();
        Entry entry = new Entry("Google.com", "alicia", "pass1234");

        repo.addEntry(entry);
        assertEquals(1, repo.count());
    }
}

package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

 
import org.junit.Test;

public class PasswordRepoTest {

    @Test
    public void addEntryShouldAddEntryToRepo() {
        EncryptionService service = mock(EncryptionService.class);

        PasswordRepo repo = new PasswordRepo(service);
        Entry entry = new Entry("Google.com", "alicia", "pass1234");

        repo.addEntry(entry);
        assertEquals(1, repo.count());
    }

    @Test
    public void addEntryShouldThrowWhenDuplicate() {
        EncryptionService service = mock(EncryptionService.class);

        PasswordRepo repo = new PasswordRepo(service);
        Entry entry = new Entry("Google.com", "alicia", "pass1234");

        repo.addEntry(entry);

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.addEntry(entry);
        });
        assertEquals(1, repo.count());

    }

    @Test
    public void addEntryShouldThrowWhenNull() {
        EncryptionService service = mock(EncryptionService.class);

        PasswordRepo repo = new PasswordRepo(service);
        Entry entry = null;

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.addEntry(entry);
        });
        assertEquals(0, repo.count());

    }

    @Test
    public void removeEntryShouldRemoveEntryFromRepo() {
        EncryptionService service = mock(EncryptionService.class);
        PasswordRepo repo = new PasswordRepo(service);
        Entry entry = new Entry("Google.com", "alicia", "pass1234");
        repo.addEntry(entry);
        assertEquals(1, repo.count());

        repo.removeEntry(entry);
        assertEquals(0, repo.count());
    }

    @Test
    public void removeEntryShouldThrowWhenNull() {
        EncryptionService service = mock(EncryptionService.class);
        PasswordRepo repo = new PasswordRepo(service);
        Entry entry = null; 

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.removeEntry(entry);
        });
        
    }

    @Test
    public void removeEntryShouldThrowIfNotExists() {
        EncryptionService service = mock(EncryptionService.class);
        PasswordRepo repo = new PasswordRepo(service);
        Entry entry = new Entry("Google.com", "alicia", "pass1234");
        
        assertThrows(IllegalArgumentException.class, ()-> {
            repo.removeEntry(entry);
        });
    }

    @Test
    public void createEntryShouldCreateAnEntryAndAddToRepo() {
        EncryptionService service = mock(EncryptionService.class);
        PasswordRepo repo = new PasswordRepo(service);

        when(service.Encrypt("pass1234")).thenReturn("encrypted_pass");

        repo.createEntry("Google.com", "alicia", "pass1234");

        assertEquals(1, repo.count());
    }

    @Test 
    public void createEntryShouldThrowIfAnyFeildIsNull(){
        EncryptionService service = mock(EncryptionService.class);
        PasswordRepo repo = new PasswordRepo(service);

        when(service.Encrypt("pass1234")).thenReturn("encrypted_pass");


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

    @Test 
    public void createEntryShouldThrowIfNameOrPasswordIsEmpty(){
        EncryptionService service = mock(EncryptionService.class);
        PasswordRepo repo = new PasswordRepo(service);

        when(service.Encrypt("pass1234")).thenReturn("encrypted_pass");

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.createEntry("", "alicia", "pass1234");
        });

        // username can be empty and and test fails if exception thrown for this
        repo.createEntry("Google.com", "", "pass1234");
        
        assertThrows(IllegalArgumentException.class, ()-> {
            repo.createEntry("Google.com", "alicia", "");
        });
        
        assertThrows(IllegalArgumentException.class, ()-> {
            repo.createEntry("", "", "");
        });

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.createEntry(" ", " ", " ");
        });
    }

    @Test
    public void createEntryShouldEncryptPasswordBeforeBeingStoredInRepo() {
        EncryptionService service = mock(EncryptionService.class);
        PasswordRepo repo = new PasswordRepo(service);

        when(service.Encrypt("pass1234")).thenReturn("encrypted_pass");

        repo.createEntry("Google.com","alicia", "pass1234");

        Entry[] stored = repo.getEntryByName("Google.com");

        when(service.Decrypt("encrypted_pass")).thenReturn("pass1234");

        assertNotNull(stored);
        assertEquals("pass1234", stored[0].getPassword(service));

        verify(service).Encrypt("pass1234");
        verify(service).Decrypt("encrypted_pass");

    }

    @Test
    public void getEntryByNameShouldThrowIfNotExists(){
        EncryptionService service = mock(EncryptionService.class);
        PasswordRepo repo = new PasswordRepo(service);

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.getEntryByName("Google.com");
        });
    }

    @Test
    public void getEntryByNameShouldThrowIfNameIsNullOrEmpty(){
        EncryptionService service = mock(EncryptionService.class);
        PasswordRepo repo = new PasswordRepo(service);
        
        when(service.Encrypt("pass1234")).thenReturn("encrypted_pass");
        repo.createEntry("Google.com","alicia", "pass1234");

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.getEntryByName(null);
        });

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.getEntryByName("");
        });

        assertThrows(IllegalArgumentException.class, ()-> {
            repo.getEntryByName(" ");
        });

        Entry[] entry = repo.getEntryByName("Google.com "); // trailing white space should not throw
    }

    @Test
    public void getEntryShouldReturnMultipleIfEntriesWithSameNamesExist(){
        EncryptionService service = mock(EncryptionService.class);
        PasswordRepo repo = new PasswordRepo(service);

        when(service.Encrypt("pass1234")).thenReturn("encrypted_pass");
        when(service.Encrypt("pass5678")).thenReturn("encrypted_pass2");

        repo.createEntry("Google.com","alicia", "pass1234");
        repo.createEntry("Google.com","bob", "pass5678");

        Entry[] result = repo.getEntryByName("Google.com");
        assertEquals(2, result.length);
    }
}
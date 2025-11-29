package org.example;

import org.junit.Test;
import org.example.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    
    @Test
    public void createUser_ShouldSaveNewUser_WhenUsernameNotTaken() {
        UserRepo repo = mock(UserRepo.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        UserService service = new UserService(repo, encryptionService);
        PasswordRepo pwdRepo = mock(PasswordRepo.class);

        when(repo.findByUsername("Bob")).thenReturn(null);

        User create = service.createUser("Bob", "pass123", pwdRepo);

        assertNotNull(create);
        assertEquals("Bob", create.getUsername());

        verify(repo).saveUser(any(User.class));
    }

    @Test
    public void createUser_ShouldThrowException_WhenUsernameTaken(){
        UserRepo repo = mock(UserRepo.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        UserService service = new UserService(repo, encryptionService);
        PasswordRepo pwdRepo = mock(PasswordRepo.class);

        when(repo.findByUsername("alice")).thenReturn(new User("alice", "whatever", pwdRepo));

        assertThrows(IllegalArgumentException.class, () -> {
            service.createUser("alice", "pass123", pwdRepo);
        });

        verify(repo, never()).saveUser(any()); // verify that the user never gets saved.
    }

    @Test
    public void passwordNullShouldThrowAnException(){
        UserRepo repo = mock(UserRepo.class);
        EncryptionService encryptionService = mock(EncryptionService.class);

        UserService service = new UserService(repo, encryptionService);
        PasswordRepo pwdRepo = mock(PasswordRepo.class);


        when(repo.findByUsername("Bob")).thenReturn(null);
        

        assertThrows(NullPointerException.class, () -> {
            service.createUser("alice", null, pwdRepo);
        });
        
        verify(repo, never()).saveUser(any());
    }

    @Test
    public void passwordEmptyShouldThrowAnException(){
        UserRepo repo = mock(UserRepo.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        UserService service = new UserService(repo, encryptionService);
        PasswordRepo pwdRepo = mock(PasswordRepo.class);

        when(repo.findByUsername("Bob")).thenReturn(null);
        

        assertThrows(IllegalArgumentException.class, () -> {
            service.createUser("alice", "", pwdRepo);
        });
        
        verify(repo, never()).saveUser(any());
    }

    @Test 
    public void usernameEmptyShouldThrowAnException() {
        UserRepo repo = mock(UserRepo.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        UserService service = new UserService(repo, encryptionService);
        PasswordRepo pwdRepo = mock(PasswordRepo.class);
        
        assertThrows(IllegalArgumentException.class, () ->
            service.createUser("", "pass123", pwdRepo)
        );
        
        verify(repo, never()).saveUser(any());
    }

    @Test 
    public void passwordShouldBeHashed() {
        UserRepo repo = mock(UserRepo.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        UserService service = new UserService(repo, encryptionService);
        PasswordRepo pwdRepo = mock(PasswordRepo.class);

        when(encryptionService.Hash("pass123")).thenReturn("Hashed_password");

        User user = service.createUser("alice", "pass123", pwdRepo);

        assertEquals(user.getPassword(), "Hashed_password");
        assertNotEquals(user.getPassword(), "pass123");
    }   
}

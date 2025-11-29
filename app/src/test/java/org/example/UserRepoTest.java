package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.Test;


public class UserRepoTest {
    
    @Test
    public void saveUserShouldStoreUserInArray() {
        UserRepo repo = new UserRepo();
        PasswordRepo pwdRepo = mock(PasswordRepo.class);
        User user = new User("alice", "pass1314", pwdRepo);

        repo.saveUser(user);

        assertEquals(1, repo.count());
    }

    @Test
    public void saveUserShouldThrowIfUserIsNull(){
        UserRepo repo = new UserRepo();

        assertThrows(IllegalArgumentException.class, () -> {
            repo.saveUser(null);
        });
    }

    @Test
    public void saveUserShouldThrowIfUserDuplicate(){
        UserRepo repo = new UserRepo();
        PasswordRepo pwdRepo = mock(PasswordRepo.class);
        User user = new User("alice", "pass1234", pwdRepo);
        repo.saveUser(user);

        assertThrows(IllegalArgumentException.class, ()->{
            repo.saveUser(user);
        });
    }

    @Test
    public void findByUsername_ShouldReturnUser_WhenExists() {
        UserRepo repo = new UserRepo();
        PasswordRepo pwdRepo = mock(PasswordRepo.class);
        User user = new User("alice", "pass1234", pwdRepo);
        repo.saveUser(user);

        User found_user = repo.findByUsername("alice");
        assertEquals(user, found_user);
    }
}

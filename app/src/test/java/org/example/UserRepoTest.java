package org.example;

import static org.junit.Assert.assertEquals;
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
}

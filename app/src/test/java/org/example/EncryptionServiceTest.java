package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class EncryptionServiceTest {
    
    @Test
    public void encryptShouldEncryptThePassword(){
        EncryptionService service = new EncryptionService();
        String encrypted = service.Encrypt("pass123");

        assertNotNull(encrypted);
        assertNotEquals("pass123", encrypted);

    }

}

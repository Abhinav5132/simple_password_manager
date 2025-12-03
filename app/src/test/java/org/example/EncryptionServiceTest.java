package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class EncryptionServiceTest {
    
    @Test
    public void encryptShouldEncryptThePassword(){
        EncryptionService service = new EncryptionService();
        String encrypted = service.Encrypt("pass123");

        assertNotNull(encrypted);
        assertNotEquals("pass123", encrypted);
    }

    @Test
    public void encrpytShouldThrowIfPasswordIsEmptyOrNull(){
        EncryptionService service = new EncryptionService();

        assertThrows(NullPointerException.class, ()->{
            service.Encrypt(null);
        });

        assertThrows(IllegalArgumentException.class, ()->{
            service.Encrypt("");
        });

        assertThrows(IllegalArgumentException.class, ()->{
            service.Encrypt(" ");
        });

        
        assertNotEquals("pass123 ", service.Encrypt("pass123 "));
    }

    @Test
    public void decryptShouldDecryptThePassword(){
        EncryptionService service = new EncryptionService();
        String encrypted = service.Encrypt("pass123");
        String decrypted = service.Decrypt(encrypted);

        assertNotNull(decrypted);
        assertEquals("pass123", decrypted);
    }

}

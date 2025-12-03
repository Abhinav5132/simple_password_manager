package org.example;

import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.util.Base64;

public class EncryptionService {
    private SecretKey key;

    public EncryptionService(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            this.key = keyGen.generateKey();
        } catch(Exception e) {
            throw new RuntimeException("Error generating AES key", e);
        }
    }

    public String Encrypt(String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            return null;
        }
    }

    public String Decrypt(String password) {
        return null;
    }

    public String Hash(String password) {
        return null;
    }
}

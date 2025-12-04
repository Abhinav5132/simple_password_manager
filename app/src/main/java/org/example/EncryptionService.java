package org.example;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

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
        if (password == null){
            throw new NullPointerException("Password cannot be null");
        }
        password = password.trim();
        if (password.isEmpty() || password.isBlank()){
            throw new IllegalArgumentException("Password cannot be empty");
        }

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, this.key);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            throw new RuntimeException("Error encypting with AES key", e);
        }
    }

    public String Decrypt(String encypted_password) {
        if (encypted_password == null){
            throw new NullPointerException("Password cannot be null");
        }
        encypted_password = encypted_password.trim();
        if (encypted_password.isEmpty() || encypted_password.isBlank()){
            throw new IllegalArgumentException("Password cannot be empty");
        }

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(encypted_password);
            byte[] decryptedBytes = cipher.doFinal(decoded);
            return new String(decryptedBytes);

        } catch (Exception e) {
            throw new RuntimeException("AES decryption failed", e);
        }
    }

    public String Hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8)); 

            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hashBytes.length; i++) {
                final String hex = Integer.toHexString(0xff & hashBytes[i]);
                if(hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        }
        catch(Exception e){
            throw new RuntimeException("SHA-256 hashing failed", e);
        }
    }

}

package io.stackunderflow.flow.domain.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    public static String hash(String clearString){

        byte[] salt = "".getBytes();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(salt);
        byte[] hashed = md.digest(clearString.getBytes());

        return new String(hashed, StandardCharsets.UTF_8);
    }
}

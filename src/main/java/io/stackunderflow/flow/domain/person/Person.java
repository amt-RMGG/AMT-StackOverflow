package io.stackunderflow.flow.domain.person;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Person {

    private String username;
    private String email;
    private String firstname;
    private String lastname;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;


    public boolean authenticate(String clearTextPassword){
        return hashPassword(clearTextPassword).equals(encryptedPassword);
    }

    public Person deepClone() {
        return this.toBuilder()
                .build();
    }

    public static class PersonBuilder {
        public PersonBuilder clearTextPassword(String clearTextPassword){
            if(clearTextPassword == null || clearTextPassword.isEmpty()){
                throw new java.lang.IllegalArgumentException("Password is mandatory");
            }
            encryptedPassword = hashPassword(clearTextPassword);
            return this;
        }

        public Person build(){

            if(username == null || username.isEmpty())
                throw new IllegalArgumentException("Username is mandatory");

            if(encryptedPassword == null || encryptedPassword.isEmpty())
                throw new IllegalArgumentException("Password is mandatory");

            if(firstname == null || firstname.isEmpty())
                throw new IllegalArgumentException("First name is mandatory");

            if(lastname == null || lastname.isEmpty())
                throw new IllegalArgumentException("Last name is mandatory");

            if(email == null || email.isEmpty())
                throw new IllegalArgumentException("Email is mandatory");

            Person newPerson = new Person(username, email, firstname, lastname, encryptedPassword);
            return newPerson;
        }
    }

    private static String hashPassword(String clearPassword){

        byte[] salt = "".getBytes();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(salt);
        byte[] hashedPassword = md.digest(clearPassword.getBytes());

        return new String(hashedPassword, StandardCharsets.UTF_8);
    }
}

package io.stackunderflow.flow.infrastructure.persistence.jdbc;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import org.junit.jupiter.api.Test;

import javax.naming.NamingException;

public class jdbcPersonTest {
    @Test
    public void insertPersonTest () throws RegistrationFailedException, NamingException {
        /*JdbcPersonRepository personRepository = new JdbcPersonRepository();

        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getUsername()).thenReturn("alice");
        Mockito.when(person.getEmail()).thenReturn("");
        Mockito.when(person.getFirstname()).thenReturn("alice");
        Mockito.when(person.getLastname()).thenReturn("anderson");
        Mockito.when(person.getEncryptedPassword()).thenReturn("abc");

        personRepository.save(person);

        assertEquals(personRepository.findByUsername("alice").get().getEmail(), "alice@alicenet.com");*/
    }
}

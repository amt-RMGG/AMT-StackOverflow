package io.stackunderflow.flow.infrastructure.persistence.jdbc;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.person.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

public class jdbcPersonTest {
    @Test
    public void insertPersonTest () throws RegistrationFailedException, NamingException {
        JdbcPersonRepository personRepository = new JdbcPersonRepository(InitialContext.doLookup("jdbc/StackUnderFlowDS"));

        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getUsername()).thenReturn("alice");
        Mockito.when(person.getEmail()).thenReturn("");
        Mockito.when(person.getFirstname()).thenReturn("alice");
        Mockito.when(person.getLastname()).thenReturn("anderson");
        Mockito.when(person.getEncryptedPassword()).thenReturn("abc");

        /*Person person = Person.builder()
                .email("alice@alicenet.com")
                .firstname("alice")
                .lastname("anderson")
                .username("alice")
                .encryptedPassword("abc")
                .build();*/
        personRepository.save(person);

        assertEquals(personRepository.findByUsername("alice").get().getEmail(), "alice@alicenet.com");
    }
}

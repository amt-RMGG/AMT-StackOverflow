package io.stackunderflow.flow.application.identitymgmt;

import io.stackunderflow.flow.application.answer.ProposeAnswerCommand;
import io.stackunderflow.flow.application.identitymgmt.authenticate.AuthenticateCommand;
import io.stackunderflow.flow.application.identitymgmt.authenticate.AuthenticationFailedException;
import io.stackunderflow.flow.application.identitymgmt.login.RegisterCommand;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.answer.Answer;
import io.stackunderflow.flow.domain.person.Person;
import io.stackunderflow.flow.infrastructure.persistence.IPersonRepository;
import io.stackunderflow.flow.infrastructure.persistence.IQuestionRepository;
import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionId;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Optional;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IdentityManagementFacadeTest {

    private IPersonRepository personRepository = Mockito.mock(IPersonRepository.class);
    private Person alice = Mockito.mock(Person.class);
    private IdentityManagementFacade facade = new IdentityManagementFacade(personRepository);

    public void mockEmptyRepository()
    {
        when(personRepository.findByUsername(anyString())).thenReturn(Optional.empty());
    }

    public void mockRepositoryContainingAlice()
    {
        when(alice.getUsername()).thenReturn("Alice");
        when(alice.authenticate("asdf")).thenReturn(true);
        when(alice.authenticate("wrongpassword")).thenReturn(false);
        when(personRepository.findByUsername("Alice")).thenReturn(Optional.of(alice));
    }

    public RegisterCommand registerExample1() {
        return RegisterCommand.builder()
                .username("Alice")
                .firstname("Alice")
                .lastname("Anderson")
                .email("Alice@alicenet.net")
                .clearTextPassword("asdf")
                .build();
    }

    public AuthenticateCommand loginExample1() {
        return AuthenticateCommand.builder()
                .username("Alice")
                .clearTextPassword("asdf")
                .build();
    }

    public AuthenticateCommand loginExample2() {
        return AuthenticateCommand.builder()
                .username("Alice")
                .clearTextPassword("wrongpassword")
                .build();
    }

    @Test
    public void registerTest() throws RegistrationFailedException {
        mockEmptyRepository();
        assertDoesNotThrow(() -> facade.register(registerExample1()));
        Mockito.verify(personRepository).save(anyObject());

        mockRepositoryContainingAlice();
        assertThrows(RegistrationFailedException.class, () -> facade.register(registerExample1()));
    }

    @Test
    public void authenticateTest() throws AuthenticationFailedException {
        mockEmptyRepository();
        assertThrows(AuthenticationFailedException.class, () -> facade.authenticate(loginExample1()));

        mockRepositoryContainingAlice();
        assertThrows(AuthenticationFailedException.class, () -> facade.authenticate(loginExample2()));
        assertDoesNotThrow(() -> facade.authenticate(loginExample1()));
        assertEquals("Alice", facade.authenticate(loginExample1()).getUsername());
    }

}

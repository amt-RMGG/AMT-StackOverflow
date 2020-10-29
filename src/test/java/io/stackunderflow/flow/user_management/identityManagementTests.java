package io.stackunderflow.flow.user_management;

import io.stackunderflow.flow.application.identitymgmt.authenticate.AuthenticationFailedException;
import io.stackunderflow.flow.application.identitymgmt.authenticate.CurrentUserDTO;
import io.stackunderflow.flow.infrastructure.persistence.memory.InMemoryPersonRepository;
import io.stackunderflow.flow.application.identitymgmt.IdentityManagementFacade;
import io.stackunderflow.flow.application.identitymgmt.authenticate.AuthenticateCommand;
import io.stackunderflow.flow.application.identitymgmt.login.RegisterCommand;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.person.IPersonRepository;
import io.stackunderflow.flow.domain.person.Person;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class identityManagementTests {

    static private IPersonRepository personRepository = new InMemoryPersonRepository();
    import static org.junit.jupiter.api.Assertions.assertThrows;

    static private IPersonRepository personRepository = new JdbcPersonRepository();

    static private IdentityManagementFacade facade = new IdentityManagementFacade(personRepository);

    @BeforeAll
    public static void validRegistration() throws RegistrationFailedException {
        facade.register(registerExample1());
    }

    public static RegisterCommand registerExample1() {
        return RegisterCommand.builder()
                .username("rob")
                .firstname("r")
                .lastname("r")
                .email("r@r.com")
                .clearTextPassword("asdf")
                .build();
    }

    public static AuthenticateCommand loginExample1() {
        return AuthenticateCommand.builder()
                .username("rob")
                .clearTextPassword("asdf")
                .build();
    }

    public static AuthenticateCommand loginExample2() {
        return AuthenticateCommand.builder()
                .username("rob")
                .clearTextPassword("abc")
                .build();
    }

    @Test
    public void registerTest() throws RegistrationFailedException {
        Optional<Person> personOption = personRepository.findByUsername("rob");
        assertFalse(personOption.isEmpty());
        assertEquals(personOption.get().getUsername(), "rob");
        assertThrows(RegistrationFailedException.class, () -> facade.register(registerExample1()));
    }

    @Test
    public void loginTest() throws AuthenticationFailedException {
        UserDTO currentUser = facade.authenticate(loginExample1());
        assertEquals(currentUser.getUsername(), "rob");
    }

    @Test
    public void failedLoginTest() {
        try {
            UserDTO currentUser = facade.authenticate(loginExample2());
            assertNotEquals(currentUser.getUsername(), "rob");
        }
        catch (AuthenticationFailedException exception)
        {
            exception.printStackTrace();
        }
    }*/
}

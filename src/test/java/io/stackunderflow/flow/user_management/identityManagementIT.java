package io.stackunderflow.flow.user_management;

import io.stackunderflow.flow.application.identitymgmt.IdentityManagementFacade;
import io.stackunderflow.flow.application.identitymgmt.authenticate.AuthenticateCommand;
import io.stackunderflow.flow.application.identitymgmt.authenticate.AuthenticationFailedException;
import io.stackunderflow.flow.application.identitymgmt.authenticate.UserDTO;
import io.stackunderflow.flow.application.identitymgmt.login.RegisterCommand;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.person.Person;
import io.stackunderflow.flow.infrastructure.persistence.IPersonRepository;
import io.stackunderflow.flow.infrastructure.persistence.jdbc.JdbcPersonRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Arquillian.class)
public class identityManagementIT {

    @Inject
    IPersonRepository personRepository;

    @Inject
    IdentityManagementFacade facade;

    @ArquillianResource
    private URL baseURL;

    @Deployment(testable = true)
    public static WebArchive createDeployment()
    {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "arquillian-managed.war")
                .addPackages(true, "io.stackunderflow.flow");
        System.out.println(archive.toString(true));
        return archive;
    }

    @BeforeAll
    public void validRegistration() throws RegistrationFailedException {
        //facade.register(registerExample1());
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
    }
}


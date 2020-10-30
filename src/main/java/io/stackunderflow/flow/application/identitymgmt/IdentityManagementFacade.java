package io.stackunderflow.flow.application.identitymgmt;

import io.stackunderflow.flow.application.identitymgmt.authenticate.AuthenticateCommand;
import io.stackunderflow.flow.application.identitymgmt.authenticate.AuthenticationFailedException;
import io.stackunderflow.flow.application.identitymgmt.authenticate.UserDTO;
import io.stackunderflow.flow.application.identitymgmt.login.RegisterCommand;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.infrastructure.persistence.IPersonRepository;
import io.stackunderflow.flow.domain.person.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("IdentityManagementFacade")
public class IdentityManagementFacade {

    private IPersonRepository personRepository;

    @Inject
    public IdentityManagementFacade(IPersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public void register(RegisterCommand command) throws RegistrationFailedException {

        //Check if another user has already the same username
        Person existingPersonWithSameUsername = personRepository.findByUsername(command.getUsername()).orElse(null);

        if(existingPersonWithSameUsername != null){
            throw new RegistrationFailedException("Username already exist!");
        }

        try {
            Person newPerson = Person.builder()
                    .username(command.getUsername())
                    .firstname(command.getFirstname())
                    .lastname(command.getLastname())
                    .email(command.getEmail())
                    .clearTextPassword(command.getClearTextPassword())
                    .build();
            personRepository.save(newPerson);
        }catch (Exception e){
            throw new RegistrationFailedException(e.getMessage());
        }
    }

    public UserDTO authenticate(AuthenticateCommand command) throws AuthenticationFailedException {
        Person person = personRepository.findByUsername(command.getUsername())
                .orElseThrow(()-> new AuthenticationFailedException("User not found!"));

        boolean success = person.authenticate(command.getClearTextPassword());
        if(!success)
            throw new AuthenticationFailedException("Password verification failed!");

        UserDTO currentUser = UserDTO.builder()
                .username(person.getUsername())
                .firstname(person.getFirstname())
                .lastname(person.getLastname())
                .email(person.getEmail())
                .build();

        return currentUser;
    }

}

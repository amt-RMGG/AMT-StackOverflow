package io.stackunderflow.flow.application.identitymgmt;

import io.stackunderflow.flow.application.identitymgmt.authenticate.UserDTO;
import io.stackunderflow.flow.application.question.QuestionsDTO;
import io.stackunderflow.flow.domain.person.IPersonRepository;
import io.stackunderflow.flow.domain.person.Person;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.question.Question;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserFacade {

    private IPersonRepository personRepository;

    public UserFacade(IPersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public UserDTO getUser(UserQuery query){
        Optional<Person> result = personRepository.findByUsername(query.getUsername());

        if(result.isEmpty()){
            return null;
        }

        Person user = result.get();


        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
    }
}

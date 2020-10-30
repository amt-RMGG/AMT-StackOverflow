package io.stackunderflow.flow.domain.person;

import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    public void builderGeneratesID()
    {
        Person p = Person.builder()
                .firstname("Alice")
                .lastname("Anderson")
                .email("alice@alicenet.net")
                .username("Alice")
                .encryptedPassword("asdf")
                .build();
        assertNotNull(p.getId());
    }

    @Test
    public void mandatoryFields()
    {
        assertThrows(IllegalArgumentException.class, () -> Person.builder().build());
    }
}

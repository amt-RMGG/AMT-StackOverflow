package io.stackunderflow.flow.domain.question;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionIdTest {

    @Test
    public void noParameterConstructorShouldProvideId(){
        QuestionId questionId = new QuestionId();
        assertNotEquals("", questionId.asString());
    }

    @Test
    public void invalidStringParameterConstructor(){
        assertThrows(IllegalArgumentException.class, () -> {
            new QuestionId("1234");
        });
    }

    @Test
    public void stringUUIDParameterConstructor(){
        UUID uuid = UUID.randomUUID();
        QuestionId questionId = new QuestionId(uuid.toString());
        assertEquals(uuid.toString(), questionId.asString());
    }
    @Test
    public void UUIDParameterConstructor(){
        UUID uuid = UUID.randomUUID();
        QuestionId questionId = new QuestionId(uuid);
        assertEquals(uuid.toString(), questionId.asString());
    }

    @Test
    public void shouldThrowExceptionWhenNullUUID(){
        UUID uuid = null;
        assertThrows(NullPointerException.class, () -> {
            new QuestionId(uuid);
        });
    }

}

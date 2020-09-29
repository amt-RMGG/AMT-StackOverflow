package io.stackunderflow.flow.domain.question;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class QuestionIdTest {

    @Test
    public void noParameterConstructorShouldProvideId(){
        QuestionId questionId = new QuestionId();
        Assert.assertNotEquals("", questionId.asString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidStringParameterConstructor(){
        QuestionId questionId = new QuestionId("1234");
    }

    @Test
    public void stringUUIDParameterConstructor(){
        UUID uuid = UUID.randomUUID();
        QuestionId questionId = new QuestionId(uuid.toString());
        Assert.assertEquals(uuid.toString(), questionId.asString());
    }
    @Test
    public void UUIDParameterConstructor(){
        UUID uuid = UUID.randomUUID();
        QuestionId questionId = new QuestionId(uuid);
        Assert.assertEquals(uuid.toString(), questionId.asString());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenNullUUID(){
        UUID uuid = UUID.randomUUID();
        uuid = null;
        QuestionId questionId = new QuestionId(uuid);
    }

}

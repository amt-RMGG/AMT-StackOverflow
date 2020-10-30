package io.stackunderflow.flow.domain.question;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuestionTest {
    @Test
    public void builderGeneratesIDandAssignsDefaultType()
    {
        Question q = Question.builder().build();
        assertNotNull(q.getId());
        assertEquals(q.getQuestionType(), QuestionType.DEFAULT);
    }
}

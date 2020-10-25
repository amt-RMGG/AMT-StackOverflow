package io.stackunderflow.flow.application.question;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionId;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

import java.util.LinkedList;

import static org.mockito.Mockito.verify;

public class QuestionManagementFacadeTests {
    static private IQuestionRepository questionRepository = Mockito.mock(IQuestionRepository.class);
    static private QuestionFacade facade = new QuestionFacade(questionRepository);

    public static ProposeQuestionCommand commandExample1()
    {
        return ProposeQuestionCommand.builder()
                .author("Douglas Adams")
                .title("H2G2")
                .text("What is the meaning of life, the universe and everything")
                .build();
    }

    public static QuestionQuery queryExample1()
    {
        return QuestionQuery.builder()
                .author("Doesn't Exist")
                .id(new QuestionId("0")).build();
    }

    @Test
    public void proposeQuestionTest() throws RegistrationFailedException {
        facade.proposeQuestion(commandExample1());
        verify(questionRepository).save(Mockito.anyObject());
    }

    @Test
    public void getQuestionsFromEmptyRepositoryTest() {
        Mockito.when(questionRepository.find(null))
                .thenReturn(new LinkedList<Question>());
        assertEquals(facade.getQuestions(null), QuestionsDTO.builder()
                .questions(new LinkedList<>())
                .build());
    }
}

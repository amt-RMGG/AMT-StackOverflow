package io.stackunderflow.flow.application.question;

import io.stackunderflow.flow.application.answer.ProposeAnswerCommand;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.answer.Answer;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionId;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class QuestionManagementFacadeTest {
    static private IQuestionRepository questionRepository = Mockito.mock(IQuestionRepository.class);
    static private QuestionFacade facade = new QuestionFacade(questionRepository);

    public static ProposeQuestionCommand questionCommandExample1()
    {
        return ProposeQuestionCommand.builder()
                .author("The mice")
                .title("H2G2")
                .text("What is the meaning of life, the universe and everything")
                .build();
    }

    public static ProposeAnswerCommand answerCommandExample1(String questionId)
    {
        return ProposeAnswerCommand.builder()
                .author("Deep thought")
                .questionId(questionId)
                .text("42")
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
        ArgumentCaptor<Question> argument = ArgumentCaptor.forClass(Question.class);
        facade.proposeQuestion(questionCommandExample1());
        verify(questionRepository).save(argument.capture());
        assertEquals("H2G2", argument.getValue().getTitle());
    }

    @Test
    public void proposeAnswerTest() throws RegistrationFailedException {
        ArgumentCaptor<Answer> argument = ArgumentCaptor.forClass(Answer.class);
        facade.proposeAnswer(answerCommandExample1(Mockito.anyString()));
        verify(questionRepository).saveAnswer(argument.capture());
        assertEquals("42", argument.getValue().getText());
    }

    @Test
    public void getQuestionsFromEmptyRepositoryTest() {
        Mockito.when(questionRepository.find(Mockito.anyObject()))
                .thenReturn(new LinkedList<Question>());
        assertEquals(facade.getQuestions(null), QuestionsDTO.builder()
                .questions(new LinkedList<>())
                .build());
    }
}

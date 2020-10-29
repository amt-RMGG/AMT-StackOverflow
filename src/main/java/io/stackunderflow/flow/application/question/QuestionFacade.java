package io.stackunderflow.flow.application.question;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.question.Question;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

public class QuestionFacade {

    private IQuestionRepository questionRepository;

    public QuestionFacade(IQuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public void proposeQuestion(ProposeQuestionCommand command){
        try {
            Question submittedQuestion = Question.builder()
                    .author(command.getAuthor())
                    .text(command.getText())
                    .title(command.getTitle())
                    .build();

            questionRepository.save(submittedQuestion);
        } catch (RegistrationFailedException e) {
            e.printStackTrace();
        }
    }

    public QuestionsDTO getQuestions(QuestionQuery query){
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO =
                allQuestions.stream()
                        .map(question -> QuestionsDTO.QuestionDTO.builder()
                        .text(question.getText())
                        .author(question.getAuthor())
                        .title(question.getTitle())
                        .build()).collect(Collectors.toList());

        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }
}

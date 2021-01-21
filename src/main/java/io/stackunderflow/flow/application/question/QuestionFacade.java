package io.stackunderflow.flow.application.question;

import io.stackunderflow.flow.application.answer.ProposeAnswerCommand;
import io.stackunderflow.flow.application.gamification.Badge;
import io.stackunderflow.flow.application.gamification.GamificationFacade;
import io.stackunderflow.flow.application.gamification.ServerInformation;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.answer.Answer;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.question.Question;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuestionFacade {

    private IQuestionRepository questionRepository;
    private GamificationFacade gamificationFacade;

    public QuestionFacade(IQuestionRepository questionRepository, GamificationFacade gamificationFacade){
        this.questionRepository = questionRepository;
        this.gamificationFacade = gamificationFacade;
    }

    public Optional<Badge> proposeQuestion(ProposeQuestionCommand command){
        try {
            Question submittedQuestion = Question.builder()
                    .author(command.getAuthor())
                    .text(command.getText())
                    .title(command.getTitle())
                    .build();

            questionRepository.save(submittedQuestion);

            //Send a event to the gamification server
            return gamificationFacade.sendEvent(command.getAuthor(), ServerInformation.proposeQuestionEventType);
        } catch (RegistrationFailedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void proposeAnswer(ProposeAnswerCommand command) {
        try {
            Answer submittedAnswer = Answer.builder()
                    .author(command.getAuthor())
                    .text(command.getText())
                    .questionId(command.getQuestionId())
                    .build();

            questionRepository.saveAnswer(submittedAnswer);

            //Send a event to the gamification server
            // gamificationFacade.sendEvent(command.getAuthor(), ServerInformation.firstAnswerEventType);
        } catch (RegistrationFailedException e) {
            e.printStackTrace();
        }
    }

    public QuestionsDTO getQuestions(QuestionQuery query){
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO =
                allQuestions.stream()
                        .map(question -> QuestionsDTO.QuestionDTO.builder()
                        .id(question.getId().asString())
                        .text(question.getText())
                        .author(question.getAuthor())
                        .title(question.getTitle())
                        .date(question.getDate())
                        .votes(question.getVotes())
                        .answers(question.getAnswers())
                        .build()).collect(Collectors.toList());

        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }

    public QuestionsDTO searchQuestions(String search){
        Collection<Question> results = questionRepository.search(search);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO =
                results.stream()
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

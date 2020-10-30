package io.stackunderflow.flow.infrastructure.persistence.jdbc;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.application.question.QuestionQuery;
import io.stackunderflow.flow.domain.answer.Answer;
import io.stackunderflow.flow.domain.answer.AnswerId;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository extends JdbcRepository implements IQuestionRepository {

    public JdbcQuestionRepository(){}

    @Override
    public Collection<Question> find(QuestionQuery query) {
        Collection<Question> questions = new LinkedList<>();
        //Find by id
        if(query.getId() != null){
            Optional<Question> q = findById(query.getId());
            if(!q.isEmpty()) {
               questions.add(q.get());
            }
            return questions;
        }
        //if the query is empty, return the all the questions
        questions = findAll();
        return questions;
    }

    @Override
    public Collection<Question> search(String search) {
        Collection<Question> results = new LinkedList<>();

        try {
            PreparedStatement statement = dataSource.getConnection()
                    .prepareStatement("SELECT * FROM question WHERE title LIKE '%" + search + "%';");

            ResultSet rs = statement.executeQuery();

            while(rs.next()){

                QuestionId id = new QuestionId(rs.getString(1));
                String title = rs.getString(2);
                String text = rs.getString(3);
                String author = rs.getString(4);

                Question q = Question.builder()
                        .id(id)
                        .title(title)
                        .text(text)
                        .author(author)
                        .build();

                results.add(q);
            }

        }catch(SQLException e){
            System.out.println("error : " + e.getMessage());
        }
        return results;
    }


    @Override
    public void save(Question entity) throws RegistrationFailedException {

        try {
            String query = "INSERT INTO question (id, title, text, author) VALUES(?, ?, ?, ?)";
            super.executeInsertQuery(query, entity.getId().asString(), entity.getTitle(), entity.getText(), entity.getAuthor());

        }catch(RegistrationFailedException e){
            throw new RegistrationFailedException(e.getMessage());
        }
    }
    @Override
    public void saveAnswer(Answer entity) throws RegistrationFailedException {
        try {
            String query = "INSERT INTO answer (id, text, user, question) VALUES(?, ?, ?, ?)";
            super.executeInsertQuery(query, entity.getId().asString(), entity.getText(), entity.getAuthor(), entity.getQuestionId());

        }catch(RegistrationFailedException e){
            throw new RegistrationFailedException(e.getMessage());
        }
    }

    @Override
    public void remove(QuestionId id) {

    }

    @Override
    public Optional<Question> findById(QuestionId id) {

        ResultSet rs = super.fetchData("SELECT * FROM question WHERE id = ?", id.asString());
        Question q = null;
        Collection<Answer> answers;
        ResultSet votesRS = super.fetchData("select sum(vote) from questionVote where questionId=?", id.asString());

        try{
            votesRS.next();
            answers = getAnswers(id);

            if(rs.next()) {
                String title = rs.getString(2);
                String text = rs.getString(3);
                String date = rs.getString(4);
                String author = rs.getString(5);
                int votes = votesRS.getInt(1);
                q = Question.builder()
                        .id(id)
                        .title(title)
                        .text(text)
                        .author(author)
                        .date(date)
                        .answers(answers)
                        .votes(votes)
                        .build();
            }
        }catch(SQLException e){
            System.out.println("error : " + e.getMessage());
        }
        if(q == null)
            return Optional.empty();
        else
            return Optional.of(q);
    }

    private Collection<Answer> getAnswers(QuestionId questionId) {

        LinkedList<Answer> ret = new LinkedList<>();
        ResultSet rs = super.fetchData("SELECT * FROM answer WHERE answer.question = ?", questionId.asString());

        try{
            while(rs.next()) {
                AnswerId id = new AnswerId(rs.getString(1));
                String text = rs.getString(2);
                String date = rs.getString(3);
                String author = rs.getString(4);

                ResultSet votesRS = super.fetchData("select sum(vote) from answerVote where answerId=?", id.asString());
                votesRS.next();
                int vote = votesRS.getInt(1);

                Answer ans = Answer.builder()
                        .id(id)
                        .text(text)
                        .author(author)
                        .date(date)
                        .votes(vote)
                        .build();

                ret.add(ans);
            }
        }catch(SQLException e){
            System.out.println("error : " + e.getMessage());
        }
        return ret;
    }

    @Override
    public Collection<Question> findAll() {
        Collection<Question> allQuestion = new LinkedList<>();

        ResultSet rs = super.fetchData("SELECT * FROM question");

        try{
            while(rs.next()) {
                QuestionId id = new QuestionId(rs.getString(1));
                String title = rs.getString(2);
                String text = rs.getString(3);
                String date = rs.getString(4);
                String author = rs.getString(5);

                Question q = Question.builder()
                        .id(id)
                        .title(title)
                        .text(text)
                        .date(date)
                        .author(author)
                        .build();

                allQuestion.add(q);
            }
        }catch(SQLException e){
            System.out.println("error : " + e.getMessage());
        }
        return allQuestion;
    }
}

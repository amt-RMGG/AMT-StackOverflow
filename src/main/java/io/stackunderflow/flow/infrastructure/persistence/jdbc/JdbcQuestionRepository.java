package io.stackunderflow.flow.infrastructure.persistence.jdbc;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.application.question.QuestionQuery;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository extends JdbcRepository implements IQuestionRepository {

    public JdbcQuestionRepository(){}

    @Override
    public Collection<Question> find(QuestionQuery query) {

        // behavior here ... TODO to be define !
        if(query != null){
            return findAll();
        }
        //if nothing is found, return the whole repo
        return findAll();
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
    public void remove(QuestionId id) {

    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        return Optional.empty();
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
                String author = rs.getString(4);

                Question q = Question.builder()
                        .id(id)
                        .title(title)
                        .text(text)
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

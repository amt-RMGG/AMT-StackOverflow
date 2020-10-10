package io.stackunderflow.flow.infrastructure.persistence.jdbc;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.application.question.QuestionQuery;
import io.stackunderflow.flow.domain.person.Person;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@ApplicationScoped //Singleton
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository implements IQuestionRepository {

    @Resource(lookup = "jdbc/StackUnderFlowDS")
    DataSource dataSource;

    public  JdbcQuestionRepository(){
        //TODO : ça marche si on fait ça, mais devrait marcher sans, avec le " @Resource(lookup = "jdbc/StackUnderFlowDS")"
        try {
            dataSource = InitialContext.doLookup("jdbc/StackUnderFlowDS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Collection<Question> find(QuestionQuery query) {
        // behavior here ... TODO to be define !
        if(query != null){
            return findAll();
        }
        //if nothing is found, return the whole repo
        return findAll();
    }

    //TODO FACTORISER çA avec une super classe JdbcRepository
    @Override
    public void save(Question entity) throws RegistrationFailedException {

        Connection connection = null;
        PreparedStatement statement = null;
        //Insert into the db
        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(
                    "INSERT INTO question (id, title, text, author) VALUES(?, ?, ?, ?)"
            );
            statement.setString(1, entity.getId().asString());
            statement.setString(2, entity.getTitle());
            statement.setString(3, entity.getText());
            statement.setString(4, entity.getAuthor());

            statement.executeUpdate();

        }catch(SQLException e){
            throw new RegistrationFailedException(e.getMessage());

            //Close connection to the DB
        }finally {
            try{
                if(statement != null)
                    connection.close();
            }catch(SQLException se){
                //TODO : do something ?
            }
            try{
                if(connection != null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
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

        try {
            PreparedStatement statement = dataSource.getConnection()
                    .prepareStatement("SELECT * FROM question");

            ResultSet rs = statement.executeQuery();

            while(rs.next() != false){

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

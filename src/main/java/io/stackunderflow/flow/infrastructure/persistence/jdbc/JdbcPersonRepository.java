package io.stackunderflow.flow.infrastructure.persistence.jdbc;

import io.stackunderflow.flow.application.identitymgmt.UserQuery;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.answer.Answer;
import io.stackunderflow.flow.domain.person.IPersonRepository;
import io.stackunderflow.flow.domain.person.Person;
import io.stackunderflow.flow.domain.person.PersonId;
import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;


@ApplicationScoped
@Named("JdbcPersonRepository")
public class JdbcPersonRepository extends JdbcRepository implements IPersonRepository {


    public  JdbcPersonRepository(){}

    @Override
    public Optional<Person> findByUsername(String username) {

        try {
            ResultSet rs = super.fetchData("SELECT * FROM user WHERE username = ?", username);

            String email = "";
            String password = "";
            String firstname = "";
            String lastname = "";

            //Si la query n'a rien retourné
            if (!rs.next()) {
                return Optional.empty();
            }else{
                email = rs.getString(2);
                password = rs.getString(3);
                firstname = rs.getString(4);
                lastname = rs.getString(5);
            }

            Person p = Person.builder()
                    .username(username)
                    .email(email)
                    .firstname(firstname)
                    .lastname(lastname)
                    .encryptedPassword(password)
                    .build();

            return Optional.of(p);

        }catch(SQLException e){
            System.out.println(e.getCause());
        }
        return Optional.empty();
    }

    @Override
    public void save(Person entity) throws RegistrationFailedException {
        synchronized (entity.getUsername()) {
            if (!findByUsername(entity.getUsername()).isEmpty()) {
                throw new RegistrationFailedException("Cannot save/update person. Integrity constraint violation : username");
            }

            try {
                String query = "INSERT INTO user (username, email, password, firstname, lastname) VALUES(?, ?, ?, ?, ?)";
                super.executeInsertQuery(query, entity.getUsername(), entity.getEmail(), entity.getEncryptedPassword(), entity.getFirstname(), entity.getLastname());

            }catch(RegistrationFailedException e){
                throw new RegistrationFailedException(e.getMessage());
            }

        }
    }

    @Override
    public void remove(PersonId id) {

    }

    @Override
    public Optional<Person> findById(PersonId id) {
        return Optional.empty();
    }

    /*
    @Override
    public Optional<Person> findById(PersonId id) {
        try {
            ResultSet rs = super.fetchData("SELECT * FROM user WHERE id = ?", id);

            String username = "";
            String email = "";
            String password = "";
            String firstname = "";
            String lastname = "";

            //String username, email, password, firstname, lastname;

            //Si la query n'a rien retourné
            if (!rs.next()) {
                return Optional.empty();
            }else{
                username = rs.getString(1);
                email = rs.getString(2);
                password = rs.getString(3);
                firstname = rs.getString(4);
                lastname = rs.getString(5);
            }

            Person p = Person.builder()
                    .id(id)
                    .username(username)
                    .email(email)
                    .firstname(firstname)
                    .lastname(lastname)
                    .encryptedPassword(password)
                    .build();

            return Optional.of(p);

        }catch(SQLException e){
            System.out.println(e.getCause());
        }
        return Optional.empty();
    }

     */

    @Override
    public Collection<Person> findAll() {

        Collection<Person> allPerson = new LinkedList<>();

        try {
            ResultSet rs = super.fetchData("SELECT * FROM user");

            while(rs.next()){

                String username = rs.getString(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String firstname = rs.getString(4);
                String lastname = rs.getString(5);

                Person p = Person.builder()
                        .username(username)
                        .email(email)
                        .firstname(firstname)
                        .lastname(lastname)
                        .encryptedPassword(password)
                        .build();

                allPerson.add(p);
            }
        }catch(SQLException e){
            System.out.println(e.getCause());
        }
        return allPerson;
    }
}

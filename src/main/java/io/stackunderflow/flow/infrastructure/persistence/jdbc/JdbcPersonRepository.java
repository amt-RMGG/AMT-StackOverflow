package io.stackunderflow.flow.infrastructure.persistence.jdbc;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.person.IPersonRepository;
import io.stackunderflow.flow.domain.person.Person;
import io.stackunderflow.flow.domain.person.PersonId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@ApplicationScoped //Singleton
@Named("JdbcPersonRepository")
public class JdbcPersonRepository implements IPersonRepository {


    @Resource(lookup = "jdbc/StackUnderFlowDS")
    DataSource dataSource;

    public  JdbcPersonRepository(){
        //TODO : ça marche si on fait ça, mais devrait marcher sans, avec le " @Resource(lookup = "jdbc/StackUnderFlowDS")"
        try {
            dataSource = InitialContext.doLookup("jdbc/StackUnderFlowDS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public  JdbcPersonRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Person> findByUsername(String username) {

        try {
            PreparedStatement statement = dataSource.getConnection()
                    .prepareStatement("SELECT * FROM user WHERE username = ?");

            statement.setString(1, username); //Permet de remplacer le '?' de la query par le parametre username
            ResultSet rs = statement.executeQuery();

            String email = "";
            String password = "";
            String firstname = "";
            String lastname = "";

            //Si la query n'a rien retourné
            if (rs.next() == false) {
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
        synchronized (entity.getUsername()){
            if(!findByUsername(entity.getUsername()).isEmpty()){
                throw new RegistrationFailedException("Cannot save/update person. Integrity constraint violation : username");
            }

            Connection connection = null;
            PreparedStatement statement = null;
            //Insert into the db
            try {

                connection = dataSource.getConnection();
                statement = connection.prepareStatement(
                        "INSERT INTO user (username, email, password, firstname, lastname) VALUES(?, ?, ?, ?, ?)"
                );

                statement.setString(1, entity.getUsername());
                statement.setString(2, entity.getEmail());
                statement.setString(3, entity.getEncryptedPassword());
                statement.setString(4, entity.getFirstname());
                statement.setString(5, entity.getLastname());

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
    }

    @Override
    public void remove(PersonId id) {

    }

    @Override
    public Optional<Person> findById(PersonId id) {
        return Optional.empty();
    }


    //TODO A tester !
    @Override
    public Collection<Person> findAll() {

        Collection<Person> allPerson = new LinkedList<>();

        try {
            PreparedStatement statement = dataSource.getConnection()
                    .prepareStatement("SELECT * FROM user");

            ResultSet rs = statement.executeQuery();

            while(rs.next() != false){

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

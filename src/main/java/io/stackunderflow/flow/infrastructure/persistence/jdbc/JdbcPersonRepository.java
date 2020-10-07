package io.stackunderflow.flow.infrastructure.persistence.jdbc;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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
    public void save(Person entity) {

    }

    @Override
    public void remove(PersonId id) {

    }

    @Override
    public Optional<Person> findById(PersonId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Person> findAll() {
        return null;
    }
}

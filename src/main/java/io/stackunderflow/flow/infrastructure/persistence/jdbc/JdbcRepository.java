package io.stackunderflow.flow.infrastructure.persistence.jdbc;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.*;

@ApplicationScoped
public class JdbcRepository {

    @Resource(lookup = "jdbc/StackUnderFlowDS")
    DataSource dataSource;

    public JdbcRepository(){}


    ResultSet fetchData(String query, Object... values){
        ResultSet rs = null;
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);

            //Replace the '?' symbole with values passed in the parameters
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            rs = statement.executeQuery();
        }catch(SQLException e){
            System.out.println(e.getCause());
        }

        return rs;
    }

    void executeInsertQuery(String query, Object... values) throws RegistrationFailedException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);

            //Replace the '?' symbole with values passed in the parameters
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RegistrationFailedException(e.getMessage());
            //Close connection to the DB
        } finally {
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
                //TODO : do something ?
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

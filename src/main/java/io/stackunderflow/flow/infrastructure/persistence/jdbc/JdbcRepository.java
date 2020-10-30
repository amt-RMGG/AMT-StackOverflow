package io.stackunderflow.flow.infrastructure.persistence.jdbc;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    void executeUpdateQuery(String query, Object... values) throws SQLException {


            Connection conn = null;
            PreparedStatement ps = null;
            try{
                conn = dataSource.getConnection();
                ps = conn.prepareStatement(query);

                for (int i = 0; i < values.length; ++i){
                    Object o = values[i];
                    if(o instanceof Integer){
                        ps.setInt(i+1, (Integer) o);
                    }else if(o instanceof String){
                        ps.setString(i+1, (String)o);
                    }
                    else{
                        throw new RuntimeException("Data type not supported locally");
                    }
                }

                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
                //Close connection to the DB
            } finally {
                try {
                    if (ps != null){
                        conn.close();
                        ps.close();
                    }

                } catch (SQLException se) {
                    se.printStackTrace();
                }
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }


    }
}

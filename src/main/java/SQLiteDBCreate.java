import java.sql.*;

public class SQLiteDBCreate {
    public static void main (String[] args){

        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:./Databases/UserCollection.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists petcounter");
            statement.executeUpdate("create table petcounter (id long, name string, amountofPets integer, guildid long)");

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}

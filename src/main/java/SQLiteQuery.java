import java.sql.*;

public class SQLiteQuery {
    public static int query(long userID, long guildID){

        int returnAmountOfPets = 0;

        String sqlQuery = "SELECT amountofPets FROM petcounter WHERE id = ? AND guildid = ?";

        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:./Databases/UserCollection.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            try(PreparedStatement pstmtQUERY = connection.prepareStatement(sqlQuery)){
                pstmtQUERY.setLong(1,userID);
                pstmtQUERY.setLong(2,guildID);

                ResultSet rs1 = pstmtQUERY.executeQuery();

                while(rs1.next()) {
                    returnAmountOfPets = rs1.getInt("amountofPets");
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
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
        return returnAmountOfPets;
    }
}

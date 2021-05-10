import java.sql.*;


public class SQLiteDBInsert {
    public static void insert(long ID, String name, long guildID)
    {
        boolean checkIfInDB = false;
        int amountOfPetsFIRST = 0;
        String sqlUpdate = "UPDATE petcounter SET amountofPets = ? "
                + "WHERE id = ? AND guildid = ?";

        String sqlInsert = "INSERT INTO petcounter(id,name,amountofPets,guildid) VALUES(?,?,?,?)";

        String sqlQuery = "SELECT amountofPets FROM petcounter WHERE id = ? AND guildid = ?";

        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:./Databases/UserCollection.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("SELECT * FROM petcounter");

            while(rs.next()) {

                if(rs.getLong("id") == ID && rs.getLong("guildid") == guildID){
                    checkIfInDB = true;
                } else{
                    checkIfInDB = false;
                }
            }
            if(checkIfInDB){

                amountOfPetsFIRST = SQLiteQuery.query(ID,guildID);

                try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
                    pstmt.setInt(1,amountOfPetsFIRST+1);
                    pstmt.setLong(2, ID);
                    pstmt.setLong(3,guildID);

                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try (PreparedStatement pstmt = connection.prepareStatement(sqlInsert)) {
                    System.out.println("Adding a new Pet counter to the DB");
                    pstmt.setLong(1,ID);
                    pstmt.setString(2,name);
                    pstmt.setInt(3,1);
                    pstmt.setLong(4,guildID);

                    pstmt.executeUpdate();
                } catch(SQLException e){
                    System.out.println(e.getMessage());
                }
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
    }
}

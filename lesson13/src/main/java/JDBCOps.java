import equipment.*;

import java.sql.*;
import java.util.ArrayList;

public class JDBCOps {
    public JDBCOps(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException exception){
            exception.printStackTrace();
        }
    }

    public Connection getConnection(){
        try{
            return DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/equipmentManager?useSSL=false",
                            "root",
                            "adminroot"
                    );
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    /***
     * The method gets all Locker objects from the database.
     * It has the same functionality as [getLockers] but contains its own
     * database connection creation
     * @return - an ArrayList of Locker objects, containing all the objects found in the database.
     */
    public ArrayList<Locker> getLockers(){
        ArrayList<Locker> result = new ArrayList<>();

        try(Connection con = getConnection()) {
            Statement stmt = con.createStatement();

            String selectSql = "SELECT * FROM lockers";
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while(resultSet.next()){
                int lockerId = resultSet.getInt("id");
                String location = resultSet.getString("location");
                String address = resultSet.getString("address");
                Locker temp = new Locker();
                temp.setId(lockerId);
                temp.setLocation(location);
                temp.setAddress(address);
                result.add(temp);
            }

        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }

        return result;
    }

    /***
     * The method adds an object of type Locker to the appropriate db table.
     * @param locker - the locker object that needs to be added.
     * @return - true if successful, false if not successful.
     */
    public boolean addLocker(Locker locker){
        try (Connection con = getConnection()) {

            Statement stmt = con.createStatement();

            String insertSql = "INSERT INTO lockers(location, address)"
                    + " VALUES('" +
                    locker.getLocation() +
                    "','" +
                    locker.getAddress() +
                    "')";
            stmt.executeUpdate(insertSql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }

    /***
     * A method that retrieves a locker with a specified id from the database.
     * The method contains its own Connection creation,
     * and does not call the [getConnection] method.
     * @param id - the id that is to be retrieved
     * @return - returns a [Locker] object if a match is found, and null otherwise
     */

    public Locker getLocker(long id){
        try (Connection con = getConnection()){
            Statement stmt = con.createStatement();
            String selectLocker = "SELECT * FROM lockers " +
                    "WHERE id='" +
                    id +
                    "'" ;

            ResultSet resultSet = stmt.executeQuery(selectLocker);
            while(resultSet.next()){
                Locker l1 = new Locker();
                l1.setId(resultSet.getInt("id"));
                l1.setLocation(resultSet.getString("location"));
                l1.setAddress(resultSet.getString("address"));
                return l1;
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }


    public ArrayList<Equipment> getAllEquipment(){
        ArrayList<Equipment> result = new ArrayList<>();

        try(Connection con = getConnection()){
            Statement stmt = con.createStatement();

            String sqlString = "SELECT * FROM equipmentTable";

            ResultSet resultSet = stmt.executeQuery(sqlString);

            while (resultSet.next()){
                long id = resultSet.getLong("id");
                int maint = resultSet.getInt("requiresMaintenance");
                boolean maintB = false;
                if (maint==1){
                    maintB = true;
                }
                String name = resultSet.getString("name");
                String eType = resultSet.getString("type");
                long lockerid = resultSet.getLong("location");
                Locker locker = getLocker(lockerid);

                Equipment temp;
                switch (eType){
                    case "Sword" : {
                        temp = new Sword();
                        break;
                    }
                    case "Volleyball" : {
                        temp = new Volleyball();
                        break;
                    }
                    default: {
                        temp = new Football();
                        break;
                    }
                }

                temp.setId(id);
                temp.setLocation(locker);
                temp.setRequiresMaintenance(maintB);

                result.add(temp);
            }

        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return result;
    }
}

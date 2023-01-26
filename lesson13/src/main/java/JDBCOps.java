import equipment.Equipment;
import equipment.Locker;

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
    public Locker getLockerBuiltin(int id){
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/equipmentManager?useSSL=false", "root", "adminroot")) {

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
    public Locker getLocker(int id){
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

    /***
     * The method gets all Locker objects from the database.
     * It has the same functionality as [getLockers] but contains its own
     * database connection creation
     * @return - an ArrayList of Locker objects, containing all the objects found in the database.
     */
    public ArrayList<Locker> getLockersInitial(){
        ArrayList<Locker> result = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/equipmentManager?useSSL=false",
                "root",
                "adminroot"
        )){
            Statement stmt = con.createStatement();

            String sqlQuery = "SELECT * FROM lockers";

            ResultSet resultSet = stmt.executeQuery(sqlQuery);

            while(resultSet.next()){
                Locker tempLocker = new Locker();
                int lockerId = resultSet.getInt("id");
                String location = resultSet.getString("location");
                String address = resultSet.getString("address");
                tempLocker.setId(lockerId);
                tempLocker.setLocation(location);
                tempLocker.setAddress(address);

                result.add(tempLocker);
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
    public boolean addLockerToDb(Locker locker){
        boolean result = false;
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/equipmentManager?useSSL=false&allowPublicKey=true",
                "root",
                "adminroot"
        )){

            Statement stms = con.createStatement();

            String insertSql= "INSERT INTO lockers(location, address) VALUES" +
                    "('" +
                    locker.getLocation() + "', '" +
                    locker.getAddress() +
                    "') ";

            stms.execute(insertSql);
            result = true;

        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return result;
    }
}

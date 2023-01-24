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

    public ArrayList<Locker> getLockers(){
        ArrayList<Locker> result = new ArrayList<>();

        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/equipmentManager?useSSL=false",
                        "root",
                        "adminroot")) {
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
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/equipmentManager?useSSL=false",
                        "root",
                        "adminroot")) {

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

    public Locker getLocker(int id){
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
}

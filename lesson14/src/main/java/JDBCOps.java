import java.sql.*;
import java.util.ArrayList;
import equipment.*;

public class JDBCOps {

    //private Connection continuousConnection;
    public JDBCOps(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException exception){
            exception.printStackTrace();
        }
        //continuousConnection = getConnection();
    }

    public Connection getConnection(){
        try{
            return DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/equipmentManager?" +
                                    "useSSL=false&" +
                                    "allowPublicKeyRetrieval=true",
                            "root",
                            "adminroot"
                    );
            /*
            Writing passwords in the clear in code is definitely a no-no.
             */
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
        String prep = "SELECT * FROM lockers";
        try(Connection con = getConnection()) {
            Statement stmt = con.createStatement();

            PreparedStatement ps = con.prepareStatement(prep);

            String selectSql = "SELECT * FROM lockers";
            ResultSet resultSet = stmt.executeQuery(selectSql);

            ps.executeQuery();


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
            con.close();

        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally {
            System.out.println("and that's done");
        }

        return result;
    }

    /***
     * The method adds an object of type Locker to the appropriate db table.
     * @param locker - the locker object that needs to be added.
     * @return - id if successful, 0 if not successful.
     */
    public long addLocker(Locker locker){
        long id = -1;
        try (Connection con = getConnection()) {

            Statement stmt = con.createStatement();

            String insertSql = "INSERT INTO lockers(location, address)"
                    + " VALUES('" +
                    locker.getLocation() +
                    "','" +
                    locker.getAddress() +
                    "')";
            stmt.executeUpdate(insertSql);

            //String idretrieval = "SELECT SCOPE_IDENTITY()";
            String idretrieval = "SELECT id FROM lockers WHERE " +
                    "location='" + locker.getLocation() + "' AND " +
                    "address='" + locker.getAddress() +
                    "'";

            ResultSet rs = stmt.executeQuery(idretrieval);

            while(rs.next()){
                id = rs.getLong("id");
                return id;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        locker.setId((int) id);
        return id;
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

    public ArrayList<Locker> getLockersByAddressPrep(String address){
        ArrayList<Locker> result = new ArrayList<>();
        String preparedSelect = "SELECT * FROM lockers WHERE address=?";

        try (Connection con = getConnection()){
            con.setAutoCommit(false);

            PreparedStatement prep = con.prepareStatement(preparedSelect);
            prep.setString(1, address);

            ResultSet resultSet = prep.executeQuery();
            while(resultSet.next()){
                Locker l1 = new Locker();
                l1.setId(resultSet.getInt("id"));
                l1.setLocation(resultSet.getString("location"));
                l1.setAddress(resultSet.getString("address"));
                result.add(l1);
                //return l1;
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return result;
    }

    public int deleteLockerById(long id){
        String deleteSQL= "DELETE FROM lockers WHERE id=?";
        int res = -1;
        try(Connection con  = getConnection()){
            PreparedStatement ps = con.prepareStatement(deleteSQL);
            ps.setLong(1, id);

            res = ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return res;
    }

    public int deleteLockerByLocation(String location){
        String deleteSQL= "DELETE FROM lockers WHERE location=?";
        int res = -1;
        try(Connection con  = getConnection()){
            PreparedStatement ps = con.prepareStatement(deleteSQL);
            ps.setString(1, location);

            res = ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList<Locker> getLockersByAddress(String address){
        ArrayList<Locker> result = new ArrayList<>();
        try (Connection con = getConnection()){
            Statement stmt = con.createStatement();
            String selectLocker = "SELECT * FROM lockers " +
                    "WHERE address='" +
                    address +
                    "'" ;

            ResultSet resultSet = stmt.executeQuery(selectLocker);
            while(resultSet.next()){
                Locker l1 = new Locker();
                l1.setId(resultSet.getInt("id"));
                l1.setLocation(resultSet.getString("location"));
                l1.setAddress(resultSet.getString("address"));
                result.add(l1);
                //return l1;
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return result;
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

    public void addEquipment(Equipment equip){
        this.addEquipment(equip, false);
    }

    public void addEquipment(Equipment equip, boolean allowed){
        String type = equip.getClass().getSimpleName();
        try(Connection con = getConnection()) {
            con.setAutoCommit(false);
            long lockerId = equip.getLocation().getId();
            Statement stat = con.createStatement();

            if(equip.getLocation() != null &&
                    equip.getLocation().getId() == -1){
                //equipment exists, but id is -1

                lockerId = this.addLocker(equip.getLocation());
                equip.getLocation().setId((int) lockerId);
            } else if (equip.getLocation() != null &&
                    equip.getLocation().getId() != -1) {
                // there is an id
                Locker locker = this.getLocker(equip.getLocation().getId());

                if(locker!=null){
                    lockerId = locker.getId();
                }
            }


            int insertableValue = 0;
            if(equip.requiresMaintenance()){
                insertableValue = 1;
            }


            String sql = "INSERT INTO equipmentTable(requiresMaintenance, location, name, type) " +
                        "VALUES ('" +
                        //(equip.requiresMaintenance() ? 1 : 0)
                        insertableValue
                        + "', '" +
                        equip.getLocation().getId() +"', '" +
                        equip.getName() + "', '" +
                        type + "')";

            int result = stat.executeUpdate(sql);
            if(allowed && lockerId!=-1){
                con.commit();
            }
            else {
                con.rollback();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}


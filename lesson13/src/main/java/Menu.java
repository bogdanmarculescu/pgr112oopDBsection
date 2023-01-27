import equipment.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public EquipmentManager em;
    //Scanner sc;

    public Menu(){
        em = new EquipmentManager();
        //this.sc = new Scanner(System.in);
    }
    public void run(){
        // runnning stuff
        Scanner sc = new Scanner(System.in);
        JDBCOps jdbcOps = new JDBCOps();
        boolean keepRunning = true;

        while(keepRunning){
            System.out.println("Options");
            System.out.println("0. Exit");
            System.out.println("1. See all lockers");
            System.out.println("12. Add a locker");
            System.out.println("2. Show equipment");
            System.out.println("3. Add equipment");

            String input = sc.nextLine();


            switch (input){
                case "0" : {
                    keepRunning = false;
                    break;
                }
                case "1" : {
                    // connect to db to show lockers
                    System.out.println("Lockers");
                    ArrayList<Locker> lockers = jdbcOps.getLockers();
                    for(Locker l : lockers){
                        System.out.println(l.printout());
                    }

                    //System.out.println("Locker 1: ");
                    //System.out.println(jdbcOps.getLocker(1).printout());
                    break;
                }
                case "12" :{
                    // call the Locker creation method here.
                    createAndAddLocker(sc, jdbcOps);
                    break;
                }
                case "2" : {
                    // connect to db and show all equipment
                    System.out.println("This is where we display stuff");

                    //should we read from db here
                    /*
                    for(String s : em.equipmentPrintout()){
                        System.out.println(s);
                    }

                     */
                    ArrayList<Equipment> list = jdbcOps.getAllEquipment();
                    for(Equipment e : list){
                        System.out.println(e.printout());
                    }
                    break;
                }
                case "3" : {
                    System.out.println("Adding equipment");
                    System.out.println("What type? ");
                    String type = sc.nextLine();
                    System.out.println("a name?");
                    String name = sc.nextLine();

                    System.out.println("does it require maintenance? (true/false)");
                    String maintenance = sc.nextLine();


                    boolean maintenanceB = false;
                    if(maintenance.equalsIgnoreCase("true")){
                        maintenanceB = true;
                    }

                    System.out.println("which locker is it in? (I mean id - leave empty if new)");
                    String locker = sc.nextLine();

                    long lockerid = -1;
                    if(locker.isEmpty()){
                        lockerid = createAndAddLocker(sc, jdbcOps);
                    }
                    else {
                        lockerid = Long.parseLong(locker);
                    }
                    Locker locker1 = jdbcOps.getLocker(lockerid);
                    // at this point locker1 SHOULD have SOMETHING in it :)

                    Equipment e;
                    switch(type){
                        case "Football" : {
                            e = new Football();
                            break;
                        }
                        case "Volleyball" : {
                            e = new Volleyball();
                            break;
                        }
                        case "Sword" :{
                            e = new Sword();
                            break;
                        }
                        default : {
                            // this is not a good solution, but it'll work as a temporary fix
                            e = new Football();
                            break;
                        }
                    }

                    e.setRequiresMaintenance(maintenanceB);
                    e.setLocation(locker1);
                    e.setName(name);

                    jdbcOps.addEquipment(e);

                    /*
                    Long objectLong = Long.getLong("42");

                    long objbasic = objectLong.longValue();

                     */

                    break;
                }
                default : {
                    System.out.println("Erm, try again.");
                }
            }
        }
    }

    public long createAndAddLocker(Scanner sc, JDBCOps jdbcOps){
        System.out.println("Add a locker");
        System.out.println("Location: ");
        String location = sc.nextLine();
        System.out.println("Address: ");
        String address = sc.nextLine();
        Locker tempLocker = new Locker();
        tempLocker.setLocation(location);
        tempLocker.setAddress(address);

        long id = jdbcOps.addLocker(tempLocker);
        //boolean added = jdbcOps.addLockerToDb(tempLocker);
        if(id != -1){
            System.out.println("Huzzah! Added!");
            System.out.println("Id: " + id);
        }
        else{
            System.out.println("NOOOOOOO!");
        }
        return id;
    }
}

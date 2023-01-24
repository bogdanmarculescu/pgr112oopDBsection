import equipment.EquipmentManager;

import java.util.Scanner;

public class Menu {
    public EquipmentManager em;

    public Menu(){
        em = new EquipmentManager();
    }
    public void run(){
        // runnning stuff
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;

        while(keepRunning){
            System.out.println("Options");
            System.out.println("0. Exit");
            System.out.println("1. See all equipment");

            String input = sc.nextLine();


            switch (input){
                case "0" : {
                    keepRunning = false;
                    break;
                }
                case "1" : {
                    // connect to db and show all equipment
                    System.out.println("This is where we display stuff");

                    //should we read from db here
                    for(String s : em.equipmentPrintout()){
                        System.out.println(s);
                    }
                    break;
                }
                default : {
                    System.out.println("Erm, try again.");
                }
            }
        }
    }
}

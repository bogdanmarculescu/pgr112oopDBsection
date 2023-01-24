import java.util.Scanner;

public class Menu {
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
                    break;
                }
                default : {
                    System.out.println("Erm, try again.");
                }
            }
        }
    }
}

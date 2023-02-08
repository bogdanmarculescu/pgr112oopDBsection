package equipmentmanager;


import equipment.Equipment;
import equipment.Football;
import equipment.Sword;
import equipment.Volleyball;

import java.util.ArrayList;

/***
 * One way to create "test" or initial data is to generate is programatically
 */
public class InitAndOtherDeamons {

    public ArrayList<Equipment> createEquipment(int quantity){
        ArrayList<Equipment> result = new ArrayList<>();
        for(int i = 0; i< quantity; i++){

        }
        return result;
    }


    public Equipment createEquipment(String type, int index){
        Equipment e;
        String nameConvention = "-" + index + "-" + Math.floor(Math.random()*42);

        switch(type){
            case "sword" : {
                e = new Sword();
                e.setName("RandomSword" + nameConvention);
                e.setRequiresMaintenance(false);
                break;
            }
            case "volleyball" :{
                e = new Volleyball();
                e.setName("RandomVolleyball" + nameConvention);
                e.setRequiresMaintenance(false);
                break;
            }
            default :{
                e = new Football();
                e.setName("RandomFootball" + nameConvention);
                e.setRequiresMaintenance(false);
                break;
            }
        }
        return e;
    }
}

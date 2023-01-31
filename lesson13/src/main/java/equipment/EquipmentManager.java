package equipment;

import java.util.ArrayList;
import java.util.Collection;

public class EquipmentManager {
    public ArrayList<Equipment> overview = new ArrayList<>();

    public boolean addEquipment(Equipment e){
        overview.add(e);
        // if for some reason adding should fail, we can return the appropriate value here
        return true;
    }

    public Equipment retrieveEquipment(long id){
        Equipment found = null;

        for(Equipment e : overview){
            if(e.id == id){
                found = e;
            }
        }

        return found;
    }

    public int itemCount(){
        return overview.size();
    }

    public ArrayList<String> equipmentPrintout(){
        ArrayList<String> result = new ArrayList<>();

        for(Equipment e : overview){
            result.add(e.printout());
        }
        return result;
    }

    public void bulkImport(ArrayList equipment){
        this.overview.addAll(equipment);
    }
}

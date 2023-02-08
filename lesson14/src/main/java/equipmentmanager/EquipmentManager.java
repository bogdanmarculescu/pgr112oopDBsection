package equipmentmanager;

import equipment.*;

import java.util.ArrayList;

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
            //if(e.id == id){
            if(e.getId() == id){
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


    /***
     * A method to search through the equipment and find items that match a given string
     * @param searchCriteria - the string being searched for
     * @return - an ArrayList with all the Equipment with a name that matches the criteria.
     */
    public ArrayList<Equipment> search(String searchCriteria){
        ArrayList<Equipment> result = new ArrayList<>();
        for(Equipment e : this.overview){
            if(e.getName().contains(searchCriteria)){
                result.add(e);
            }
        }
        return result;
    }

    /***
     * A method to search through the equipment and find Footballs that match a given string
     * @param searchCriteria - the string being searched for
     * @return - an ArrayList with all the Football objects with a name that matches the criteria.
     */
    public ArrayList<Football> searchFootball(String searchCriteria){
        ArrayList<Football> result = new ArrayList<>();
        for(Equipment e : this.overview){
            if(e.getName().contains(searchCriteria) && e instanceof Football){
                result.add((Football) e);
            }
        }
        return result;
    }

    /***
     * A method to search through the equipment and find items that match a given string
     * @param searchCriteria - the string being searched for
     * @return - an ArrayList with all the Equipment with a name that matches the criteria
     * and the correct type.
     */
    public ArrayList<Equipment> search(String searchCriteria, String type){
        ArrayList<Equipment> result = new ArrayList<>();
        for(Equipment e : this.overview){
            if(e.getName().contains(searchCriteria) && e.getClass().getSimpleName().equalsIgnoreCase(type)){
                result.add(e);
            }
        }
        return result;
    }
}

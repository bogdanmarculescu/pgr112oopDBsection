package equipment;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EquipmentManagerTest {
    public EquipmentManager init(){
        EquipmentManager em = new EquipmentManager();

        em.addEquipment(new Football());
        em.addEquipment(new Volleyball());
        em.addEquipment(new Sword());
        return em;
    }
    @Test
    public void simpleAddTest(){

        EquipmentManager em = init();
        assertEquals(3, em.itemCount());

        em.addEquipment(new Sword());
        assertEquals(4, em.itemCount());
    }

    @Test
    public void testKickable(){
        EquipmentManager em = init();
        ArrayList<Football> footballs = new ArrayList<>();

        assertEquals(0, footballs.size());

        for(Equipment e: em.overview){
            if(e instanceof Kickable){
                footballs.add((Football) e);
            }
        }

        assertEquals(1, footballs.size());
    }

    @Test
    public void testCasting(){
        ArrayList<Equipment> eqList = new ArrayList<>();

        Football f1 = new Football();
        eqList.add(f1);

        Volleyball v1 = new Volleyball();
        eqList.add(v1);

        //v1.getClass();

        assertEquals(2, eqList.size());

    }

    @Test
    public void bulkImportTest(){
        EquipmentManager em = init();

        EquipmentManager em2 = new EquipmentManager();

        em2.bulkImport(em.overview);

        ArrayList<Sword> fencingGear = new ArrayList<>();
        fencingGear.add(new Sword("Epee"));
        fencingGear.add(new Sword("Foil"));
        fencingGear.add(new Sword("Saber"));

        em2.bulkImport(fencingGear);

        assertEquals(6, em2.itemCount());

    }
}

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
}

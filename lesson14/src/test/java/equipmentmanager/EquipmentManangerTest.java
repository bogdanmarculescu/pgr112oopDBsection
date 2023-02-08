package equipmentmanager;

import equipment.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquipmentManangerTest {

    public EquipmentManager createAndInit(){
        EquipmentManager em = new EquipmentManager();
        Locker locker = new Locker();
        em.addEquipment(new Volleyball("VolleyBall2", locker));
        em.addEquipment(new Volleyball("VolleyBall42", locker));

        em.addEquipment(new Football("FootBall10", locker));
        em.addEquipment(new Football("FootBall42", locker));

        em.addEquipment(new Sword("Murat's sword", "Sabre"));
        em.addEquipment(new Sword("FencingFoil", "Foil"));
        em.addEquipment(new Sword("StandardSword", "Epee"));

        return em;

    }

    @Test
    public void emTest(){
        EquipmentManager em = createAndInit();
        String searchString = "Ball";

        int initSize = em.itemCount();

        em.addEquipment(new Volleyball("VolleyBall2", new Locker()));
        em.addEquipment(new Sword("Football", "Sabre"));

        ArrayList<Equipment> result = em.search(searchString);


        assertTrue(result.size()>0);
        assertEquals(initSize+2, em.itemCount());


        /*
        This relies on a sensible user not naming a football "Sword" or a sword "FootBall".
        This may noy always be true of all users.
         */
        for(Equipment e : result){
            assertTrue(e instanceof Football || e instanceof Volleyball);
        }
    }
}

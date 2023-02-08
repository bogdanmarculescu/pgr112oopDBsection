package equipment;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LockerTest {

    @Test
    public void basicTestVolleyball(){
        Volleyball v = new Volleyball("CompetitionVolleyball", new Locker());


        assertTrue(v.getName().equalsIgnoreCase("CompetitionVolleyball"));
    }

    @Test
    public void lockerTest(){
        Locker l = new Locker();
        l.setLocation("TestLocation");

        assertTrue(l.getLocation().equalsIgnoreCase("testlocation"));
    }
}

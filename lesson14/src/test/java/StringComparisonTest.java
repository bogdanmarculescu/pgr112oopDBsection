import equipment.Locker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringComparisonTest {

    @Test
    public void stringCOmparison(){

        String s1 = "God-emperor of Dune";
        String s2 = new String("dune");
        String s3 = "Dune";
        String s4 = new String("dune");

        assertTrue(s1.toLowerCase().contains(s2.toLowerCase()));
        assertFalse(s2 == s4);
        assertTrue(s2.equalsIgnoreCase(s4));
        assertFalse(s3.equals(s4));
        assertTrue(s3.equalsIgnoreCase(s4));

    }

    @Test
    public void lifecycleStuff(){
        int i = -1;
        int j = i;
        i = 42;

        assertTrue(i!=j);

        Locker l1 = new Locker();
        l1.setId(-1);


        Locker l2 = l1;
        assertTrue(l2.getId()==-1);

            l1.setId(42);

        l2 = new Locker();
        l2.setId(37);


        l1 = new Locker();
        l1.setId(13);


    }
}

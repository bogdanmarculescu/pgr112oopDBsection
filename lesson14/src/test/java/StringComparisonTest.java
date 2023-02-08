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
    public void randomDeletionTest(){
        JDBCOps ops = new JDBCOps();

        int result = ops.deleteLockerById(42);

        assertTrue(result==0);
        Locker locker = new Locker();
        locker.setLocation("TestLocation");
        locker.setAddress("TestAddress");
        ops.addLocker(locker);

        ops.deleteLockerByLocation("TestLocation");


    }
}

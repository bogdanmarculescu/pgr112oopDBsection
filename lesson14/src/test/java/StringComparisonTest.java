import org.junit.jupiter.api.Assertions;
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

    }
}

package krysi;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ReductionTest {

    private char[] z = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private int passwordLength = 7;
    private Reduction reduction = new Reduction(passwordLength,z);

    @Test
    public void testExecuteReduction() {
        assertEquals("87inwgn",reduction.executeReduction("29c3eea3f305d6b823f562ac4be35217",0));
    }
}
package krysi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ReductionTest {

    private char[] z = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private Tools tools = new Tools(z);
    private int passwordLength = 7;
    private Reduction reduction = new Reduction(passwordLength,z);

    @Test
    public void testExecuteReduction() {
        String inputHash = tools.getMD5String("0000000");
        assertEquals("87inwgn",reduction.executeReduction(inputHash,0));
    }

    @Test
    public void testExecuteReduction2() {
//        String inputHash = tools.hashWithMd5("0000000");
//        assertEquals("87inwgn",reduction.executeReduction(inputHash,0));
    }
}
package krysi;

import org.junit.Test;

import static org.junit.Assert.*;

public class RainbowTest {

    char[] z = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    int passwordLength = 7;
    int chainLength = 2000;
    int r = 2000;

    Rainbow rainbow = new Rainbow(passwordLength, z, chainLength, r);
    Tools tools = new Tools(z);

    @Test
    public void testMatchInput() {
        assertEquals("0bgec3d",rainbow.matchInput("1d56a37fb6b08aa709fe90e12ca59e12"));
    }

    @Test
    public void testHashPassword() {
        assertEquals("1d56a37fb6b08aa709fe90e12ca59e12",tools.getMD5String("0bgec3d"));
    }

    @Test
    public void testFindPassword() {
        assertEquals("0bgec3d", rainbow.findPassword("00000rs","1d56a37fb6b08aa709fe90e12ca59e12"));
    }

    @Test
    public void testFindFirstValue() {
        assertEquals("00000rs",rainbow.findFirstValue("1d56a37fb6b08aa709fe90e12ca59e12"));
    }

}
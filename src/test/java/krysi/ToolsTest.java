package krysi;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class ToolsTest {

    private char[] z = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private Tools tools = new Tools(z);

    @Test
    public void testReadHashValue() {
        assertEquals("1d56a37fb6b08aa709fe90e12ca59e12",
                tools.readHashValue("src/main/resources/hashValue.txt"));
    }

    @Test
    public void testHashToBigInteger() {
        assertEquals(new BigInteger("181823"),tools.hashToBigInteger("i18n"));
    }

    @Test
    public void testIntToString() {
        int [] input = {20,27,34,28,18,1,2,3};
        assertEquals("krysi123",tools.intToString(input));
    }

    @Test
    public void testWrongToRightWayString() {
        assertEquals("dcba", tools.wrongToRightWayString("abcd"));
    }

    @Test
    public void testGetMD5String() {
        assertEquals("29c3eea3f305d6b823f562ac4be35217", tools.getMD5String("0000000"));
    }
}
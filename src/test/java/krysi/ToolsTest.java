package krysi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToolsTest {

    private char[] z = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private Tools tools = new Tools(z);

    @Test
    public void readHashValue() {
        assertEquals("1d56a37fb6b08aa709fe90e12ca59e12",
                tools.readHashValue("src/main/resources/hashValue.txt"));
    }

    @Test
    public void hashToInteger() {
        assertEquals(181823,tools.hashToInteger("i18n"));
    }

    @Test
    public void intToString() {
        int [] input = {20,27,34,28,18,1,2,3};
        assertEquals("krysi123",tools.intToString(input));
    }

    @Test
    public void wrongToRightWayString() {
        assertEquals("dcba", tools.wrongToRightWayString("abcd"));
    }

    @Test
    public void hashWithMd5() {
        assertEquals("29c3eea3f305d6b823f562ac4be35217",tools.hashWithMd5("0000000"));
    }
}
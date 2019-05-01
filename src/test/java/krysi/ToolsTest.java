package krysi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToolsTest {

    Tools tools = new Tools();

    @Test
    public void readHashValue() {
    }

    @Test
    public void hashToInteger() {
    }

    @Test
    public void intToString() {
    }

    @Test
    public void wrongToRightWayString() {
    }

    @Test
    public void hashWithMd5() {
        assertEquals("29c3eea3f305d6b823f562ac4be35217",tools.hashWithMd5("0000000"));
    }
}
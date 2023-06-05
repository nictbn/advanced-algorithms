package lzw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LZWTest {

    LZW l;
    @BeforeEach
    void setUp() {
        l = new LZW();
    }

    @Test
    public void compressTest() {
        List<Integer> result = l.compress("ababcbc");
        assertEquals(6, result.size());
        assertEquals(97, result.get(0));
        assertEquals(98, result.get(1));
        assertEquals(256, result.get(2));
        assertEquals(99, result.get(3));
        assertEquals(98, result.get(4));
        assertEquals(99, result.get(5));
        assertNull(l.compress(null));
    }
}
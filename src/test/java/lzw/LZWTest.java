package lzw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LZWTest {

    public static final String COMPRESSION_TEST = "This is a test of compression. Just to test if compression works.";
    public static final String PATTERN = "ababcbc";
    LZW l;
    @BeforeEach
    void setUp() {
        l = new LZW();
    }

    @Test
    public void compressTest() {
        List<Integer> result = l.compress(PATTERN);
        assertEquals(6, result.size());
        assertEquals(97, result.get(0));
        assertEquals(98, result.get(1));
        assertEquals(256, result.get(2));
        assertEquals(99, result.get(3));
        assertEquals(98, result.get(4));
        assertEquals(99, result.get(5));
        assertNull(l.compress(null));
    }

    @Test
    public void decompressTest() {
        List<Integer> compressed = l.compress(PATTERN);
        String decompressed = l.decompress(compressed);
        assertNotNull(decompressed);
        assertEquals(PATTERN, decompressed);

        List<Integer> compressed2 = l.compress(COMPRESSION_TEST);
        String decompressed2 = l.decompress(compressed2);
        assertNotNull(decompressed2);
        assertEquals(COMPRESSION_TEST, decompressed2);
    }

    @Test
    public void decompressInvalidTest() {
        List<Integer> compressed = l.compress("abababa");
        compressed.add(456);
        assertEquals("-1", l.decompress(compressed));
    }
}
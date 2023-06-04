package huffman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static huffman.Huffman.CHARACTER_LIMIT;
import static org.junit.jupiter.api.Assertions.*;

class HuffmanTest {
    Huffman h;
    @BeforeEach
    void setUp() {
        h = new Huffman();
    }

    @Test
    void createFrequencyTableTest() {
        int[] frequencies = h.createFrequencyTable("aabbbcd".toCharArray());
        assertEquals(CHARACTER_LIMIT, frequencies.length);
        assertEquals(2, frequencies['a']);
        assertEquals(3, frequencies['b']);
        assertEquals(1, frequencies['c']);
        assertEquals(1, frequencies['d']);
    }
}
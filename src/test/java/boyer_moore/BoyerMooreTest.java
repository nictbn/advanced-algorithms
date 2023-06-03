package boyer_moore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoyerMooreTest {
    public static final char[] PATTERN_1 = "baibai".toCharArray();
    public static final char[] PATTERN_2 = "baidai".toCharArray();
    BoyerMoore b;

    @BeforeEach
    void setUp() {
        b = new BoyerMoore();
    }

    @Test
    public void isPrefixTest() {
        assertTrue(b.isPrefix(PATTERN_1, 6));
        assertTrue(b.isPrefix(PATTERN_1, 3));
        assertFalse(b.isPrefix(PATTERN_1, 1));
        assertFalse(b.isPrefix(PATTERN_1, 2));
        assertFalse(b.isPrefix(PATTERN_1, 4));
        assertFalse(b.isPrefix(PATTERN_1, 5));

        assertTrue(b.isPrefix(PATTERN_2, 6));
        assertFalse(b.isPrefix(PATTERN_2, 3));
        assertFalse(b.isPrefix(PATTERN_2, 1));
        assertFalse(b.isPrefix(PATTERN_2, 2));
        assertFalse(b.isPrefix(PATTERN_2, 4));
        assertFalse(b.isPrefix(PATTERN_2, 5));
    }

    @Test
    public void suffixLengthTest() {
        assertEquals(2, b.suffixLength(PATTERN_2, 2));
        assertEquals(3, b.suffixLength("kaikai".toCharArray(), 2));
        assertEquals(1, b.suffixLength("mytestmytest".toCharArray(), 2));
        assertEquals(6, b.suffixLength("mytestmytest".toCharArray(), 5));
    }

    @Test
    public void computePrefixTest() {
        int[] table = new int[6];
        b.computePrefix(PATTERN_2, table);
        assertArrayEquals(new int[]{6, 7, 8, 9, 10, 11}, table);
    }

    @Test
    public void computeSuffixTEst() {
        int[] table = new int[6];
        b.computeSuffix(PATTERN_2, table);
        assertArrayEquals(new int[]{1, 0, 5, 0, 0, 0}, table);
    }

    @Test
    public void preprocessSuffixTableTest() {
        int[] table = b.preComputeSuffixTable(PATTERN_2);
        assertArrayEquals(new int[]{1, 7, 5, 9, 10, 11}, table);
    }
    @Test
    public void searchTest() {
        assertEquals(14, b.search("thisisour firsttest".toCharArray(), "tt".toCharArray()));
        assertEquals(6, b.search("abcdbabaidai".toCharArray(), "baidai".toCharArray()));
        assertEquals(0, b.search("abcdbabaidai".toCharArray(), "a".toCharArray()));
        assertEquals(0, b.search("abcdbabaidai".toCharArray(), "ab".toCharArray()));
        assertEquals(1, b.search("abcdbabaidai".toCharArray(), "b".toCharArray()));
        assertEquals(9, b.search("abcdbabaidai".toCharArray(), "dai".toCharArray()));
        assertEquals(3, b.search("asdz".toCharArray(), "z".toCharArray()));
        assertEquals(0, b.search("asdz".toCharArray(), "".toCharArray()));
        assertEquals(0, b.search("asdz".toCharArray(), null));
        assertEquals(-1, b.search(null, "z".toCharArray()));
        assertEquals(-1, b.search("abcdbabaidai".toCharArray(), "baidaiz".toCharArray()));
    }
}
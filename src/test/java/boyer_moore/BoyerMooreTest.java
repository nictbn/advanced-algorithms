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
}
package rabin_karp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RabinKarpTest {
    RabinKarp r = new RabinKarp();
    @BeforeEach
    void setUp() {
        r = new RabinKarp();
    }

    @Test
    void shiftCharacterValueTest() {
        assertEquals(97, (int) 'a');
        assertEquals(1, r.shiftCharacterValue('a'));
    }

    @Test
    void calculateHashTest() {
        assertEquals(28L, r.calculateHash("acbacc".toCharArray(), 3));
    }

    @Test
    void recalculateHashTest() {
        assertEquals(18L, r.recalculateHash(28L, 'a', 'a', 3));
    }

    @Test
    void matchTest() {
        assertTrue(r.match("learning".toCharArray(), "nin".toCharArray(), 4));
        assertTrue(r.match("learning".toCharArray(), "le".toCharArray(), 0));
        assertTrue(r.match("learning".toCharArray(), "g".toCharArray(), 7));

        assertFalse(r.match("learning".toCharArray(), "ing".toCharArray(), 4));
        assertFalse(r.match("learning".toCharArray(), "l".toCharArray(), 1));
    }

    @Test
    void searchTest() {
        assertEquals(3, r.search("acbacc".toCharArray(), "acc".toCharArray()));
        assertEquals(4, r.search("learning".toCharArray(), "nin".toCharArray()));
        assertEquals(-1, r.search("learning".toCharArray(), "nina".toCharArray()));
        assertEquals(0, r.search("learning".toCharArray(), "le".toCharArray()));
        assertEquals(5, r.search("learning".toCharArray(), "ing".toCharArray()));
        assertEquals(5, r.search("learning".toCharArray(), "in".toCharArray()));
        assertEquals(6, r.search("learning".toCharArray(), "ng".toCharArray()));
        assertEquals(7, r.search("learning".toCharArray(), "g".toCharArray()));

        assertEquals(-1, r.search("learning".toCharArray(), null));
        assertEquals(-1, r.search(null, "ing".toCharArray()));
        assertEquals(-1, r.search(null, null));


    }
}
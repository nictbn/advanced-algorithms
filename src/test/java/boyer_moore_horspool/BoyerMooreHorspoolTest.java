package boyer_moore_horspool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoyerMooreHorspoolTest {
    BoyerMooreHorspool b = null;

    @BeforeEach
    void setUp() {
        b = new BoyerMooreHorspool();
    }

    @Test
    void preprocessTableTest() {
        int[] table = b.preprocessTable("test".toCharArray());
        assertEquals(1, table['t']);
        assertEquals(2, table['e']);
        assertEquals(1, table['s']);
        assertEquals(4, table['x']);

        table = b.preprocessTable("abc".toCharArray());
        assertEquals(2, table['a']);
        assertEquals(1, table['b']);
        assertEquals(3, table['c']);
        assertEquals(3, table['d']);

        table = b.preprocessTable("abcdb".toCharArray());
        assertEquals(4, table['a']);
        assertEquals(1, table['b']);
        assertEquals(2, table['c']);
        assertEquals(1, table['d']);
        assertEquals(5, table['j']);
    }

    @Test
    void searchTest() {
        assertEquals(4, b.search("learning".toCharArray(), "nin".toCharArray()));
        assertEquals(7, b.search("learning".toCharArray(), "g".toCharArray()));
        assertEquals(0, b.search("learning".toCharArray(), "l".toCharArray()));
        assertEquals(0, b.search("learning".toCharArray(), "le".toCharArray()));
        assertEquals(0, b.search("learning".toCharArray(), "learning".toCharArray()));
        assertEquals(5, b.search("learning".toCharArray(), "in".toCharArray()));
        assertEquals(-1, b.search("learning".toCharArray(), "x".toCharArray()));
        assertEquals(5, b.search("learning".toCharArray(), "in".toCharArray()));

        assertEquals(0, b.search("learning".toCharArray(), null));
        assertEquals(-1, b.search(null, "abc".toCharArray()));
        assertEquals(0, b.search(null, null));
    }
}
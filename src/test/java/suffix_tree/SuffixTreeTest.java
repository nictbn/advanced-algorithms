package suffix_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SuffixTreeTest {
    SuffixTree t;
    @BeforeEach
    void setUp() {
        t = new SuffixTree("banana".toCharArray());
    }

    @Test
    void addUniqueTest() {
        String expected = Arrays.toString("bana$".toCharArray());
        String result = Arrays.toString(t.addUnique("bana".toCharArray()));
        assertEquals(expected, result);
    }
}
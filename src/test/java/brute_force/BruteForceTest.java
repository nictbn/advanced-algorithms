package brute_force;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BruteForceTest {
    BruteForce b = null;
    char[] array;
    @BeforeEach
    void setUp() {
        b = new BruteForce();
        String s = "academy.learnprogramming";
        array = s.toCharArray();
    }

    @Test
    void firstMatch() {
        assertEquals(0, b.firstMatch(array, new char[]{'a'}));
        assertEquals(16, b.firstMatch(array, new char[]{'g'}));
        assertEquals(22, b.firstMatch(array, new char[]{'n','g'}));
        assertEquals(6, b.firstMatch(array, new char[]{'y','.', 'l', 'e'}));
        assertEquals(-1, b.firstMatch(array, new char[]{'z', 'z'}));
    }

    @Test
    void everyMatch() {
        int[] expected = new int[array.length];
        resetExpected(expected);
        expected[0] = 0;
        expected[1] = 2;
        expected[2] = 10;
        expected[3] = 18;
        int[] found = b.everyMatch(array, new char[]{'a'});
        assertArrayEquals(expected, found);

        resetExpected(expected);
        found = b.everyMatch(array, new char[]{'g'});
        expected[0] = 16;
        expected[1] = 23;
        assertArrayEquals(expected, found);


        resetExpected(expected);
        found = b.everyMatch(array, new char[]{'.'});
        expected[0] = 7;
        assertArrayEquals(expected, found);
    }

    public void resetExpected(int[] expected) {
        Arrays.fill(expected, -1);
    }
}
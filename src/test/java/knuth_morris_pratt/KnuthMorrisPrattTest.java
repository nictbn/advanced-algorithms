package knuth_morris_pratt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnuthMorrisPrattTest {

    @Test
    void computeLongestSuffixPrefixTable() {
        KnuthMorrisPratt kmp = new KnuthMorrisPratt();
        int[] actual = kmp.computeLongestSuffixPrefixTable(new char[]{'a', 'b', 'a', 'b', 'a', 'c'});
        int[] expected = new int[]{0, 0, 1, 2, 3, 0};
        assertArrayEquals(expected, actual);

        actual = kmp.computeLongestSuffixPrefixTable(new char[]{'a', 'a', 'n', 'a', 'c'});
        expected = new int[]{0, 1, 0, 1, 0};
        assertArrayEquals(expected, actual);

        actual = kmp.computeLongestSuffixPrefixTable(new char[]{'a', 'b', 'c', 'd', 'a', 'b', 'c', 'a'});
        expected = new int[]{0, 0, 0, 0, 1, 2, 3, 1};
        assertArrayEquals(expected, actual);

        actual = kmp.computeLongestSuffixPrefixTable(new char[]{'c', 'f', 'g', 'c', 'f', 'a'});
        expected = new int[]{0, 0, 0, 1, 2, 0};
        assertArrayEquals(expected, actual);

        actual = kmp.computeLongestSuffixPrefixTable(new char[]{'a', 'a', 'b', 'a', 'c', 'a', 'z'});
        expected = new int[]{0, 1, 0, 1, 0, 1, 0};
        assertArrayEquals(expected, actual);

        actual = kmp.computeLongestSuffixPrefixTable(new char[]{'a'});
        expected = new int[]{0};
        assertArrayEquals(expected, actual);

        actual = kmp.computeLongestSuffixPrefixTable(new char[]{'a', 'a'});
        expected = new int[]{0, 1};
        assertArrayEquals(expected, actual);

        actual = kmp.computeLongestSuffixPrefixTable(new char[]{'a', 'b'});
        expected = new int[]{0, 0};
        assertArrayEquals(expected, actual);
    }
}
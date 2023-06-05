package suffix_trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SuffixTrieMapTest {
    SuffixTrieMap t;
    @BeforeEach
    void setUp() {
        t = new SuffixTrieMap("banana");
    }

    @Test
    void insertSuffixTest() {
        assertNotNull(t.root.children.get('b'));
        assertNotNull(t.root.children.get('a'));
        assertNotNull(t.root.children.get('n'));
        assertNull(t.root.children.get('c'));

        assertNotNull(t.root.children.get('b').children.get('a'));
        assertNotNull(t.root.children.get('b').children.get('a').children.get('n').children.get('a').children.get('n').children.get('a').children.get('$'));
        assertNotNull(t.root.children.get('a').children.get('n').children.get('a').children.get('n').children.get('a').children.get('$'));

        assertNull(t.root.children.get('b').children.get('n'));

        assertNotNull(t.root.children.get('a').children.get('$'));
        assertNotNull(t.root.children.get('a').children.get('n'));
        assertNull(t.root.children.get('a').children.get('a'));
    }

    @Test
    public void searchTest() {
        List<Integer> result1 = t.search("ana");
        assertEquals(2, result1.size());
        assertEquals(3, result1.get(0));
        assertEquals(5, result1.get(1));

        List<Integer> result2 = t.search("a");
        assertEquals(3, result2.size());
        assertEquals(1, result2.get(0));
        assertEquals(3, result2.get(1));
        assertEquals(5, result2.get(2));

        assertNull(t.search("anc"));
    }

    @Test
    public void isSuffixTest() {
        assertTrue(t.isSuffix("ana"));
        assertTrue(t.isSuffix("na"));
        assertTrue(t.isSuffix("a"));
        assertFalse(t.isSuffix("ba"));
        assertFalse(t.isSuffix("nac"));
    }

    @Test
    public void isSubstringTest() {
        assertTrue(t.isSubstring("nan"));
        assertTrue(t.isSubstring("nana"));
        assertTrue(t.isSubstring("anana"));
        assertTrue(t.isSubstring("a"));
        assertFalse(t.isSubstring("ac"));
        assertFalse(t.isSubstring("c"));
    }
}
package suffix_trie;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuffixTrieTest {

    @Test
    void insertTest() {
        SuffixTrie t = new SuffixTrie("banana");
        assertNotNull(t.root.children['b']);
        assertNotNull(t.root.children['a']);
        assertNotNull(t.root.children['n']);
        assertNull(t.root.children['c']);

        assertNotNull(t.root.children['b'].children['a']);
        assertNotNull(t.root.children['b'].children['a'].children['n'].children['a'].children['n'].children['a'].children['$']);
        assertNotNull(t.root.children['a'].children['n'].children['a'].children['n'].children['a'].children['$']);

        assertNull(t.root.children['b'].children['n']);

        assertNotNull(t.root.children['a'].children['$']);
        assertNotNull(t.root.children['a'].children['n']);
        assertNull(t.root.children['a'].children['a']);
    }

    @Test
    public void searchTest() {
        SuffixTrie t = new SuffixTrie("banana");
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
        SuffixTrie t = new SuffixTrie("banana");
        assertTrue(t.isSuffix("ana"));
        assertTrue(t.isSuffix("na"));
        assertTrue(t.isSuffix("a"));
        assertFalse(t.isSuffix("ba"));
        assertFalse(t.isSuffix("nac"));
    }

    @Test
    public void isSubstringTest() {
        SuffixTrie t = new SuffixTrie("banana");
        assertTrue(t.isSubstring("nan"));
        assertTrue(t.isSubstring("nana"));
        assertTrue(t.isSubstring("anana"));
        assertTrue(t.isSubstring("a"));
        assertFalse(t.isSubstring("ac"));
        assertFalse(t.isSubstring("c"));
    }
}
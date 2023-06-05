package suffix_trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
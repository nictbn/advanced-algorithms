package suffix_trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SuffixTrieMapTest {

    @Test
    void insertTest() {
        SuffixTrieMap t = new SuffixTrieMap("banana");
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
}
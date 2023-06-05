package prefix_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrefixTreeTest {
    PrefixTree tree;

    @BeforeEach
    void setUp() {
        tree = new PrefixTree();
    }

    @Test
    public void insertTest() {
        tree.insert("cat".toCharArray(), 1);
        tree.insert("door".toCharArray(), 2);
        tree.insert("cats".toCharArray(), 3);
        System.out.println(tree.root);
        assertEquals(true, tree.root.getChild('c').getChild('a').getChild('t').isWord);
        assertEquals('c', tree.root.getChild('c').c);
        assertEquals(0, tree.root.getChild('c').id);
        assertEquals('a', tree.root.getChild('c').getChild('a').c);
        assertEquals(0, tree.root.getChild('c').getChild('a').id);
        assertEquals('t', tree.root.getChild('c').getChild('a').getChild('t').c);
        assertEquals(1, tree.root.getChild('c').getChild('a').getChild('t').id);
    }

    @Test
    public void findTest() {
        tree.insert("cat".toCharArray(), 1);
        tree.insert("door".toCharArray(), 2);
        tree.insert("cats".toCharArray(), 3);

        assertEquals(3, tree.find("cats".toCharArray()).id);
        assertEquals(2, tree.find("door".toCharArray()).id);
        assertEquals(1, tree.find("cat".toCharArray()).id);

        assertTrue(tree.find("cats".toCharArray()).isWord);
        assertTrue(tree.find("door".toCharArray()).isWord);
        assertTrue(tree.find("cat".toCharArray()).isWord);
        assertNull(tree.find("cantfind".toCharArray()));
    }

    @Test
    public void deleteTest() {
        tree.insert("cat".toCharArray(), 1);
        tree.insert("cats".toCharArray(), 2);
        tree.insert("door".toCharArray(), 3);
        tree.insert("done".toCharArray(), 4);
        tree.insert("horse".toCharArray(), 5);
        System.out.println(tree.root);
        tree.delete("horse".toCharArray());
        System.out.println(tree.root);
        tree.delete("cat".toCharArray());
        System.out.println(tree.root);
        tree.delete("cats".toCharArray());
        System.out.println(tree.root);
    }
}
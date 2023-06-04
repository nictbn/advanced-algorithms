package huffman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.PriorityQueue;

import static huffman.Huffman.CHARACTER_LIMIT;
import static org.junit.jupiter.api.Assertions.*;

class HuffmanTest {
    public static final char[] PATTERN_1 = "aabbbcd".toCharArray();
    Huffman h;
    @BeforeEach
    void setUp() {
        h = new Huffman();
    }

    @Test
    void createFrequencyTableTest() {
        int[] frequencies = h.createFrequencyTable(PATTERN_1);
        assertEquals(CHARACTER_LIMIT, frequencies.length);
        assertEquals(2, frequencies['a']);
        assertEquals(3, frequencies['b']);
        assertEquals(1, frequencies['c']);
        assertEquals(1, frequencies['d']);
    }

    @Test
    void createPriorityQueueTest() {
        int[] frequencies = h.createFrequencyTable(PATTERN_1);
        PriorityQueue<HuffmanNode> queue = h.createPriorityQueue(frequencies);
        assertEquals(4, queue.size());
        assertNotNull(queue.peek());
        assertEquals('c', queue.peek().getCharacter());
        assertEquals(1, Objects.requireNonNull(queue.poll()).getFrequency());

        assertNotNull(queue.peek());
        assertEquals('d', queue.peek().getCharacter());
        assertEquals(1, Objects.requireNonNull(queue.poll()).getFrequency());

        assertNotNull(queue.peek());
        assertEquals('a', queue.peek().getCharacter());
        assertEquals(2, Objects.requireNonNull(queue.poll()).getFrequency());

        assertNotNull(queue.peek());
        assertEquals('b', queue.peek().getCharacter());
        assertEquals(3, Objects.requireNonNull(queue.poll()).getFrequency());

        assertEquals(0, queue.size());
    }

    @Test
    public void getTwoLeastUsedAsOneNodeTest() {
        int[] frequencies = h.createFrequencyTable(PATTERN_1);
        PriorityQueue<HuffmanNode> queue = h.createPriorityQueue(frequencies);

        HuffmanNode root = h.getTwoLeastUsedAsOneNode(queue);
        assertEquals('-', root.getCharacter());
        assertEquals(2, root.getFrequency());

        assertEquals('c', root.getLeft().getCharacter());
        assertEquals(1, root.getLeft().getFrequency());
        assertNull(root.getLeft().getLeft());
        assertNull(root.getLeft().getRight());

        assertEquals('d', root.getRight().getCharacter());
        assertEquals(1, root.getRight().getFrequency());
        assertNull(root.getLeft().getLeft());
        assertNull(root.getRight().getRight());



        root = h.getTwoLeastUsedAsOneNode(queue);

        assertEquals('-', root.getCharacter());
        assertEquals(5, root.getFrequency());

        assertEquals('a', root.getLeft().getCharacter());
        assertEquals(2, root.getLeft().getFrequency());
        assertNull(root.getLeft().getLeft());
        assertNull(root.getLeft().getRight());

        assertEquals('b', root.getRight().getCharacter());
        assertEquals(3, root.getRight().getFrequency());
        assertNull(root.getLeft().getLeft());
        assertNull(root.getRight().getRight());
    }
}
package huffman;

import java.util.PriorityQueue;

public class Huffman {
    public static final int CHARACTER_LIMIT = 256;
    public int[] createFrequencyTable(char[] text) {
        int[] frequencies = new int[CHARACTER_LIMIT];
        for (int i = 0; i < text.length; i++) {
            frequencies[text[i]]++;
        }
        return frequencies;
    }

    public PriorityQueue<HuffmanNode> createPriorityQueue(int[] frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(1, new FrequencyComparator());
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.add(new HuffmanNode((char) i, frequencies[i]));
            }
        }
        return queue;
    }
}

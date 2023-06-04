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

    public HuffmanNode createHuffmanTree(PriorityQueue<HuffmanNode> queue) {
        HuffmanNode root = null;
        while (queue.size() > 0) {
            root = getTwoLeastUsedAsOneNode(queue);
            if (queue.size() > 0) {
                queue.add(root);
            }
        }
        return root;
    }

    public String compress(char[] text) {
        int[] frequencies = createFrequencyTable(text);
        PriorityQueue<HuffmanNode> queue = createPriorityQueue(frequencies);
        HuffmanNode root = createHuffmanTree(queue);
        String compressed = encodeString(text, root);
        return compressed;
    }

    public String encodeString(char[] text, HuffmanNode root) {
        StringBuilder s = new StringBuilder();
        String[] array = new String[CHARACTER_LIMIT];
        generateBytes(array, root, new StringBuilder());
        for (int i = 0; i < text.length; i++) {
            s.append(array[text[i]]);
        }
        return s.toString();
    }

    public void generateBytes(String[] array, HuffmanNode root, StringBuilder s) {
        if (root.getCharacter() == '-') {
            s.append("0");
            generateBytes(array, root.getLeft(), s);
            s.append("1");
            generateBytes(array, root.getRight(), s);
        } else {
            System.out.println(root.getCharacter() + " - " + s.toString());
            array[root.getCharacter()] = s.toString();
            s.deleteCharAt(s.length() - 1);
        }
    }

    public HuffmanNode getTwoLeastUsedAsOneNode(PriorityQueue<HuffmanNode> queue) {
        HuffmanNode node1 = queue.poll();
        HuffmanNode node2 = queue.poll();
        HuffmanNode root = new HuffmanNode('-', node1.getFrequency() + node2.getFrequency());
        root.setLeft(node1);
        root.setRight(node2);
        return root;
    }
}

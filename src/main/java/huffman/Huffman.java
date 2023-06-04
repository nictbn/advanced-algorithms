package huffman;

import java.util.PriorityQueue;

public class Huffman {
    public static final int CHARACTER_LIMIT = 256;
    StringBuilder header = new StringBuilder();
    public int[] createFrequencyTable(char[] text) {
        int[] frequencies = new int[CHARACTER_LIMIT];
        for (int i = 0; i < text.length; i++) {
            frequencies[text[i]]++;
        }
        return frequencies;
    }

    public PriorityQueue<HuffmanNode> createPriorityQueue(int[] frequencies) {
        header = new StringBuilder();
        header.append((char) 1);
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(1, new FrequencyComparator());
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.add(new HuffmanNode((char) i, frequencies[i]));
                header.append(":").append((char) i).append(frequencies[i]);
            }
        }
        header.append((char) 2);
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
        return header.toString() + encodeString(text, root);
    }

    public char[] decompress(char[] encodedText) {
        if (encodedText[0] != (char) 1) {
            return null;
        }
        int[] frequencies = parseHeaderAsFrequency(encodedText);
        PriorityQueue<HuffmanNode> queue = createPriorityQueue(frequencies);
        HuffmanNode root = createHuffmanTree(queue);
        String decompressed = decodeString(encodedText, root);
        return decompressed.toCharArray();
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

    public String decodeString(char[] text, HuffmanNode root) {
        StringBuilder s = new StringBuilder();
        HuffmanNode currentNode = root;
        for (int i = header.length(); i < text.length; i++) {
            if (text[i] - '0' == 0) {
                currentNode = currentNode.getLeft();
            } else if (text[i] - '0' == 1) {
                currentNode = currentNode.getRight();
            }
            if (isLeaf(currentNode)) {
                s.append(currentNode.getCharacter());
                currentNode = root;
            }
        }
        return s.toString();
    }

    public boolean isLeaf(HuffmanNode currentNode) {
        if (currentNode == null) {
            return false;
        }
        return currentNode.getLeft() == null && currentNode.getRight() == null;
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

    public int[] parseHeaderAsFrequency(char[] text) {
        int[] frequencies = new int[CHARACTER_LIMIT];
        int i = 0;
        for (; i < text.length && text[i] != (char) 2; i++) {
            header.append(text[i]);
            if (text[i] == ':') {
                i++;
                header.append(text[i]);
                int f = 0;
                int m = 1;
                int j = i + 1;
                for (; j < text.length && text[j] != (char) 2 && text[j] != ':'; j++) {
                    f = (f * m) + text[j] - '0';
                    if (f != 0) {
                        m = 10;
                    }
                    header.append(text[i] - '0');
                }
                frequencies[text[i]] = f;
                i = j - 1;
            }
        }
        return frequencies;
    }
}

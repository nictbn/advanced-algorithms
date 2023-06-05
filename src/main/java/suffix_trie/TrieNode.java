package suffix_trie;

public class TrieNode {
    public final int MAX_ARRAY = 256;
    TrieNode[] children = new TrieNode[MAX_ARRAY];

    public TrieNode() {
        for (int i = 0; i < MAX_ARRAY; i++) {
            children[i] = null;
        }
    }

    public void insertSuffix(String text) {
        text = text + "$";
        for (int i = 0; i < text.length(); i++) {
            insertSuffix(text, i);
        }
    }

    public void insertSuffix(String text, int index) {
        if (text.length() > index) {
            char current = text.charAt(index);
            if (children[current] == null) {
                children[current] = new TrieNode();
            }
            children[current].insertSuffix(text, ++index);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int c = 0; c < children.length; c++) {
            if (children[c] != null) {
                result.append((char) c).append("->").append(children[c].toString());
            }
        }
        return result.toString();
    }
}

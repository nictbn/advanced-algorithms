package suffix_trie;

public class SuffixTrieMap {
    TrieNodeMap root = new TrieNodeMap();
    public SuffixTrieMap(String text) {
        root.insertSuffix(text);
    }
}

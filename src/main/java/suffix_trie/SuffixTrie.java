package suffix_trie;

public class SuffixTrie {
    TrieNode root = new TrieNode();
    public SuffixTrie(String text) {
        root.insertSuffix(text);
    }
}

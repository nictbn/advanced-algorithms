package suffix_trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNodeMap {
    Map<Character, TrieNodeMap> children = new HashMap<>();

    public void insertSuffix(String text) {
        text = text + "$";
        for (int i = 0; i < text.length(); i++) {
            insertSuffix(text, i);
        }
    }

    public void insertSuffix(String text, int index) {
        if (text.length() > index) {
            char current = text.charAt(index);
            if (children.get(current) == null) {
                children.put(current, new TrieNodeMap());
            }
            children.get(current).insertSuffix(text, ++index);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Character c : children.keySet()) {
            result.append(c).append("->").append(children.get(c).toString());
        }
        return result.toString();
    }
}

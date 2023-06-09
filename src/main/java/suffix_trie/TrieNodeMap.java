package suffix_trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TrieNodeMap {
    Map<Character, TrieNodeMap> children = new HashMap<>();
    List<Integer> indexes = new LinkedList<>();

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
            children.get(current).indexes.add(index);
            children.get(current).insertSuffix(text, ++index);
        }
    }

    public List<Integer> search(String pattern) {
        return search(pattern, 0);
    }

    private List<Integer> search(String pattern, int startPosition) {
        if (pattern.length() == startPosition) {
            return indexes;
        }
        if (children.get(pattern.charAt(startPosition)) != null) {
            return children.get(pattern.charAt(startPosition)).search(pattern, ++startPosition);
        }
        return null;
    }

    public boolean isSuffix(String pattern) {
        return isSuffix(pattern, 0);
    }
    public boolean isSuffix(String pattern, int startPosition) {
        if (pattern.length() == startPosition) {
            return children.get('$') != null;
        }
        if (children.get(pattern.charAt(startPosition)) != null) {
            return children.get(pattern.charAt(startPosition)).isSuffix(pattern, ++startPosition);
        }
        return false;
    }

    public boolean isSubstring(String pattern) {
        List<Integer> indexes = search(pattern);
        return indexes != null;
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

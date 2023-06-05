package prefix_tree;

public class PrefixTree {
    PrefixNode root;

    public PrefixTree() {
        root = new PrefixNode();
    }

    public void insert(char[] word, int id) {
        PrefixNode current = root;
        for (int i = 0; i < word.length; i++) {
            if (current.hasChild(word[i])) {
                current = current.getChild(word[i]);
            } else {
                PrefixNode node = new PrefixNode(word[i], 0);
                current.addChild(node);
                current = node;
            }
        }
        current.isWord = true;
        current.id = id;
    }

    public PrefixNode find(char[] word) {
        PrefixNode current = root;
        for (int i = 0; i < word.length; i++) {
            if (current.hasChild(word[i])) {
                current = current.getChild(word[i]);
            } else {
                return null;
            }
        }
        return current;
    }

    public boolean delete (char[] word) {
        return delete(word, root, 0);
    }

    public boolean delete(char[] word, PrefixNode node, int wordIndex) {
        if (wordIndex == word.length - 1) {
            PrefixNode w = node.getChild(word[wordIndex]);
            w.isWord = false;
            w.id = 0;
            if (w.canDelete()) {
                node.children.remove(word[wordIndex]);
                return true;
            }
        } else {
            if (!node.hasChild(word[wordIndex])) {
                return false;
            }
            boolean canDelete = delete(word, node.getChild(word[wordIndex]), ++wordIndex);
            if (canDelete) {
                if (!node.getChild(word[--wordIndex]).isWord) {
                    node.children.remove(word[wordIndex]);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}

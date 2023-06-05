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
}

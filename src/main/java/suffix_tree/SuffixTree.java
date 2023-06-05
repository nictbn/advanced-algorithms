package suffix_tree;

public class SuffixTree {
    public final char UNIQUE = '$';

    SuffixNode root;

    private ActivePoint activePoint;

    private char[] input;

    private int remaining;

    private End end;

    public SuffixTree(char[] input) {
        this.input = addUnique(input);
    }

    public char[] addUnique(char[] input) {
        char[] c = new char[input.length + 1];
        for (int i = 0; i < input.length; i++) {
            c[i] = input[i];
        }
        c[input.length] = UNIQUE;
        return c;
    }

    public void buildSuffixTree() {
        root = new SuffixNode(0, new End(0));
        root.index = -1;
        end = new End(-1);
        activePoint = new ActivePoint(root);

        for (int i = 0; i < input.length; i++) {
            startPhase(i);
        }
    }

    public void startPhase(int i) {
        end.end++;
        remaining++;
        while (remaining > 0) {
            if (activePoint.activeLength == 0) {
                if (activePoint.activeNode.children[input[i]] != null) {
                    activePoint.activeEdge = activePoint.activeNode.children[input[i]].start;
                    activePoint.activeLength++;
                    break;
                } else {
                    root.children[input[i]] = new SuffixNode(i, end);
                    remaining--;
                }
            } else {
                // TODO REMOVE LATER
                remaining--;
            }
        }
    }
}

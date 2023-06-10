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
        SuffixNode lastInternalNode = null;
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
                SuffixNode edge = activePoint.activeNode.children[input[activePoint.activeEdge]];
                char c = getNextCharacter(edge);
                if (c ==  input[i]) {
                    if (lastInternalNode != null) {
                        lastInternalNode.suffixLink = edge;
                    }
                    walkDown(i);
                    break;
                } else {
                    int currentStart = edge.start;
                    edge.start += activePoint.activeLength;
                    SuffixNode internalNode = new SuffixNode(currentStart, new End(edge.start - 1));
                    SuffixNode leafNode = new SuffixNode(i, end);
                    internalNode.children[input[edge.start]] = edge;
                    internalNode.children[input[leafNode.start]] = leafNode;
                    internalNode.index = -1;
                    activePoint.activeNode.children[input[internalNode.start]] = internalNode;

                    if (lastInternalNode != null) {
                        lastInternalNode.suffixLink = internalNode;
                    }
                    lastInternalNode = internalNode;
                    internalNode.suffixLink = root;
                }
                if (activePoint.activeNode != root) {
                    activePoint.activeNode = activePoint.activeNode.suffixLink;
                } else {
                    activePoint.activeEdge++;
                    activePoint.activeLength--;
                }
                remaining--;
            }
        }
    }

    private void walkDown(int index) {
        SuffixNode edge = activePoint.activeNode.children[input[activePoint.activeEdge]];
        if (edgeSize(edge) < activePoint.activeLength) {
            activePoint.activeNode = edge;
            activePoint.activeLength = activePoint.activeLength - edgeSize(edge);
            activePoint.activeEdge = edge.children[input[index]].start;
        } else {
            activePoint.activeLength++;
        }
    }

    private char getNextCharacter(SuffixNode edge) {
        if (edgeSize(edge) >= activePoint.activeLength) {
            return input[edge.start + activePoint.activeLength];
        }
        return '%';
    }

    public int edgeSize(SuffixNode edge) {
        return (edge.end.end - edge.start);
    }


}

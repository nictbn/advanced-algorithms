package suffix_tree;

import java.util.ArrayList;
import java.util.List;

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
        root = new SuffixNode(1, new End(0));
        root.index = -1;
        end = new End(-1);
        activePoint = new ActivePoint(root);

        for (int i = 0; i < input.length; i++) {
            startPhase(i);
        }
        setIndex(root, 0, input.length);
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
                    System.out.println("Phase(" + input[i] + ") Rule 3 extension applied(" + activePoint.activeNode.start + ":" + input[i] + ")");
                    break;
                } else {
                    root.children[input[i]] = new SuffixNode(i, end);
                    remaining--;
                    System.out.println("Phase(" + input[i] + ") Rule 2 extension created(" + activePoint.activeNode.start + ":" + input[i] + ")");
                }
            } else {
                char c = getNextCharacter(i);
                if (c != 0) {
                    if (c == input[i]) {
                        SuffixNode edge = selectEdge();
                        if (lastInternalNode != null) {
                            lastInternalNode.suffixLink = edge;
                        }
                        System.out.println("Phase(" + input[i] + ") Rule 3 extension start(" + edge.start + ":" + input[edge.start] + ") Next Char match: " + i + ":" + input[i] + "-" + c);
                        walkDown(i);
                        break;
                    } else {
                        SuffixNode edge = selectEdge();
                        int currentStart = edge.start;
                        edge.start += activePoint.activeLength;
                        SuffixNode internalNode = new SuffixNode(currentStart, new End(currentStart + activePoint.activeLength - 1));
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
                        remaining--;
                        System.out.println("Phase(" + input[i] + ") Rule 2 Ext - changed node (" + internalNode.start + "-" + internalNode.end.end + "):" + input[internalNode.start]);
                        System.out.println("Phase(" + input[i] + ") Rule 2 Ext --- children[edge] " + edge.start + ":" + input[edge.start]);
                        System.out.println("Phase(" + input[i] + ") Rule 2 Ext --- children[leafNode] " + leafNode.start + ":" + input[leafNode.start]);
                    }
                } else {
                    SuffixNode edge = selectEdge();
                    edge.children[input[i]] = new SuffixNode(i, end);
                    if (lastInternalNode != null) {
                        lastInternalNode.suffixLink = edge;
                    }
                    lastInternalNode = edge;
                }
                if (activePoint.activeNode != root) {
                    activePoint.activeNode = activePoint.activeNode.suffixLink;
                } else {
                    activePoint.activeEdge++;
                    activePoint.activeLength--;
                }
            }
        }
    }

    private void walkDown(int index) {
        SuffixNode edge = selectEdge();
        if (edgeSize(edge) < activePoint.activeLength) {
            activePoint.activeNode = edge;
            activePoint.activeLength = activePoint.activeLength - edgeSize(edge);
            activePoint.activeEdge = edge.children[input[index]].start;
        } else {
            activePoint.activeLength++;
        }
    }

    private char getNextCharacter(int i) {
        SuffixNode edge = selectEdge();
        if (edgeSize(edge) >= activePoint.activeLength) {
            return input[edge.start + activePoint.activeLength];
        } else if (edgeSize(edge) + 1 == activePoint.activeLength) {
            if (edge.children[input[i]] != null) {
                return input[i];
            }
            return 0;
        } else {
            activePoint.activeNode = edge;
            activePoint.activeEdge = activePoint.activeEdge + edgeSize(edge) + 1;
            activePoint.activeLength = activePoint.activeLength - edgeSize(edge) - 1;
            return getNextCharacter(i);
        }
    }

    public SuffixNode selectEdge() {
        return activePoint.activeNode.children[input[activePoint.activeEdge]];
    }

    public int edgeSize(SuffixNode edge) {
        return (edge.end.end - edge.start);
    }
    public void dfsTraversal() {
        List<Character> result = new ArrayList<>();
        for (SuffixNode node : root.children) {
            dfsTraversal(node, result);
        }
    }
    private void dfsTraversal(SuffixNode node, List<Character> result) {
        if (node == null) {
            return;
        }
        if (node.index != -1) {
            for (int i = node.start; i <= node.end.end; i++) {
                result.add(input[i]);
            }
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
            System.out.println("index = " + node.index);
            for (int i = node.start; i <= node.end.end; i++) {
                result.remove(result.size() - 1);
            }
            return;
        }
        for (int i = node.start; i <= node.end.end; i++) {
            result.add(input[i]);
        }
        for (SuffixNode s : node.children) {
            dfsTraversal(s, result);
        }
        for (int i = node.start; i <= node.end.end; i++) {
            result.remove(result.size() - 1);
        }
    }

    private void setIndex(SuffixNode root, int val, int size) {
        if (root == null) {
            return;
        }
        val = val + root.end.end - root.start + 1;
        if (root.index != -1) {
            root.index = size - val;
            return;
        }
        for (SuffixNode node : root.children) {
            setIndex(node, val, size);
        }
    }
}

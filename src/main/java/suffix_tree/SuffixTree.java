package suffix_tree;

public class SuffixTree {
    public final char UNIQUE = '$';

    private SuffixNode root;

    private ActivePoint activePoint;

    private char[] input;

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
}
